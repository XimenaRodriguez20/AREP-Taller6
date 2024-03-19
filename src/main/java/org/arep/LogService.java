package org.arep;

import com.mongodb.client.MongoDatabase;
import org.arep.Mongodb.LogController;
import org.arep.Mongodb.MongoUtil;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class LogService {


    public static void main(String[] args) {
        port(getPort());


        MongoDatabase database = MongoUtil.getDatabase();
        LogController logControl = new LogController(database);

        logControl.getLogs().forEach(log -> System.out.println(log.toJson()));


        get("apiservice", (req, res) -> {
            String x = req.queryParams("msg");
            res.type("application/json");
            logControl.addLog(x);
            return /*"yes" ;*/ logControl.getLogs();
        });

    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35004;
    }


}