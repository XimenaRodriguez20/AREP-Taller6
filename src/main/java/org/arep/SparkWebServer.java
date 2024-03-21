package org.arep;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.*;

public class SparkWebServer {

    private static final List<String> urls = Arrays.asList(
            "http://service1:35001/apiservice?msg=",
            "http://service2:35002/apiservice?msg=",
            "http://service3:35003/apiservice?msg=");
    //private static final List<String> urls = List.of("http://localhost:35001/apiservice?msg=");
    private static int contLogs = 0;
    public static void main(String[] args) {
        port(getPort());

        staticFileLocation("public");

        get("apiclient", (req, res) -> {
            String mensaje = req.queryParams("msg");
            res.type("application/json");
            URL path = getLogServiceUrl(mensaje);
            return /*"yes" ;*/ HttpConection.ResponseRequest(path);
        });

    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

    private static URL getLogServiceUrl(String mensaje) throws MalformedURLException {
        // Get the URL for the current index
        String getUrl = urls.get(contLogs);
        // Increment the index and wrap it around if it reaches the end of the list
        contLogs = (contLogs + 1) % urls.size();
        return new URL(getUrl + mensaje);
    }

}