package edu.eci.arep.roundRobin;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main( String[] args ) {
        port(getPort());
        get("/hello", SparkWebServer::getGreeting);
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
}