package com.swamy.dm;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;

import java.util.List;


public class RandomPattern extends Base
{

    String id;
    Integer number;
    List<String> arr;
    Integer indexs[];

    public RandomPattern() {
        this.id = new Integer(1).toString();
    }

    public List<String> getArr() {
        return arr;
    }
    public void setArr(List<String> arr) {
        this.arr = arr;
    }
    public Integer[] getIndexs() {
        return indexs;
    }
    public void setIndexs(Integer[] indexs) {
        this.indexs = indexs;
    }
    public void setId(Object id) {
        //Not other ID for the randompattern object except '1'
        this.id = new Integer(1).toString();
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
