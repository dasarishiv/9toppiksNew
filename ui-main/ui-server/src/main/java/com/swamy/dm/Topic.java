package com.swamy.dm;

import java.util.ArrayList;

/**
 * Date: 10/26/14
 * Copyrights Reserved
 */
public class Topic extends Base {

    String _id;

    String name;

    String description;

    ArrayList urls;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList getUrls() {
        return urls;
    }

    public void setUrls(ArrayList urls) {
        this.urls = urls;
    }
}
