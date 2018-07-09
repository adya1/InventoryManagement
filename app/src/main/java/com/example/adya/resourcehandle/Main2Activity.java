package com.example.adya.resourcehandle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
        TextView ename, eemail, eid;
        Button save, view;
        int pos;

        FirebaseDatabase database;
        DatabaseReference myRef;
        List<ListData> list;
        RecyclerView recyclerview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main2);
                Intent i = getIntent();
                pos = i.getIntExtra("position", 0);
              //  Toast.makeText(getApplicationContext(), "Item position clicked " + String.valueOf(pos), Toast.LENGTH_SHORT).show();

                ename = (TextView) findViewById(R.id.etname);
                eemail = (TextView) findViewById(R.id.eemail);
                eid = (TextView) findViewById(R.id.id);
                save = (Button) findViewById(R.id.save);
                view = (Button) findViewById(R.id.view);
                recyclerview = (RecyclerView) findViewById(R.id.rview);
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("message").child("Pc"+String.valueOf(pos));

        save.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        String name =  ename.getText().toString();
        String email =  eemail.getText().toString();
        String id =eid.getText().toString();


        String key =myRef.push().getKey();
        Userdetails userdetails = new Userdetails();

        userdetails.setName(name);
        userdetails.setEmail(email);
        userdetails.setId(id);

        myRef.child(key).setValue(userdetails);
        ename.setText("");
        eemail.setText("");
       eid.setText("");

        }
        });

        /*RecyclerviewAdapter recycler = new RecyclerviewAdapter(list);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Main2Activity.this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator( new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);*/


        view.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {


        myRef.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        list = new ArrayList<>();
        // StringBuffer stringbuffer = new StringBuffer();
        if(dataSnapshot.child("PC"+String.valueOf(pos))==null){Toast.makeText(getApplicationContext(),"Item Available",Toast.LENGTH_SHORT).show();}
        else{
        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()) {

                Userdetails userdetails = dataSnapshot1.getValue(Userdetails.class);

                ListData listdata = new ListData();
                String name = userdetails.getName();
                String email = userdetails.getEmail();
                String id = userdetails.getId();
                listdata.setName(name);
                listdata.setEmail(email);
                listdata.setId(id);
                list.add(listdata);
                // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Item Not Available", Toast.LENGTH_SHORT).show();

        }
        }
        RecyclerviewAdapter recycler = new RecyclerviewAdapter(list);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Main2Activity.this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator( new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);


        }

@Override
public void onCancelled(DatabaseError error) {
        // Failed to read value
        //  Log.w(TAG, "Failed to read value.", error.toException());
        }
        });

        }
        });


        }
        }
