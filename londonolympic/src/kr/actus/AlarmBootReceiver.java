package kr.actus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import kr.actus.sqlite.PlayListHepler;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;

public class AlarmBootReceiver extends BroadcastReceiver {
	
	private static String TAG = "AlarmBootReceiver";
	
	public void onReceive(Context context, Intent intent) {
 
        String action = intent.getAction();
 
        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
            //Device 부팅시 일로 들어오므로 이곳에서 원하는 작업을 구현한다.
        	
        	Log.e("START", "ALARM TEST");
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            setAlarm(context, am);
           
          // Log.e("AlarmBootReceiver","onReceiver()");

        	/*Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        	vibe.vibrate(500); 
        	
    		
    		
    		NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    		Intent i = new Intent(context, PlayList.class);
    		PendingIntent p = PendingIntent.getBroadcast(context, 0, i, 0);

    		
    		
    		Notification n = new Notification(android.R.drawable.stat_notify_chat,"경기시작 알람 울립니다.",1000);
    		n.flags = Notification.FLAG_AUTO_CANCEL;
    		n.setLatestEventInfo(context, "경기시작 알람 울립니다.", "얼렁 TV 앞으로 모이세요요!!!", p);
    		nm.notify(1,n);
    		
    		/
    		 * 
    		 */
    	//	Log.e("AlarmBootReceiver","onReceiver()");
        }
 
    }
	
	public void setAlarm(Context context, AlarmManager am)
    {
	  PlayListHepler db;
	  db = new PlayListHepler(context.getApplicationContext());
	  ArrayList<HashMap<String, String>> list = db.getAlarmedClomn();
	  ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>(list);
	  
	  for(HashMap<String, String> reservedInfo : result )
	  {
	   //날짜와 시간을 AlarmReceiver 에 넘겨줘서 사용할려고 ㅋㅋㅋㅋ
	String d = reservedInfo.get("date") + reservedInfo.get("time");
	d = d.replaceAll(":","");
	 
	       //  Log.e(TAG,"wow"+d);     
	
	  Calendar cal = Calendar.getInstance(); 
	  
		  try
		  { 
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmm"); 
	      // d="201207231855";
	          cal.setTime(formatter.parse(d)); 
	  
	      } 
	      catch(ParseException e) 
	      { 
	          e.printStackTrace(); 
	      } 
	
	      long mili = cal.getTimeInMillis();
	      
		if(mili > System.currentTimeMillis())
		{
			Log.e("Test",  mili - System.currentTimeMillis() + "");
	    	Intent i = new Intent(((ContextWrapper) context).getBaseContext(), AlarmReceiver.class);
	        PendingIntent p = PendingIntent.getBroadcast(((ContextWrapper) context).getBaseContext(), 0, i, 0);
	        am.set(AlarmManager.RTC_WAKEUP,mili,p);
	    }
	   }
	  }
}
