package kr.actus;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {


	@Override
	public void onReceive(Context context, Intent intent) {
		
     
		//진동진동진동!
		
    	Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    	vibe.vibrate(500); 
    	
		
		
		NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent i = new Intent(context, PlayList.class);
		PendingIntent p = PendingIntent.getBroadcast(context, 0, i, 0);

		
		
		Notification n = new Notification(android.R.drawable.stat_notify_chat,"경기시작 알람 울립니다.",1000);
		n.flags = Notification.FLAG_AUTO_CANCEL;
		n.setLatestEventInfo(context, "경기시작 알람 울립니다.", "얼렁 TV 앞으로 모이세요요!!!", p);
		nm.notify(1,n);
		
		
		//Log.e("AlarmReceiver","onReceiver()");
 
	
	}
}
