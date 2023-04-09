package com.example.child_tracker;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFileWriter {

    private static final String TAG = "JsonFileWriter";
    private static final String FILE_NAME = "my_data2.json";



    public static void writeJsonArrayToFile(Context context, ArrayList<MyData> myDataArray) {
        // Create a JSONArray and add your data to it
        JSONArray jsonArray = new JSONArray();
        for (MyData myData : myDataArray) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", myData.getName());
                jsonObject.put("time", myData.getTime());
            } catch (JSONException e) {
                Log.e(TAG, "Error creating JSON object", e);
                return;
            }
            jsonArray.put(jsonObject);
        }
        Log.d("Writing data",jsonArray.toString());
        // Write the JSON array to a file
        File file = new File(context.getFilesDir(), FILE_NAME);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            Log.e(TAG, "Error writing JSON to file", e);
            return;
        }
    }
}
