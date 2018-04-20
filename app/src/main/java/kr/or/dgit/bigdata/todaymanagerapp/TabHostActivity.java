package kr.or.dgit.bigdata.todaymanagerapp;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

public class TabHostActivity extends AppCompatActivity {
    private LinearLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tab_host );







        TabHost host = (TabHost)findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_today,null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("Tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_diary,null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("Tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_chart,null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

       /* spec = host.newTabSpec("Tab4");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_today,null));
        spec.setContent(R.id.tab_content4);
        host.addTab(spec);*/

        root = findViewById(R.id.tab_content1);
    }

    //메뉴세팅
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );

        return true;
    }

    //메뉴선택
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Toast.makeText( getApplicationContext(),"logout", Toast.LENGTH_LONG).show();
                break;
            case R.id.mypage:
                Toast.makeText( getApplicationContext(),"mypage", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected( item );
    }

    public void setOrientationHorizontal(View view) {
        if(root.getOrientation()==LinearLayout.VERTICAL) {
            root.setOrientation(LinearLayout.HORIZONTAL);
        }
    }

    public void setOrientationVertical(View view) {
        if(root.getOrientation()==LinearLayout.HORIZONTAL) {
            root.setOrientation(LinearLayout.VERTICAL);
        }
    }
}
