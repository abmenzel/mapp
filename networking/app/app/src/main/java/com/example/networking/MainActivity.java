package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. When the activity is created, create a WebView component and load the url google.com
        //    Also start the networking activity with our database at Repl.co
        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://google.com");

        startNetworking();
    }

    // 2. Our networking activity creates a FetchJob and pass it to a new thread we create to do this background task
    //    this allows us to do the job in the background, and let other important jobs run on the main thread.
    private synchronized void startNetworking(){
        FetchJob fetchJob = new FetchJob("https://gbserver.abmenzel.repl.co/");
        new Thread(fetchJob).start();
    }

    private class FetchJob implements Runnable{

        String url;

        public FetchJob(String url){
            this.url = url;
        }

        // 4. Without going into detail.
        // We establish a connection with the database.
        // We get the bytes from the database, read all the bytes and return the byte array.
        public byte[] getUrlBytes(String urlSpec) throws IOException {
            System.out.println("Getting bytes from " + url + " ...");
            URL url = new URL(urlSpec);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = connection.getInputStream();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException(connection.getResponseMessage() +
                            ": with " +  urlSpec);
                }
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                return out.toByteArray();
            } finally {
                connection.disconnect();
            }
        }

        // 5. We turn the bytes into a string, which we turn into a json array.
        //    Then we print out every json object from the array.
        public void printParse (byte[] bytes) throws JSONException {
            System.out.println("Printing json ... ");
            JSONArray json = new JSONArray(new String(bytes));
            for(int i = 0; i < json.length(); i++){
                System.out.println(json.get(i).toString());
            }
        }

        // 3. Our fetch job starts by getting all of the response bytes from the url provided.
        //    these bytes are passed to the printParse function, which turns the bytes into a json format.
        public void run(){
            try {
                System.out.println("Running ...");
                printParse(getUrlBytes(url));
            }catch(IOException e) {
                System.out.println("Error IO");
            }catch(JSONException je){
                System.out.println("Error JSON");
            }
        }
    }
}