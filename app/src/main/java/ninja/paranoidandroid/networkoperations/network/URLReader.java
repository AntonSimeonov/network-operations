package ninja.paranoidandroid.networkoperations.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anton on 30.11.16.
 */

public class URLReader {

    private final static String TAG = "URLreader";
    private HttpURLConnection mConnection;
    private String mUrlAddress;


    public void setConnection(String urlAddress){

        mUrlAddress = urlAddress;
        URL urlObj = null;

        try {

            urlObj = new URL(mUrlAddress);
            mConnection = (HttpURLConnection) urlObj.openConnection();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getWebPageContent(){


        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        String inputLine = null;

        try {

            int responseCode = mConnection.getResponseCode();

            if(responseCode == 200){
                Log.i(TAG, "RESPONCE CODE IS 200");

                reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));

                while ((inputLine = reader.readLine()) != null) {

                    response.append(inputLine);

                }

            }else if(responseCode == 400){
                Log.i(TAG, "RESPONCE CODE IS 400");

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            mConnection.disconnect();
        }
        return response.toString();
    }

}
