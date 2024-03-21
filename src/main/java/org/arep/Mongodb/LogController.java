package org.arep.Mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogController {
    private final MongoCollection<Document> logsFile;

    public LogController(MongoDatabase base) {
        logsFile = base.getCollection(MongoUtil.BASE_NAME);
        logsFile.find().forEach(i -> logsFile.deleteOne(i));
    }

    public boolean addLog (String logName) {
        try {
            Document doc = new Document("Log", logName).append("Hour", LocalDateTime.now());
            logsFile.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Document> getLogs() {
        FindIterable<Document> allLogs = logsFile.find().sort(Sorts.descending("Hour")).limit(10);
        List<Document> logsList = new ArrayList<>();
        allLogs.forEach(logsList::add);
        logsList.forEach(System.out::println);

        return logsList;
    }

    public List<String> getLogsString() {
        List<Document> logsList = getLogs();
        List<String> respuesta = new ArrayList<>();
        logsList.forEach(i -> respuesta.add("{\"Log\":\"" + i.get("Log") + "\", \"Hour\":\"" + i.get("Hour") + "\"}"));

        return respuesta;
    }
}