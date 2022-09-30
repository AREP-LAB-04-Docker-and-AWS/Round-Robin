package edu.eci.arep.roundRobin.externalServices;

import java.io.IOException;

public interface LogService {

    public String uploadAnStringAndGetLastStrings(String newStringToUpload) throws IOException;
}