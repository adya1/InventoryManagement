package com.example.adya.resourcehandle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity  extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private  ArrayList<DataModel> data=new ArrayList<>();
    static View.OnClickListener myOnClickListener;
    private Random mRandom = new Random();
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<ViewDataModel> list;
    /*private static ArrayList<Integer> removedItems;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  myOnClickListener = new MyOnClickListener(this);*/


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        /*layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        //  data = new ArrayList<DataModel>();
        /*for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],

                    MyData.id_[i]

            ));
        }*/

        /* removedItems = new ArrayList<Integer>();
         */
        /* adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);*/
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        view();
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    DataModel dataModel = dataSnapshot1.getValue(DataModel.class);

                    ViewDataModel listdata = new ViewDataModel();
                    String ename = dataModel.getEmpname();

                    String id = dataModel.getItemid();
                    listdata.setEmpname(ename);
                    dataModel.setEmpname(ename);
                    dataModel.setItemid(id);
                    listdata.setItemid(id);

                    list.add(listdata);
                    data.add(dataModel);
                    adapter = new CustomAdapter(data);


                }


                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

    private void view() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    DataModel dataModel = dataSnapshot1.getValue(DataModel.class);

                    ViewDataModel listdata = new ViewDataModel();
                    String ename = dataModel.getEmpname();

                    String id = dataModel.getItemid();
                    listdata.setEmpname(ename);
                    dataModel.setEmpname(ename);
                    dataModel.setItemid(id);
                    listdata.setItemid(id);

                    list.add(listdata);
                    data.add(dataModel);
                    adapter = new CustomAdapter(data);


                }


                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            /*removeItem(v);*/
        }
/*
        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);

            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }*/
    }



    @Override


  public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

 if (item.getItemId() == R.id.add_item) {

     int itemId= + mRandom.nextInt(100);
     database = FirebaseDatabase.getInstance();
     myRef = database.getReference("message");




     DataModel dm=new DataModel();
     dm.setEmpname("Available");
     dm.setItemid("Item"+String.valueOf(itemId));
     if(data.size()>0)
     {data.clear();

            data.add(0,dm);
     adapter.notifyDataSetChanged();}
else
         data.add(0,dm);
     /*adapter = new CustomAdapter(data);
     layoutManager = new LinearLayoutManager(this);
     recyclerView.setLayoutManager(layoutManager);
     recyclerView.setItemAnimator(new DefaultItemAnimator());
     recyclerView.setAdapter(adapter);*/
     //adapter.notifyDataSetChanged();
     myRef.push().setValue(dm);
     Toast.makeText(getApplicationContext(),"Item added",Toast.LENGTH_SHORT).show();

        }

 return true;
    }


   /* private void addRemovedItemToList() {
        int addItemAtListPosition = 3;

        data.add(addItemAtListPosition, new DataModel(
                MyData.nameArray[removedItems.get(0)],

                MyData.id_[removedItems.get(0)]

        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }*/
}

