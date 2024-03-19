package org.arep.Mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String DATA_BASE= "mongodb://db:27017";
    public static final String BASE_NAME= "logs";

    public static MongoDatabase getDatabase(){
        MongoClient client = MongoClients.create(DATA_BASE);
        return client.getDatabase(BASE_NAME);
    }
}