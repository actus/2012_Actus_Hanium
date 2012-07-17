package com.corea.londonolympic;

import android.app.Activity;
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
}