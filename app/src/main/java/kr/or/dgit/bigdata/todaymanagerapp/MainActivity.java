package kr.or.dgit.bigdata.todaymanagerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private Context mContext;



   // private Button btn_custom_login;

   // private LoginButton btn_kakao_login;

    private com.kakao.usermgmt.LoginButton btnKakao;
    private SessionCallback callback;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mContext = getApplicationContext();

        callback = new SessionCallback(); //세션콜백을 부르고
        Session.getCurrentSession().addCallback(callback); //추가
        //Session.getCurrentSession().checkAndImplicitOpen();

        btnKakao = (LoginButton) findViewById(R.id.btn_kakao_login);


        final Intent intent = new Intent(this, MainActivity.class);


        /**카카오톡 로그아웃 요청**/
        //한번 로그인이 성공하면 세션 정보가 남아있어서 로그인창이 뜨지 않고 바로 onSuccess()메서드를 호출합니다.
        //테스트 하시기 편하라고 매번 로그아웃 요청을 수행하도록 코드를 넣었습니다 ^^
      /* UserManagement.requestLogout( new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //로그아웃 성공 후 하고싶은 내용 코딩 ~

                startActivity(intent);
                finish();
            }
        });*/



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




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    public void mOnclick(View view) {
        Intent intent = new Intent();
        intent.setClass( this, TabHostActivity.class );
//        Log.e( "email", email);
        startActivity(intent);
       // finish();

    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }



        public void requestMe() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.e("SessionCallback :: ", "onSuccess");

                    String nickname = userProfile.getNickname();

                    String email = userProfile.getEmail();

                    String profileImagePath = userProfile.getProfileImagePath();

                    String thumnailPath = userProfile.getThumbnailImagePath();

                    String UUID = userProfile.getUUID();

                    long id = userProfile.getId();

                    Log.e("Profile : ", nickname + "");

                    Log.e("Profile : ", email + "");

                    Log.e("Profile : ", profileImagePath  + "");

                    Log.e("Profile : ", thumnailPath + "");

                    Log.e("Profile : ", UUID + "");

                    Log.e("Profile : ", id + "");

                    redirectSignupActivity(email);
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }
            });

        }



    }

    protected void redirectSignupActivity(String email) {
        final Intent intent = new Intent(this, LoginMainActivity.class);
        intent.putExtra( "email", email );
        intent.putExtra( "test", "test" );
//        Log.e( "email", email);
        startActivity(intent);
        finish();
    }



}
