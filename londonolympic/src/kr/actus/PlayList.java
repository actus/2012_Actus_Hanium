package kr.actus;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import kr.actus.sqlite.PlayListHepler;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PlayList extends Activity {
	
	private static String TAG = "PlayList"; 
	protected ListAdapter adapter;
	ArrayList<HashMap<String, String>> list;
	ArrayList<HashMap<String, String>> list2;
	ArrayList<HashMap<String, String>> Result;
	ArrayList<HashMap<String, String>> Result2;
	
	public HashSet<Integer> positions = new HashSet<Integer>();
	LayoutInflater inflater;
	
	TextView textok;
	
	private AlarmManager am;
	long timeset;
	Intent i;
	PendingIntent p;

	PlayListHepler db;
	int oldPosition,newPosition;
	
	String play;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        
   
        
	    i = new Intent(getBaseContext(), AlarmReceiver.class);
	    p = PendingIntent.getBroadcast(getBaseContext(), 0, i, 0);
        
       
        
        
        Intent intent = getIntent(); 					//Schedule에서 Intent를 받아옴
         play = intent.getStringExtra("date"); 	// 받아온 데이터를 paly넣어준다.
        
       // og.i("DATE 입니다.", play);
        
        
        textok = (TextView)findViewById(R.id.textok);
        textok.setText(play); 							// 액티비티에 날짜띄우는 text
        
       
        //리스트뷰에 정보 띄우기. 
		final ListView listView = (ListView) findViewById(R.id.list);
		inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		list = new ArrayList<HashMap<String,String>>();

		//DB연결
		db = new PlayListHepler(getApplicationContext());
		list = db.getAllPlays(play);
		Result = new ArrayList<HashMap<String,String>>(list);
        
		//날짜에 맞게 리스트 띄우기
		final CustomAdapter adapter = new CustomAdapter(this, R.layout.main, Result);
    	listView.setAdapter(adapter);
    	
//DB에서 날짜시간 가져오기 
    	
    	//list2= new ArrayList<HashMap<String,String>>();
    	list2 = db.getAllAlarm();
    	Result2 = new ArrayList<HashMap<String,String>>(list2);
    	

    	

    	//리스트뷰 클릭하면 알람 라디오 버튼 나오기.
    	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    		public void onItemClick(AdapterView<?> parent, final View view, final int position,
    				long id) {
    			final long id2 = id;
    			final AdapterView<?> _parent = parent;

    		     int selectedPosition = listView.getSelectedItemPosition();

    			new AlertDialog.Builder(PlayList.this)
				.setTitle("알람 설정")
				
				 .setSingleChoiceItems(new String[] {"ON","OFF"}, -1, 
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch(item){
						
						case 0:
						positions.add(position);
				        Log.e("position",position+"");
				        
				        Log.e("id",id2+"");
					
					  view.setBackgroundColor(0xFFCD853F);
					 
					   db.update(position+1, play, "yes");
					   Log.d("날짜넘어오나?", play);

					   setAlarm(view);
					        break;
					        
						case 1:
							
				            am.cancel(p);  // 알람 중지
							 view.setBackgroundColor(Color.TRANSPARENT);
							  db.update(position+1, play, "no");
							 		
						}
					        
							}
					
				})

				.setPositiveButton("확인", null)
				.show();			    	
    		} });

    }
    
    
    protected void startDialog() {
    	AlertDialog.Builder alt1 = new AlertDialog.Builder(this);  
        alt1.setMessage("리스트를 누르시면 경기시간 알람 설정이 가능합니다.")
        .setCancelable(false)
        .setPositiveButton("확인", new DialogInterface.OnClickListener() { 
        	public void onClick(DialogInterface dialog, int id) {          	
        		} 
        	});  
        AlertDialog alt2 = alt1.create();        
        alt2.setTitle("도움말");                 
        alt2.show();
    }
	
    public void setAlarm(View view)
    {
		CustomAdapter.ViewHolder viewHolder = (CustomAdapter.ViewHolder)view.getTag();

		String date = (String) viewHolder.date.getText();
		String time = (String)viewHolder.time.getText();

		//날짜와 시간을 AlarmReceiver 에 넘겨줘서 사용할려고 ㅋㅋㅋㅋ
		String d = date + time;
		Intent i3;
		i3 = new Intent(PlayList.this,AlarmReceiver.class);
		i3.putExtra("alarm",d); //끝 
		
		d = d.replaceAll(":","");

       Log.e("lololo","wow"+d);					

	        Calendar cal = Calendar.getInstance(); 
	        
	        try
	        { 
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmm"); 
	            
	            cal.setTime(formatter.parse(d)); 
					
        	} 
	        catch(ParseException e) 
	        { 
	            e.printStackTrace(); 
	        } 

	        long mili = cal.getTimeInMillis();
	       

	        am.set(AlarmManager.RTC_WAKEUP,mili,p);
    }
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		startDialog();
	}
	

    
    //DB정보를 Hashmap에 연동?
	private class CustomAdapter extends ArrayAdapter<HashMap<String, String>>{
		public CustomAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> Strings) {
			super(context, textViewResourceId, Strings);
		}
		private class ViewHolder{
			TextView info, explain, time, date, reservation;
			
		}
		ViewHolder viewHolder;
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){        
				convertView=inflater.inflate(R.layout.row, null);
				viewHolder=new ViewHolder();
				
				viewHolder.date=(TextView) convertView.findViewById(R.id.date);
				viewHolder.time=(TextView) convertView.findViewById(R.id.time);
				viewHolder.info=(TextView) convertView.findViewById(R.id.info);
				viewHolder.explain=(TextView) convertView.findViewById(R.id.explain);
				
				
				convertView.setTag(viewHolder);
				
			} else {
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.date.setText(Result.get(position).get("date"));
			viewHolder.time.setText(Result.get(position).get("time"));
			viewHolder.info.setText(Result.get(position).get("info"));
			viewHolder.explain.setText(Result.get(position).get("explain"));
			
			String reservation = Result.get(position).get("reservation");
			String date2 = Result.get(position).get("date");
			
			if(reservation != null && reservation.equals("yes"))
			{
				convertView.setBackgroundColor(0xFFCD853F);
			}
			else 
			{
				convertView.setBackgroundColor(Color.TRANSPARENT);
				 
				for(int savedPosition : positions)
				 {
				  if(position == savedPosition)
				  {
				   convertView.setBackgroundColor(0xFFCD853F);
				   Log.e("CustomAdapter","savedPosition"+savedPosition + " position: " + position);
				  }

				 }

			}
		
			
			return convertView;
		}
	

	}


	protected void onDestroy() {
		// TODO Auto-generated method stub
		db.close();
		super.onDestroy();
	}
	
		
	
	
    
}