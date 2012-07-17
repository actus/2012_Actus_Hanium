package com.corea.londonolympic;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
 
public class Schedule extends Activity {
	
	Intent i;
	private AlarmManager am;
	PendingIntent p;
	 protected void onCreate(Bundle savedInstanceState) {

		  super.onCreate(savedInstanceState);

		   setContentView(R.layout.schedule); 
		   
		   ((Button)findViewById(R.id.bnt0726)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0727)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0728)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0729)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0730)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0731)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0801)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0802)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0803)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0804)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0805)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0806)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0807)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0808)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0809)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0810)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0811)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0812)).setOnClickListener(listener);
		   ((Button)findViewById(R.id.bnt0813)).setOnClickListener(listener);
		   

	        

	  }
	 
		   Button.OnClickListener listener = new View.OnClickListener() {
				
				public void onClick(View view) {
					
					switch(view.getId()) {
					case R.id.bnt0726:
					i = new Intent(Schedule.this, PlayList.class);
					i.putExtra("date","20120726" );
						
						break;
						
					case R.id.bnt0727:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120727" );
						break;
						
					case R.id.bnt0728:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120728" );
						break;
						
					case R.id.bnt0729:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120729" );
						break;
						
					case R.id.bnt0730:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120730" );
						break;
						
					case R.id.bnt0731:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120731" );
						break;
						
					case R.id.bnt0801:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120801" );
						break;
						
					case R.id.bnt0802:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120802" );
						break;
						
					case R.id.bnt0803:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120803" );
						break;
						
					case R.id.bnt0804:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120804" );
						break;
						
					case R.id.bnt0805:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120805" );
						break;
						
					case R.id.bnt0806:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120806" );
						break;
						
					case R.id.bnt0807:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120807" );
						break;
						
					case R.id.bnt0808:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120808" );
						break;
						
					case R.id.bnt0809:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120809" );
						break;
						
					case R.id.bnt0810:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120810" );
						break;
						
					case R.id.bnt0811:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120811" );
						break;
						
					case R.id.bnt0812:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120812" );
						break;
						
					case R.id.bnt0813:
						i = new Intent(Schedule.this, PlayList.class);
						i.putExtra("date","20120813" );
						break;
						
						default:
							Toast.makeText(getBaseContext(), "Error!!", Toast.LENGTH_LONG).show();
							
					}
					
				
					
					
					
				startActivity(i);
				
				
				
			
				}
				
			};

			
			

		  

		  }

