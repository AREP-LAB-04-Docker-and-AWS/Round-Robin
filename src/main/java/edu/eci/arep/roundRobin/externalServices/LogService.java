package edu.eci.arep.roundRobin.externalServices;

import java.io.IOException;

public interface LogService {

    String uploadAStringAndGetLastStrings(String newStringToUpload) throws IOException;

    String getHistoryOfLastSavedStrings(String stringsQty) throws IOException;
}