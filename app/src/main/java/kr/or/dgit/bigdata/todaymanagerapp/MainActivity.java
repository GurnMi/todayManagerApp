package kr.or.dgit.bigdata.todaymanagerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AutoText;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private com.kakao.usermgmt.LoginButton btnKakao;
    private SessionCallback callback;
    private Handler mHandler;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        btnKakao = (LoginButton) findViewById(R.id.btn_kakao_login);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String result = (String)msg.obj;
                try{
                    JSONObject arr = new JSONObject(result);
                    if(arr.getString("result").equalsIgnoreCase("true")){
                        Log.d("IF진입","IFIFIFIFIF");
                        Intent intent = new Intent(MainActivity.this,LoginMainActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }else{
                        Log.d("ELSE","ELSEELSEELSEELSE");
                        //회원가입창으로 유도
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        final Intent intent = new Intent(this, MainActivity.class);

        //해쉬키 가져오기!!
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
    }

    public void mOnclick(View view) {
        Intent intent = new Intent();
        intent.setClass( this, TabHostActivity.class );
        // Log.e("email", email);
        startActivity(intent);
        // finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }

        public void requestMe() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSuccess(UserProfile userProfile) {
                    email = userProfile.getEmail();
                    redirectSignupActivity(email);
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {}

                @Override
                public void onNotSignedUp() {}
            });
        }
    }

    protected void redirectSignupActivity(String email) {
        String url = "http://192.168.0.9:8080/todayManager/app/user?id=" + email;
        HttpRequestTask req = new HttpRequestTask(this, mHandler, "GET", email);
        req.execute(url);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**카카오톡 로그아웃 요청**/
        //한번 로그인이 성공하면 세션 정보가 남아있어서 로그인창이 뜨지 않고 바로 onSuccess()메서드를 호출합니다.
        //테스트 하시기 편하라고 매번 로그아웃 요청을 수행하도록 코드를 넣었습니다 ^^
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //로그아웃 성공 후 하고싶은 내용 코딩 ~
                finish();
            }
        });
    }
}
