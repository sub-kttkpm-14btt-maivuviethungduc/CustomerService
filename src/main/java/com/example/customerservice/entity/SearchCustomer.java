package com.example.customerservice.entity;

public class SearchCustomer {
   String customerId;
   String name;
   public  SearchCustomer(String customerId, String name){
       this.customerId= customerId;
       this.name= name;
   }


    public String getCustomerId(){ return  customerId;}
    public String getName(){ return  name;}
}
