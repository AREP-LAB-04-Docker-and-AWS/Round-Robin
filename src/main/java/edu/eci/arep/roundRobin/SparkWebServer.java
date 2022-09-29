package edu.eci.arep.roundRobin;

import edu.eci.arep.roundRobin.model.RoundRobin;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class SparkWebServer {

    private static RoundRobin roundRobin;

    public static void main( String[] args ) {
        port(getPort());
        get("/hello", SparkWebServer::getGreeting);

        // Testing RoundRobin
        roundRobin = new RoundRobin(SparkWebServer.getServicePorts());
        System.out.println("Content: " + roundRobin.getContent());
        for (int i = 0; i < 10; i++) {
            System.out.println("Using port: " + roundRobin.getNext());
        }
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static ArrayList<Integer> getServicePorts() {
        return new ArrayList<Integer>(Arrays.asList(1111, 2222, 3333, 4444));
    }

    private static String getGreeting(Request request, Response response) {
        response.type("text/html");
        return "Hello, Spark!";
    }
}