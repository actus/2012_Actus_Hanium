package kr.actus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Games extends Activity{

	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.games);
    	
    	 ((Button)findViewById(R.id.btn_game1)).setOnClickListener(new Button.OnClickListener(){
	    	  public void onClick(View v){
	    	  
	    	  Intent intent = new Intent(Games.this, gob_quiz.class);
	    	  startActivity(intent);
	      }
});
	      
	      ((Button)findViewById(R.id.btn_game2)).setOnClickListener(new Button.OnClickListener(){
	    	  public void onClick(View v){
	    	  
	    	  Intent intent = new Intent(Games.this, minigame_cardpairing.class);
	    	  startActivity(intent);
	      }
});
    	
    	
    	
	}
	public void onBackPressed(){
		AlertDialog.Builder ad=new AlertDialog.Builder(this);
		ad.setTitle("����");
		ad.setMessage("�����Ͻðڽ��ϱ�?");
		ad.setPositiveButton("��", new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		ad.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		ad.show();
	}
}