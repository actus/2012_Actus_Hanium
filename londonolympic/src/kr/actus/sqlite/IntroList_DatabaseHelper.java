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

public class IntroList_DatabaseHelper extends SQLiteOpenHelper
{
	public static final String ROOT_DIR = "/data/data/kr.actus/databases/";
	private static final String DATABASE_NAME = "gameintro.sqlite";
	private static final int SCHEMA_VERSION = 1; 
	
	public IntroList_DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);		
		setDB(context);
	}
	
	@Override
	public void onCreate(SQLiteDatabase gameintro) {		
	}

	@Override
	public void onUpgrade(SQLiteDatabase gameintro, int oldVersion, int newVersion) {
	}  

	public static void setDB(Context ctx) {
		  File folder = new File(ROOT_DIR);
		  if(folder.exists()) { 
		  } else {
		   folder.mkdirs();
		  }
		   AssetManager assetManager = ctx.getResources().getAssets();
		   File outfile = new File(ROOT_DIR+"gameintro.sqlite"); 	  
		   InputStream is = null;   
		   FileOutputStream fo = null;
		   long filesize = 0;     
		   try {
			 is = assetManager.open("gameintro.sqlite", AssetManager.ACCESS_BUFFER);  
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
	
	public ArrayList<HashMap<String,String>> getIntroPersons()
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String selectQuery = "select * from gameintro" ;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
  
		if (cursor.moveToFirst()) {
	  
			do {
    	  
				HashMap<String, String> person = new HashMap<String, String>();
      	
				person.put("name", cursor.getString(1));
				person.put("intro", cursor.getString(2));
				person.put("image", cursor.getString(4));

				
      	
				list.add(person);
			} while (cursor.moveToNext());           	
		} 
		db.close();
		cursor.close();
		return list;
	}
	public ArrayList<HashMap<String,String>> getintroPersons()
	 {
	  ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	  String selectQuery = "select * from gameintro" ;
	  SQLiteDatabase db = this.getReadableDatabase();
	  Cursor cursor = db.rawQuery(selectQuery, null);
	  
	  if (cursor.moveToFirst()) {
	   
	   do {
	       
	    HashMap<String, String> contents = new HashMap<String, String>();
	       
	    contents.put("intro", cursor.getString(2));
	   

	    
	       
	    list.add(contents);
	   } while (cursor.moveToNext());            
	  } 
	  db.close();
	  cursor.close();
	  return list;
	}
	public ArrayList<HashMap<String,String>> getnamePersons()
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String selectQuery = "select * from gameintro" ;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
 
		if (cursor.moveToFirst()) {
	  
			do {
   	  
				HashMap<String, String> person = new HashMap<String, String>();
     	
				
				person.put("name", cursor.getString(1));
				
				list.add(person);
			} while (cursor.moveToNext());           	
		} 
		db.close();
		cursor.close();
		return list;
	}
	
	
	public ArrayList<HashMap<String,String>> getimagePersons()
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String selectQuery = "select * from gameintro " ;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
 
		if (cursor.moveToFirst()) {
	  
			do {
   	  
				HashMap<String, String> person = new HashMap<String, String>();
     	
				
				person.put("image", cursor.getString(4));
				
				list.add(person);
			} while (cursor.moveToNext());           	
		} 
		db.close();
		cursor.close();
		return list;
	}
	
	
}