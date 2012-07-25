package kr.actus;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import kr.actus.sqlite.DatabaseHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InfoExplain extends Activity {
	
	private static final String TAG = null;

	protected ListAdapter adapter;
	
	ArrayList<HashMap<String, String>> list;
	ArrayList<HashMap<String, String>> Result;
	
	ArrayList<HashMap<String, String>> list2;
	ArrayList<HashMap<String, String>> Result2;
	
	ArrayList<HashMap<String, String>> list3;
	ArrayList<HashMap<String, String>> Result3;
	
	LayoutInflater inflater;
	ListView listview;
	Intent i;
	TextView textok;
	TextView textok2;
	TextView textok3;
	ImageView imageview;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoexplain);
        

        
        
            
        
		inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//CONTENTS
		
		list = new ArrayList<HashMap<String,String>>();

		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		
		list = db.getSelectPersons();
		Result = new ArrayList<HashMap<String,String>>(list);
		
		
       
		Intent intent = getIntent();
        
       int position =36;


		String r1 = (Result.get(intent.getIntExtra("position", position)).toString());
       
		
		String r2 = r1.replace("{contents=","");
		  
	
		String r3 = r2.replace("}","");
       
		   textok = (TextView)findViewById(R.id.explain_text);
	       textok.setText(r3);
	       
	       
			//¿Ã∏ß
			
			list3 = new ArrayList<HashMap<String,String>>();

			
			list3 = db.getNamePersons();
			Result3 = new ArrayList<HashMap<String,String>>(list3);


			String s1 = (Result3.get(intent.getIntExtra("position", position)).toString());
	       
			
			String s2 = s1.replace("{name=","");
			  
		
			String s3 = s2.replace("}","");
	       
			   textok3 = (TextView)findViewById(R.id.explain_name);
		       textok3.setText(s3);
	    
	       
	       
	     //IMAGE
	       list2 = new ArrayList<HashMap<String,String>>();

			
			
			list2 = db.getViewPersons();
			Result2 = new ArrayList<HashMap<String,String>>(list2);
			
			
			// int position2 =36;

			// String photo = Result2.get(position).get("gameinfo_image");	
			String image = (Result2.get(intent.getIntExtra("position", position)).toString());
		       
				
				String image2 = image.replace("{gameinfo_image=","");
				  
			
				String image3 = image2.replace("}","");
				

                 ImageView i = (ImageView)findViewById(R.id.test);
				
				
             	Bitmap bm2;  
    			try {
    				
    				i.setImageDrawable(Drawable.createFromStream(getAssets().open(image3),null));
    					
    				} catch (IOException e) {
    					e.printStackTrace();
    			    }
    			return ;
    		}
				
				
				
				
			
		/*class MyView extends View{
			public MyView(Context context){
				super(context);
			}
			

			Bitmap bm;  
			@Override
			protected void onDraw(Canvas canvas){
				super.onDraw(canvas);
				
				bm = BitmapFactory.decodeStream(getResources().getAssets().open(image3));
				
				Bitmap bitmap = BitmapFactory.decodeFile(filePath);
				canvas.drawBitmap(bitmap,0,0,null);
				
				bitmap.recycle();
			}
			
			
		}*/
			
         
    
    }
    
    
  

