package com.example.foodman;

import java.io.Serializable;

public class Report implements Serializable {
    String _name;
    String data;
    public Report(){}
    public Report(String name, String data){

        this._name = name;
        this.data = data;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data){
        this.data = data;
    }
}