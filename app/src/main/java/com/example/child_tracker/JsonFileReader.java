package com.example.child_tracker;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonFileReader {

    private static final String TAG = "JsonFileReader";
    private static final String FILE_NAME = "my_data2.json";

    public static ArrayList<MyData> readJsonFromFile(Context context) {

        ArrayList<MyData> myDataList = new ArrayList<>();

        // Read the JSON data from the file
        File file = new File(context.getFilesDir(), FILE_NAME);
        String jsonString = readFromFile(file);
        if (jsonString == "") {
//            Log.e(TAG, "Error reading JSON from file");
            return myDataList;
        }

        // Parse the JSON data into an ArrayList<MyData>
        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String time = jsonObject.getString("time");
                MyData myData = new MyData(name, time);
                myDataList.add(myData);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON data", e);
        }

        return myDataList;
    }

    private static String readFromFile(File file) {

        String jsonString = "";

        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            try {
                JSONArray jsonArray = new JSONArray(line);
                Log.d("json array","hello "+line);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String time = jsonObject.getString("time");
                    Log.d("line data", "Name: " + name + ", Time: " + time);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            while ((line = bufferedReader.readLine()) != null) {
//                Log.d("line data",line);
//                stringBuilder.append(line);
//            }
            jsonString = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found", e);
        } catch (IOException e) {
            Log.e(TAG, "Error reading from file", e);
        }

        return jsonString;
    }
}
