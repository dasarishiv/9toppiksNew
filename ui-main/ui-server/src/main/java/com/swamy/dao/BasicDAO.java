package com.swamy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;
import com.swamy.exception.DBNotAvailableException;
import com.swamy.exception.DataNotSavedException;

/**
 * Date: 10/20/14
 * Copyright 2013 Hewlett-Packard Development Company, L.P
 */
public class BasicDAO {

    private DB db;
    public static Logger logger = Logger.getLogger(BasicDAO.class.getName());
    private static final String ID = "_id";

    public static DB getDB() throws DBNotAvailableException
    {
        int retryCount = 0;
        do{
            try
            {
                return getMongo().getDB("mydb");
            }
            catch(Exception e){
                logger.log(Level.SEVERE,"DB Connection cannot be created..retrying " + retryCount,e);
                retryCount ++;
                mongo = null; //setting it to null, so next time when calling getMongo, it will try to create the mono again
            }
        }while(retryCount < 5);

        throw new DBNotAvailableException("DB Connection cannot be created");
    }

    private static Mongo mongo;

    public static Mongo getMongo() throws DBNotAvailableException
    {
        if(mongo == null)
        {
            try
            {
                String mongodb_urls = System.getenv("mongoservers");
                int port = 27017;
                String servers[] = mongodb_urls.split(";");
                List<ServerAddress> addrs = new ArrayList<ServerAddress>();
                for(String server : servers) {
                    addrs.add(new ServerAddress(server, port));
                }
                mongo = new Mongo( addrs);
                return mongo;
            }
            catch (Exception e) {
                logger.log(Level.SEVERE,"DB Connection cannot be created", e);
                throw new DBNotAvailableException("DB Connection cannot be created");
            }
        }

        return mongo;
    }

    public String toJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public void save(String collection, Object object) throws DBNotAvailableException {
        DBCollection col = getDB().getCollection(collection);
        BasicDBObject obj = (BasicDBObject)JSON.parse(toJson(object));
        col.save(obj,WriteConcern.SAFE);
    }

    public void update(String collection, Object id, Object object) throws DBNotAvailableException, DataNotSavedException {
        try {
            BasicDBObject idObj = new BasicDBObject();
            idObj.append("_id", id);
            DBCollection col = getDB().getCollection(collection);
            col.update(idObj, (BasicDBObject)JSON.parse(toJson(object)));
        }
        catch (Exception e) {
            throw new DataNotSavedException(e);
        }
    }

    public void delete(String collection, Object id) throws DBNotAvailableException {
        BasicDBObject object = new BasicDBObject();
        object.append(ID, id);
        getDB().getCollection(collection).remove(object);
    }


}
