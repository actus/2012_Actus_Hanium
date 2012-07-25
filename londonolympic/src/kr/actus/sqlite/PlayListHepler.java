package kr.actus.sqlite;

 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayListHepler extends SQLiteOpenHelper
{
	public static final String ROOT_DIR = "/data/data/kr.actus/databases/";
	private static final String DATABASE_NAME = "plays.sqlite";
	private static final int SCHEMA_VERSION = 1;
	
	public PlayListHepler(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);		
		setDB(context);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}  

	public static void setDB(Context ctx) {
		  File folder = new File(ROOT_DIR);
		  if(folder.exists()) { 
		  } else {
		   folder.mkdirs();
		  }
		   AssetManager assetManager = ctx.getResources().getAssets();
		   File outfile = new File(ROOT_DIR+"plays.sqlite"); 	  
		   InputStream is = null;   
		   FileOutputStream fo = null;
		   long filesize = 0;     
		   try {
			 is = assetManager.open("plays.sqlite", AssetManager.ACCESS_BUFFER);  
		     filesize = is.available();
		     if (outfile.length() <= 0) {
		     byte[] tempdata = new byte[(int) filesize];
		     is.read(tempdata); 
		     is.close(); 
		     outfile.createNewFile();
		     fo = new FileOutputStream(outfile);
		     fo.write(tempdata);
		     fo.close();    
		    } else {}
		    } catch (IOException e) {}   
	} 
	
	public ArrayList<HashMap<String,String>> getAllPlays(String date)
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String selectQuery = "select * from plays where date = '" + date+ "'" ;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
  
		if (cursor.moveToFirst()) {
	  
			do {
    	  
				HashMap<String, String> play = new HashMap<String, String>();
				play.put("date", cursor.getString(1));
				play.put("time", cursor.getString(2));
				play.put("info", cursor.getString(3));
				play.put("explain", cursor.getString(4));
				
                play.put("reservation",  cursor.getString(5)); 

				
      	
				list.add(play);
			} while (cursor.moveToNext());           	
		} 
		
		
		db.close();
		cursor.close();
		return list;
	}
	
	
	public ArrayList<HashMap<String,String>> getAllAlarm()
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String selectQuery = "select * from plays";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
  
		if (cursor.moveToFirst()) {
	  
			do {
    	  
				HashMap<String, String> alarm = new HashMap<String, String>();
				alarm.put("date", cursor.getString(1));
				alarm.put("time", cursor.getString(2));
				

				
      	
				list.add(alarm);
			} while (cursor.moveToNext());           	
		} 
		
		
		db.close();
		cursor.close();
		return list;
	}
	
	
	public void update(int id, String play, String reserve)
	{
		String updateQuery = "update plays set reservation = '"+ reserve +"' where position1 = " +id +" and date ='"+ play+"'" ;
		SQLiteDatabase db = this.getWritableDatabase();
		try
		{
			db.execSQL(updateQuery);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		db.close();
  	}

	
	
	public ArrayList<HashMap<String,String>> getAlarmedClomn()
	 {
	  ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	  String selectQuery = "select * from plays where reservation = 'yes'";
	  SQLiteDatabase db = this.getReadableDatabase();
	  Cursor cursor = db.rawQuery(selectQuery, null);
	  
	  if (cursor.moveToFirst()) {
	   
	   do {
	       
	    HashMap<String, String> alarm = new HashMap<String, String>();
	    alarm.put("date", cursor.getString(1));
	    alarm.put("time", cursor.getString(2));
	        
	    list.add(alarm);
	   } while (cursor.moveToNext());            
	  }

	  db.close();
	  cursor.close();
	  return list;
	 }
	  
	
	
	
	
}