package kr.or.dgit.bigdata.todaymanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class JoinActivity extends AppCompatActivity {
    EditText userid;
    EditText usernick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join );

        //이메일 수정불가 처리
        userid = findViewById( R.id.userid );


        usernick = findViewById( R.id.usernick );
    }


   /* //회원가입 버튼
    public void mOnclick(View view) {
       *//* String id =userid.getText().toString();
        String nick = usernick.getText().toString();

        //닉네임 미 입력처리
        if(usernick.getText().toString().length()==0){
            Toast.makeText( this,"닉네임을 입력해주세요.",Toast.LENGTH_LONG ).show();
            return;
        }
*//*

    }*/
}
