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
        
       
        
        
        Intent intent = getIntent(); 					//Schedule���� Intent�� �޾ƿ�
         play = intent.getStringExtra("date"); 	// �޾ƿ� �����͸� paly�־��ش�.
        
       // og.i("DATE �Դϴ�.", play);
        
        
        textok = (TextView)findViewById(R.id.textok);
        textok.setText(play); 							// ��Ƽ��Ƽ�� ��¥���� text
        
       
        //����Ʈ�信 ���� ����. 
		final ListView listView = (ListView) findViewById(R.id.list);
		inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		list = new ArrayList<HashMap<String,String>>();

		//DB����
		db = new PlayListHepler(getApplicationContext());
		list = db.getAllPlays(play);
		Result = new ArrayList<HashMap<String,String>>(list);
        
		//��¥�� �°� ����Ʈ ����
		final CustomAdapter adapter = new CustomAdapter(this, R.layout.main, Result);
    	listView.setAdapter(adapter);
    	
//DB���� ��¥�ð� �������� 
    	
    	//list2= new ArrayList<HashMap<String,String>>();
    	list2 = db.getAllAlarm();
    	Result2 = new ArrayList<HashMap<String,String>>(list2);
    	

    	

    	//����Ʈ�� Ŭ���ϸ� �˶� ���� ��ư ������.
    	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    		public void onItemClick(AdapterView<?> parent, final View view, final int position,
    				long id) {
    			final long id2 = id;
    			final AdapterView<?> _parent = parent;

    		     int selectedPosition = listView.getSelectedItemPosition();

    			new AlertDialog.Builder(PlayList.this)
				.setTitle("�˶� ����")
				
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
					   Log.d("��¥�Ѿ����?", play);

					   setAlarm(view);
					        break;
					        
						case 1:
							
				            am.cancel(p);  // �˶� ����
							 view.setBackgroundColor(Color.TRANSPARENT);
							  db.update(position+1, play, "no");
							 		
						}
					        
							}
					
				})

				.setPositiveButton("Ȯ��", null)
				.show();			    	
    		} });

    }
    
    
    protected void startDialog() {
    	AlertDialog.Builder alt1 = new AlertDialog.Builder(this);  
        alt1.setMessage("����Ʈ�� �����ø� ���ð� �˶� ������ �����մϴ�.")
        .setCancelable(false)
        .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() { 
        	public void onClick(DialogInterface dialog, int id) {          	
        		} 
        	});  
        AlertDialog alt2 = alt1.create();        
        alt2.setTitle("����");                 
        alt2.show();
    }
	
    public void setAlarm(View view)
    {
		CustomAdapter.ViewHolder viewHolder = (CustomAdapter.ViewHolder)view.getTag();

		String date = (String) viewHolder.date.getText();
		String time = (String)viewHolder.time.getText();

		//��¥�� �ð��� AlarmReceiver �� �Ѱ��༭ ����ҷ��� ��������
		String d = date + time;
		Intent i3;
		i3 = new Intent(PlayList.this,AlarmReceiver.class);
		i3.putExtra("alarm",d); //�� 
		
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
	

    
    //DB������ Hashmap�� ����?
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