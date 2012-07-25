package kr.actus;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;

/*
 중간에서 5개의 액티비티를 총괄해서 조정해준다.
 */

public class MainActivity extends TabActivity {

      @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, Splash.class));    
        //TabHost 객체 얻어오기

        TabHost tabHost = getTabHost();

       //첫번째 탭 구성

        Drawable tab1 = getResources().getDrawable(R.drawable.icon_list);
        tabHost.addTab(tabHost.newTabSpec("tag1").

                setIndicator("경기일정", tab1).

                setContent(new Intent(this, Schedule.class)));

   
        //두번째 탭 구성
        Drawable tab2 = getResources().getDrawable(R.drawable.icon_player);
        tabHost.addTab(tabHost.newTabSpec("tag2").

                setIndicator("종목소개", tab2).

                setContent(new Intent(this,GameIntro.class)));
        
        //세번째 탭 구성
        Drawable tab3 = getResources().getDrawable(R.drawable.icon_ground);
        tabHost.addTab(tabHost.newTabSpec("tag3").

                setIndicator("경기장정보", tab3).

                setContent(new Intent(this, GameInfo.class)));
        
        //네번째 탭 구성
        Drawable tab4 = getResources().getDrawable(R.drawable.icon_game);
        tabHost.addTab(tabHost.newTabSpec("tag4").

                setIndicator("게임", tab4).

                setContent(new Intent(this, Games.class)));
        
        //다섯번째 탭 구성
        Drawable tab5 = getResources().getDrawable(R.drawable.icon_set);
        tabHost.addTab(tabHost.newTabSpec("tag5").

                setIndicator("만든이", tab5).

                setContent(new Intent(this, Setting.class)));


        

    }

        

}


