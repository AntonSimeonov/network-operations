package ninja.paranoidandroid.networkoperations;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import ninja.paranoidandroid.networkoperations.network.ServerConnection;
import ninja.paranoidandroid.networkoperations.network.URLReader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkAsyncOperations networkOperations = new NetworkAsyncOperations();
        networkOperations.execute("http://www.google.com/");
    }


    private class NetworkAsyncOperations extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String urlAddress = strings[0];
            String result = null;

            result = readWebPage(urlAddress);
            //result = getServerResponce(urlAddress);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("MainActivity tag: ", s);
        }
    }


    private String readWebPage(String urlAddress){

        String result = null;
        URLReader urlReader = new URLReader();
        urlReader.setConnection(urlAddress);
        result = urlReader.getWebPageContent();

        return result;
    }

    private String getServerResponce(String urlAddress, String postArguments){

        String result = null;

        ServerConnection serverConnection = new ServerConnection();
        serverConnection.setConnection(urlAddress);
        serverConnection.setPostRequestHeader();
        serverConnection.sendPostRequest(postArguments);
        result = serverConnection.getServerResponce();

        return result;
    }
}
