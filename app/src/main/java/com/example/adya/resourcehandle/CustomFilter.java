package com.example.adya.resourcehandle;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter  extends Filter {

    public CustomAdapter adapter;
    public ArrayList<DataModel> filterList;

    public CustomFilter(ArrayList<DataModel> filterList,CustomAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<DataModel> filteredItems=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getItemid().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredItems.add(filterList.get(i));
                }
            }

            results.count=filteredItems.size();
            results.values=filteredItems;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.dataSet= (ArrayList<DataModel>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
