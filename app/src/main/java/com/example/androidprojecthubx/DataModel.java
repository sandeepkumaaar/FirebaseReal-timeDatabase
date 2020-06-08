package com.example.androidprojecthubx;

public class DataModel {

    private String id, Name, Email_id, Mobile_number, Number_of_People, Address, Event_Category;

    public DataModel(){

    }

    public DataModel(String id, String name, String email_id, String mobile_number, String number_of_People, String address, String event_Category) {
        this.id = id;
        Name = name;
        Email_id = email_id;
        Mobile_number = mobile_number;
        Number_of_People = number_of_People;
        Address = address;
        Event_Category = event_Category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public String getMobile_number() {
        return Mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        Mobile_number = mobile_number;
    }

    public String getNumber_of_People() {
        return Number_of_People;
    }

    public void setNumber_of_People(String number_of_People) {
        Number_of_People = number_of_People;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEvent_Category() {
        return Event_Category;
    }

    public void setEvent_Category(String event_Category) {
        Event_Category = event_Category;
    }
}