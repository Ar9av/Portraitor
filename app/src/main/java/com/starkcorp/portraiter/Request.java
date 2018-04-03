package com.starkcorp.portraiter;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anirudh on 6/28/2017.
 */
public class Request {
    private static final String TAG = Request.class.getSimpleName();
    public static String post(String Urlstring, String DatatoSend){
        try{

            URL url=new URL(Urlstring);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(20000);
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bufferedWriter.write(DatatoSend);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            int response=httpURLConnection.getResponseCode();
            if(response== HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder=new StringBuilder();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            }else{
                Log.e(TAG,"ERROR - Invalid response code from server "+ response);
                return null;
            }


        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG,"ERROR "+e);
            return null;
        }
    }
}
