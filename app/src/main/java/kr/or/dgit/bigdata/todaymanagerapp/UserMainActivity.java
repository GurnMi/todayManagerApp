package kr.or.dgit.bigdata.todaymanagerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kakao.util.helper.log.Logger;

public class UserMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_main );

        Intent intent = getIntent();
        String email = intent.getExtras().getString( "email" );

        Logger.e("profile" ,"intent"+email);

    }


}
