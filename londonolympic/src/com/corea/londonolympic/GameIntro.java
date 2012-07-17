package com.corea.londonolympic;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.corea.londonolympic.sqlite.IntroList_DatabaseHelper;

public class GameIntro extends Activity {
	
	protected ListAdapter adapter;
	ArrayList<HashMap<String, String>> list;
	ArrayList<HashMap<String, String>> Result;
	LayoutInflater inflater;
	ListView listview;


	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameintro);
        
        listview = (ListView) findViewById(R.id.introlist);
		
		inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		list = new ArrayList<HashMap<String,String>>();

		IntroList_DatabaseHelper db = new IntroList_DatabaseHelper(getApplicationContext());
		
		list = db.getIntroPersons();
		Result = new ArrayList<HashMap<String,String>>(list);

		final CustomAdapter adapter = new CustomAdapter(this, R.layout.introlist_row, Result);
		listview.setAdapter(adapter);
    	
    	
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

    		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		
    	
    			
    			for(int num=0;num<26;num++)
    			if(position==num){
    				Intent i = new Intent(GameIntro.this, IntroExplain.class);	
    				i.putExtra("position",position);
    				startActivity(i);
    				
    			}
    			
    		}
    			
    			
    		
    	});
    	
    	
    
    }
    
      private class CustomAdapter extends ArrayAdapter<HashMap<String, String>>{
  		public CustomAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> Strings) {
  			super(context, textViewResourceId, Strings);
  		}
  		private class ViewHolder{
  			TextView name;
  			ImageView image;
  			
  			
  		}
  		ViewHolder viewHolder;

  		public View getView(int position, View convertView, ViewGroup parent) {
  			if(convertView==null){        
  				convertView=inflater.inflate(R.layout.introlist_row, null);
  				viewHolder=new ViewHolder();
  				viewHolder.image=(ImageView) convertView.findViewById(R.id.photo1);
  				viewHolder.name=(TextView) convertView.findViewById(R.id.name1);
  				
  				
	convertView.setTag(viewHolder);
				
			} else {
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.name.setText(Result.get(position).get("name"));
			String photo = Result.get(position).get("image");			
			Bitmap bm;  
			try {
					bm = BitmapFactory.decodeStream(getResources().getAssets().open(photo));
					 viewHolder.image.setImageBitmap(bm);
				} catch (IOException e) {
					e.printStackTrace();
			    }
			return convertView;
		}
	}
    
}