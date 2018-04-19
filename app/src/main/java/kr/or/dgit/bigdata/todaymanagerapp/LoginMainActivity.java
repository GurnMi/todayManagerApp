package kr.or.dgit.bigdata.todaymanagerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kr.or.dgit.bigdata.todaymanagerapp.domain.UserVO;

public class LoginMainActivity extends AppCompatActivity {
    TextView useridTv;
    TextView test;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_main );

        useridTv = (TextView)findViewById( R.id.userid );
        test = (TextView)findViewById( R.id.test );



        Intent intent= getIntent();
        userid =intent.getExtras().getString( "email" );
        String test1 =intent.getExtras().getString( "test" );
        //Log.e( "email", email);
        useridTv.setText( userid );
        test.setText( test1 );
       // requestMe();


    }

   /* private class ParsingXml extends AsyncTask<String, Void, ArrayList<UserVO>> {

        @Override
        protected ArrayList<UserVO> doInBackground(String... strings) {
            StringBuffer sb = new StringBuffer();
            String str = null;
            try {
                BufferedReader fis = new BufferedReader(new InputStreamReader(openFileInput(params[0])));
                while ((str = fis.readLine()) != null) {
                    sb.append(str);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
           return parsingXml(sb.toString());

        }
    }*/




}



