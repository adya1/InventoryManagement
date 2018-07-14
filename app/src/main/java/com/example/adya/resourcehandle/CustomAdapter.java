package com.example.adya.resourcehandle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adya on 04-07-2018.
 */

 public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    public ArrayList<DataModel> dataSet, filterList;
    CustomFilter filter;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private Context cntxt;

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
        this.filterList=data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textView2;
        ImageView iv;
        /*TextView TextView;
        ImageView imageViewIcon;*/

        // private final Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            cntxt = itemView.getContext();
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textView2 = (TextView) itemView.findViewById(R.id.textview2);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            //   itemView.setClickable(true);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Main2Activity.class);
                    intent.putExtra("position",getAdapterPosition());
                    context.startActivity(intent);


                }
            });*/


            /*this.TextView = (TextView) itemView.findViewById(R.id.TextView);*/
//            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }


    }

    ;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final TextView textViewName = holder.textViewName;
        final TextView textView2 = holder.textView2;

        final ImageView options = holder.iv;
        holder.iv.setTag(listPosition);
        //textViewName.setText(dataSet.get(listPosition).getName() + " " + dataSet.get(listPosition).getId());
        // textView2.setText("Available");
        textViewName.setText(dataSet.get(listPosition).getItemid());
        textView2.setText(dataSet.get(listPosition).getEmpname());
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final int position = (int) view.getTag();
                //creating a popup menu
                PopupMenu popup = new PopupMenu(cntxt, options);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            // get prompts.xml view


                            case R.id.menu3: {
                                LayoutInflater li = LayoutInflater.from(cntxt);
                                View promptsView = li.inflate(R.layout.promptpass, null);

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        cntxt);

                                // set prompts.xml to alertdialog builder
                                alertDialogBuilder.setView(promptsView);
                                final TextInputLayout passinputWrapper=(TextInputLayout)promptsView.findViewById(R.id.passinputWrapper);

                                final EditText passinput = (EditText) promptsView
                                        .findViewById(R.id.passinput);
                                passinputWrapper.setHint("Enter Password");

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // get user input and set it to result
                                                        // edit text
                                                        if (passinput.getText().toString().equalsIgnoreCase("bel@123")) {
                                                            Toast.makeText(cntxt, "Item deleted", Toast.LENGTH_SHORT).show();
                                                            DataModel dm = new DataModel();

                                                            dm = dataSet.get(position);
                                                            final String item = dm.getItemid();
                                                            database = FirebaseDatabase.getInstance();
                                                            myRef = database.getReference("message");
                                                            myRef.orderByChild("itemid").equalTo(item).addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                                        String key = dataSnapshot1.getKey();
                                                                        dataSnapshot1.getRef().removeValue();


                                                                    }


                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });

                                                            dataSet.remove(position);
                                                            notifyItemRemoved(position);
                                                        } else
                                                            Toast.makeText(cntxt, "Wrong Password,you cannot delete", Toast.LENGTH_LONG).show();
                                                    }
                                                })
                                        .setNegativeButton("Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();

                                // show it
                                alertDialog.show();


                            }

                            break;

                            //handle menu2 click

                            case R.id.menu2: {
                                LayoutInflater li = LayoutInflater.from(cntxt);
                                View promptsView = li.inflate(R.layout.promptpass, null);

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        cntxt);

                                // set prompts.xml to alertdialog builder
                                alertDialogBuilder.setView(promptsView);
                                final TextInputLayout passinputWrapper=(TextInputLayout)promptsView.findViewById(R.id.passinputWrapper);

                                final EditText passinput = (EditText) promptsView
                                        .findViewById(R.id.passinput);
                                passinputWrapper.setHint("Enter Password");

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // get user input and set it to result
                                                        // edit text
                                                        if (passinput.getText().toString().equalsIgnoreCase("bel@123")) {
                                                            addemp();
                                                        } else
                                                            Toast.makeText(cntxt, "Wrong Password,you cannot update", Toast.LENGTH_LONG).show();
                                                    }
                                                })
                                        .setNegativeButton("Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();

                                // show it
                                alertDialog.show();


                            }

                            break;
                        }
                        return false;
                    }

                    private void addemp() {
                        final int position = (int) view.getTag();
                        LayoutInflater li = LayoutInflater.from(cntxt);
                        View promptsView = li.inflate(R.layout.prompt, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                cntxt);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);
                        final TextInputLayout userinputWrapper=(TextInputLayout)promptsView.findViewById(R.id.editTextDialogUserInputWrapper);

                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.editTextDialogUserInput);
                        userinputWrapper.setHint("Enter Employee Name");

                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // get user input and set it to result
                                                // edit text
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                final String currentDateTime = dateFormat.format(new Date()); // Find todays date

                                                textView2.setText(userInput.getText() + " at " + currentDateTime);

                                                DataModel dm = new DataModel();
                                                dm = dataSet.get(position);
                                                final String item = dm.getItemid();
                                                database = FirebaseDatabase.getInstance();
                                                myRef = database.getReference("message");
                                                myRef.orderByChild("itemid").equalTo(item).addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                            String key = dataSnapshot1.getKey();
                                                            myRef.child(key).child("empname").setValue(userInput.getText() + " at " + currentDateTime);

                                                        }


                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }
}

