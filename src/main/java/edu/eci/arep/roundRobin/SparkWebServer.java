package edu.eci.arep.roundRobin;

import edu.eci.arep.roundRobin.externalServices.impl.LogServiceImpl;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static spark.Spark.*;

public class SparkWebServer {

    private static final LogServiceImpl logService;

    static {
        logService = new LogServiceImpl();
    }

    public static void main( String[] args ) {
        port(getPort());
        get("/get/hello", SparkWebServer::getGreeting);
        get("/get/strings", SparkWebServer::uploadAnStringAndGetLastStrings);
        get("/get/lastStrings", SparkWebServer::getHistoryOfLastSavedStrings);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String getGreeting(Request request, Response response) {
        response.type("text/html");
        return "Hello, Spark!";
    }

    private static String uploadAnStringAndGetLastStrings(Request request, Response response) {
        response.type("application/json");
        try {
            String newStringToUpload = request.queryParams("newString");
            return logService.uploadAnStringAndGetLastStrings(newStringToUpload);
        } catch (IOException e) {
            e.printStackTrace();
            return "{error: " + e.getMessage() + "}";
        }
    }

    private static String getHistoryOfLastSavedStrings(Request request, Response response) {
        response.type("application/json");
        try {
            String stringsQty = request.queryParams("stringsQty");
            return logService.getHistoryOfLastSavedStrings(stringsQty);
        } catch (IOException e) {
            e.printStackTrace();
            return "{error: " + e.getMessage() + "}";
        }
    }
}