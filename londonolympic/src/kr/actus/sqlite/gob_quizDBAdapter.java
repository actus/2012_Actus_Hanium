package kr.actus.sqlite;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class gob_quizDBAdapter extends SQLiteOpenHelper{
	
	private Context mContext;
	private SQLiteDatabase db;
	private Cursor cur;
	
	public static final String PACKAGE_DIR = "/data/data/kr.actus/databases/";
	public static final String DATABASE_NAME = "quiz.db";
	public gob_quizDBAdapter(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.mContext=context;
		// TODO Auto-generated constructor stub
	}
	
	public void OpenDB(){
		Log.i("debug", "open db");
		
		LoadDB(mContext);
		
		try{			
			db = SQLiteDatabase.openDatabase(PACKAGE_DIR+"databases/"+DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
			
			cur = db.rawQuery("SELECT * From qna", null);
			Log.i("debug", "acquire db");
			
		}catch(Exception e){
			
		}
	}	

	public static void LoadDB(Context ctx) {
		// check 		
		File folder = new File(PACKAGE_DIR + "databases");
		folder.mkdirs();
		File outfile = new File(PACKAGE_DIR + "databases/" + DATABASE_NAME);
		Log.i("debug", "load db");
		if (outfile.length() <= 0) {
			AssetManager assetManager = ctx.getResources().getAssets();	
			try {	
				InputStream is = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);	
				long filesize = is.available();	
				byte [] tempdata = new byte[(int)filesize];	
				is.read(tempdata); 		
				is.close();				
				outfile.createNewFile();	
				FileOutputStream fo = new FileOutputStream(outfile);
				fo.write(tempdata);			
				fo.close();	
				Log.i("debug", "success");
			} catch (IOException e) {
				e.printStackTrace();
				Log.i("debug", "failed...");
			}	
		}		
	}

	public Cursor indicate(){
		Random rand=new Random();
		cur.moveToPosition(rand.nextInt(cur.getCount()));
		return cur;		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
	}
	
}
