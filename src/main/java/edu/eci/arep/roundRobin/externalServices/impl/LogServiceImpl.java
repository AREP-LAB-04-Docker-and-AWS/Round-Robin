package edu.eci.arep.roundRobin.externalServices.impl;

import edu.eci.arep.roundRobin.model.RoundRobin;
import edu.eci.arep.roundRobin.externalServices.LogService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class LogServiceImpl implements LogService {

    private final String HOST;
    private final RoundRobin roundRobin;

    {
        HOST = "http://localhost";
        roundRobin = new RoundRobin(getServicePorts());
    }

    @Override
    public String uploadAStringAndGetLastStrings(String newStringToUpload) throws IOException {
        final String PATH = "/get/strings";
        // Creating request
        URL requestUrl = new URL( HOST + ":" + roundRobin.getNext() + PATH + "?newString=" + newStringToUpload);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        // Setting request method
        connection.setRequestMethod("GET");
        // Setting request header(s)
        connection.setRequestProperty("content-type", "application/json");
        // reading response
        StringBuilder response = new StringBuilder();
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        // Closing
        input.close();
        connection.disconnect();

        return response.toString();
    }

    @Override
    public String getHistoryOfLastSavedStrings(String stringsQty) throws IOException {
        final String PATH = "/get/lastStrings";
        // Creating request
        URL requestUrl = new URL( HOST + ":" + roundRobin.getNext() + PATH + "?stringsQty=" + stringsQty);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        // Setting request method
        connection.setRequestMethod("GET");
        // Setting request header(s)
        connection.setRequestProperty("content-type", "application/json");
        // reading response
        StringBuilder response = new StringBuilder();
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        // Closing
        input.close();
        connection.disconnect();

        return response.toString();
    }

    private ArrayList<Integer> getServicePorts() {
        return new ArrayList<>(Arrays.asList(35001, 35002, 35003));
    }
}