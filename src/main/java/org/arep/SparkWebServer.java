package org.arep;

import com.mongodb.client.MongoDatabase;
import org.arep.Mongodb.LogController;
import org.arep.Mongodb.MongoUtil;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String[] args) {
        port(getPort());

        staticFileLocation("public");

        MongoDatabase database = MongoUtil.getDatabase();
        LogController logsService = new LogController(database);

        // Create a new user
        logsService.addLog("John Doe");

        // List users
        logsService.getLogs().forEach(log -> System.out.println(log.toJson()));

        // Update user
        logsService.addLog("Jordy");

        logsService.getLogs().forEach(log -> System.out.println(log.toJson()));


        get("logs", (req, res) -> {
            String x = req.queryParams("msg");
            res.type("application/json");
            logsService.addLog(x);
            return /*"yes" ;*/ logsService.getLogs();
        });

    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }




}