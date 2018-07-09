package com.example.adya.resourcehandle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyHolder>{

    List<ListData> listdata;

    public RecyclerviewAdapter(List<ListData> listdata) {
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, int position) {
        ListData data = listdata.get(position);
        holder.vname.setText(data.getName());
        holder.vemail.setText(data.getEmail());
        holder.vid.setText(data.getId());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView vname , vid,vemail;

        public MyHolder(final View itemView) {
            super(itemView);

            vname = (TextView) itemView.findViewById(R.id.vname);
            vemail = (TextView) itemView.findViewById(R.id.vemail);
            vid = (TextView) itemView.findViewById(R.id.id2);




        }
    }


}
