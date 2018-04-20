package kr.or.dgit.bigdata.todaymanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView useridTv;
    String userid;
    Button today_load;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        useridTv = (TextView)findViewById( R.id.userid);
        result = (TextView)findViewById(R.id.result_data);
        today_load = (Button)findViewById(R.id.today_load);
        today_load.setOnClickListener(this);

        Intent intent = getIntent();
        userid = intent.getExtras().getString( "email" );
        useridTv.setText( userid );
    }

    @Override
    public void onClick(View v) {
        String url = "http://192.168.0.9:8080/todayManager/app/user?id=" + userid;
        //new HttpRequestTask().execute(url);
    }
}



