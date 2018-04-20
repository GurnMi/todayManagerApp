package kr.or.dgit.bigdata.todaymanagerapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private Handler mHandler;
    private String type;
    private String email;

    public HttpRequestTask(Context context, Handler handler, String type, String email) {
        this.mContext = context;
        this.mHandler = handler;
        this.type = type;
        this.email = email;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        HttpURLConnection con = null;
        String line = null;
        try {
            URL url = new URL(strings[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con != null) {
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }
            }
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                con.disconnect();
            } catch (Exception e) {
            }
        }
        Log.d("RESULT DATA", sb.toString());
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        Message message = Message.obtain(mHandler,1,s);
        mHandler.sendMessage(message);
    }
}
