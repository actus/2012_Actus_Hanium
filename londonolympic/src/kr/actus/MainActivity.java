package kr.actus;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;

/*
 �߰����� 5���� ��Ƽ��Ƽ�� �Ѱ��ؼ� �������ش�.
 */

public class MainActivity extends TabActivity {

      @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, Splash.class));    
        //TabHost ��ü ������

        TabHost tabHost = getTabHost();

       //ù��° �� ����

        Drawable tab1 = getResources().getDrawable(R.drawable.icon_list);
        tabHost.addTab(tabHost.newTabSpec("tag1").

                setIndicator("�������", tab1).

                setContent(new Intent(this, Schedule.class)));

   
        //�ι�° �� ����
        Drawable tab2 = getResources().getDrawable(R.drawable.icon_player);
        tabHost.addTab(tabHost.newTabSpec("tag2").

                setIndicator("����Ұ�", tab2).

                setContent(new Intent(this,GameIntro.class)));
        
        //����° �� ����
        Drawable tab3 = getResources().getDrawable(R.drawable.icon_ground);
        tabHost.addTab(tabHost.newTabSpec("tag3").

                setIndicator("���������", tab3).

                setContent(new Intent(this, GameInfo.class)));
        
        //�׹�° �� ����
        Drawable tab4 = getResources().getDrawable(R.drawable.icon_game);
        tabHost.addTab(tabHost.newTabSpec("tag4").

                setIndicator("����", tab4).

                setContent(new Intent(this, Games.class)));
        
        //�ټ���° �� ����
        Drawable tab5 = getResources().getDrawable(R.drawable.icon_set);
        tabHost.addTab(tabHost.newTabSpec("tag5").

                setIndicator("������", tab5).

                setContent(new Intent(this, Setting.class)));


        

    }

        

}


