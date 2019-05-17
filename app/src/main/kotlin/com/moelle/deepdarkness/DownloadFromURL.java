package com.moelle.deepdarkness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFromURL extends AsyncTask<String, String, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... fileUrl) {

        int count;
        try {
            URL url = new URL(fileUrl[0]);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            // show progress bar 0-100%
            int fileLength = urlConnection.getContentLength();
            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
            OutputStream outputStream = new FileOutputStream("/sdcard/downloadedfile.jpg");

            byte data[] = new byte[1024];
            long total = 0;
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / fileLength));
                outputStream.write(data, 0, count);
            }
            // flushing output
            outputStream.flush();
            // closing streams
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String file_url) {
    }
}

