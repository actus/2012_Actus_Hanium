package kr.actus;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import kr.actus.sqlite.IntroList_DatabaseHelper;
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

public class IntroExplain extends Activity {
	
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
        setContentView(R.layout.introexplain);

		inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		list = new ArrayList<HashMap<String,String>>();

		IntroList_DatabaseHelper db = new IntroList_DatabaseHelper(getApplicationContext());
		
		list = db.getintroPersons();
		
		Result = new ArrayList<HashMap<String,String>>(list);
		
		
       
		Intent intent = getIntent();
        
        int position =26;


        String r1 = (Result.get(intent.getIntExtra("position", position)).toString());
       
		String r2 = r1.replace("{intro="," ");
		  
	
		String r3 = r2.replace("}"," ");
       
		   textok = (TextView)findViewById(R.id.introexplain_text);
	       textok.setText(r3);
         
	   	//¿Ã∏ß
			
			list3 = new ArrayList<HashMap<String,String>>();

			
			list3 = db.getnamePersons();
			Result3 = new ArrayList<HashMap<String,String>>(list3);


			String s1 = (Result3.get(intent.getIntExtra("position", position)).toString());
	       
			
			String s2 = s1.replace("{name=","");
			  
		
			String s3 = s2.replace("}","");
	       
			   textok3 = (TextView)findViewById(R.id.introexplain_name);
		       textok3.setText(s3);
	    
	       
	       
	     //IMAGE
	       list2 = new ArrayList<HashMap<String,String>>();

			
			
			list2 = db.getimagePersons();
			Result2 = new ArrayList<HashMap<String,String>>(list2);
			
			
			// int position2 =36;

			// String photo = Result2.get(position).get("gameinfo_image");	
			String image = (Result2.get(intent.getIntExtra("position", position)).toString());
		       
				
				String image2 = image.replace("{image=","");
				  
			
				String image3 = image2.replace("}","");
				

                ImageView i = (ImageView)findViewById(R.id.intro_image);
				
				
            	Bitmap bm2;  
   			try {
   				
   				i.setImageDrawable(Drawable.createFromStream(getAssets().open(image3),null));
   					
   				} catch (IOException e) {
   					e.printStackTrace();
   			    }
   			return ;
   		
    }
        
}
