package kr.actus;



import java.io.IOException;

import kr.actus.sqlite.gob_quizDBAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class gob_quiz extends Activity{
	
	
	private TextView narr;
	private ImageView que,next;
	private Button one,two,three,four;
	private gob_quizDBAdapter mDb;
	private Cursor cur;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.gob_quiz);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
    	next = (ImageView)findViewById(R.id.quiz_normal);
    	que = (ImageView)findViewById(R.id.gob_quiz_question);
    	one = (Button)findViewById(R.id.gob_quiz_one);
    	two = (Button)findViewById(R.id.gob_quiz_two);
    	three = (Button)findViewById(R.id.gob_quiz_three);
    	four = (Button)findViewById(R.id.gob_quiz_four);
    	narr = (TextView)findViewById(R.id.gob_quiz_narration); 
    
    	
    	mDb = new gob_quizDBAdapter(getBaseContext(),"quiz.db",null,1);
    	
    	mDb.OpenDB();
    	
    	init();    	
    	
    	one.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				score(1);
			}
		});
    	
    	two.setOnClickListener(new View.OnClickListener() {
			
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				score(2);
			}
		});
    	
    	three.setOnClickListener(new View.OnClickListener() {
			
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				score(3);
			}
		});
    	
    	four.setOnClickListener(new View.OnClickListener() {
			
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				score(4);
			}
		});
    	
    	next.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				init();
			}
		});    		
	}
	public void updateText(){
		
		
		
		//text_que.setText("문제: 어느나라 국기일까요?");
		
     	//Bitmap bm;  
		try {
			
			que.setImageDrawable(Drawable.createFromStream(getAssets().open(cur.getString(1)),null));
		
			} catch (IOException e) {
				e.printStackTrace();
				Log.d("Limsusu", "에러난건데 모가?사진사진 ="+e);
		    }
		
		one.setText("1. "+cur.getString(2));
		one.setTextColor(Color.BLACK);
		two.setText("2. "+cur.getString(3));
		two.setTextColor(Color.BLACK);
		three.setText("3. "+cur.getString(4));
		three.setTextColor(Color.BLACK);
		four.setText("4. "+cur.getString(5));
		four.setTextColor(Color.BLACK);
		narr.setText("해설 - "+cur.getString(7));
	}
	
	public void updateCursor(){
		cur=mDb.indicate();
		Log.d("LIMSUSU","랜덤랜덤랜덤");
	}
	
	public void score(int num){
		Log.i("debug","call score method");
		int ans=Integer.valueOf(cur.getString(6));
		if(ans==num){
			Log.i("debug","correct");
			Log.i("debug","answer="+cur.getString(6));
			AlertDialog.Builder alt_rul = new AlertDialog.Builder(this);
	        
	        alt_rul.setMessage("정답입니다")
	        .setCancelable(false)
	        .setPositiveButton("닫기", new DialogInterface.OnClickListener() { 
	         public void onClick(DialogInterface dialog, int id) {
	        	 
	         } 
	        });  
	        AlertDialog alert2 = alt_rul.create();	        
	        alert2.setTitle("알림");  	           
	        alert2.show();
			
		}else{
			AlertDialog.Builder alt_rul = new AlertDialog.Builder(this);	        
	        alt_rul.setMessage("틀렸습니다")
	        .setCancelable(false)
	        .setPositiveButton("닫기", new DialogInterface.OnClickListener() { 
	         public void onClick(DialogInterface dialog, int id) {
	          
	         } 
	        });  
	        AlertDialog alert2 = alt_rul.create();	      
	        alert2.setTitle("알림");  	        
	        alert2.show();
		}
		next.setImageResource(R.drawable.quiz_great);
		narr.setVisibility(View.VISIBLE);
		if(ans==1)
			one.setTextColor(Color.RED);
		else if(ans==2)
			two.setTextColor(Color.RED);
		else if(ans==3)
			three.setTextColor(Color.RED);
		else if(ans==4)
			four.setTextColor(Color.RED);
	}
	
	public void init(){
    	narr.setVisibility(View.INVISIBLE);
    	next.setImageResource(R.drawable.quiz_normal);
    	updateCursor();
    	updateText();
	}
	
}
