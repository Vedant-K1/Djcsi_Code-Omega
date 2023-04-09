package com.example.child_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    TextInputLayout t1;
    String text;
    ArrayList<MyData> myDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.b1_login);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Your Tracking has Started!! Now your child is Safe :)", Toast.LENGTH_SHORT).show();
                if (checkUsageStatsPermission()) {
                    // Get the usage statistics
                    List<UsageStats> usageStatsList = getUsageStatistics();
                    // Process the usage statistics as needed
                    for (UsageStats usageStats : usageStatsList) {
                        String packageName = usageStats.getPackageName();
                        long totalTimeInForeground = usageStats.getTotalTimeInForeground()/1000;

                        // Display the package name and total time in the TextView
                        text = packageName + ": " + totalTimeInForeground + " seconds";
                        try {
                            JSONObject jsonObject = new JSONObject();
                            String[] parts = text.split(": ");
                            jsonObject.put("name", parts[0]);
                            jsonObject.put("seconds", parts[1].replace(" seconds", ""));
                            String jsonText = jsonObject.toString();
                            if(totalTimeInForeground > 5){
//                                Log.d("string tag",jsonText);
                                String n = appname(jsonText);
                                String t = time_stamp(jsonText);
                                myDataList.add(new MyData(n,t));
                                }
//                                Log.d("string tag",jsonText);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    JsonFileWriter jsonFileWriter = new JsonFileWriter();
                    jsonFileWriter.writeJsonArrayToFile(getApplicationContext(), myDataList);
                    ArrayList<MyData> myDataList = JsonFileReader.readJsonFromFile(getApplicationContext());


                } else {
                    Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                    startActivity(intent);
                    // Request permission to access usage statistics
                    // (you need to handle this case yourself)
                }
                Log.d("final data",myDataList.toString());
                Intent intent2 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent2);
            }
        });


    }
    private boolean checkUsageStatsPermission() {
        // Add the permission to the manifest file first
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }


    private List<UsageStats> getUsageStatistics() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        long start = calendar.getTimeInMillis();
        long end = System.currentTimeMillis();

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end);
        return usageStatsList;
    }

    private String appname(String jsonText) {
        String appName2 = "";
        try {
            JSONObject jsonObject2 = new JSONObject(jsonText.substring(jsonText.indexOf("{"), jsonText.lastIndexOf("}") + 1));
            String appName = jsonObject2.getString("name");
            Log.d("string package", appName);
            String packageName2 = appName;

            if (packageName2.startsWith("com.")) {
                packageName2 = packageName2.substring(4);
            }
            if (packageName2.contains(".")) {
                String[] parts2 = packageName2.split("\\.");
                for (String part : parts2) {
                    appName2 += part.substring(0, 1).toUpperCase() + part.substring(1);
                }
            } else {
                appName2 = packageName2.substring(0, 1).toUpperCase() + packageName2.substring(1);
            }
            if (appName2.equals("Android")) {
                appName2 = "Android System";
            } else if (appName2.equals("Googleplay")) {
                appName2 = "Google Play";
            }
//            Log.d("string name", appName2);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return appName2;
    }
    private String time_stamp(String jsonText) {
        String appName="";
        try {
            JSONObject jsonObject2 = new JSONObject(jsonText.substring(jsonText.indexOf("{"), jsonText.lastIndexOf("}") + 1));
            appName = jsonObject2.getString("seconds");
//            Log.d("string time", appName);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return appName;
    }
}