package com.example.adya.resourcehandle;

/**
 * Created by Adya on 04-07-2018.
 */

class DataModel {

    String empname;

   String itemid;


   /* public DataModel(String empname,String itemid) {
        this.empname=empname;
        this.itemid=itemid;


    }*/
    public void  setEmpname(String empname){this.empname=empname;}

    public String getEmpname() {
        return empname;
    }


    public void setItemid(String itemid){this.itemid=itemid;}
    public String getItemid() {
        return itemid;
    }
}

