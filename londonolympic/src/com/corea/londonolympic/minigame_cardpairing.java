package com.corea.londonolympic;



/*
 * 미니 게임 - 카드 짝 맞추기 게임
 */



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class minigame_cardpairing extends Activity implements View.OnClickListener {
	private static final int TOTAL_CARD_NUM = 16; // 카드 수
	
	private int[] cardId = {R.id.card01, R.id.card02, R.id.card03, R.id.card04, R.id.card05, R.id.card06, R.id.card07, R.id.card08,
							R.id.card09, R.id.card10, R.id.card11, R.id.card12, R.id.card13, R.id.card14, R.id.card15, R.id.card16};
	
	
	
	
	
	
	
	private Card[] cardArray = new Card[TOTAL_CARD_NUM];
	
	private int CLICK_CNT = 0; // 클릭 카운트
	private Card first, second; // 첫번째 누른 버튼과 두번재 누른 버튼
	private int SUCCESS_CNT = 0; // 짝 맞추기 성공 카운트
	private boolean INPLAY = false; // 카드를 클릭할 수 있는지 여부
	
	//----------- 액티비티 위젯 -----------//
	private Button start;
	//-----------------------------------//
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_cardpairing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        
        for(int i=0; i<TOTAL_CARD_NUM; i++) {
        	cardArray[i] = new Card(i/2); // 카드 생성
        	findViewById(cardId[i]).setOnClickListener(this); // 카드 클릭 리스너 설정
        	cardArray[i].card = (ImageButton) findViewById(cardId[i]); // 카드 할당
        	cardArray[i].onBack(); // 카드 뒤집어 놓음
        }
        
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View v) {
				startGame();
				start.setText("다시 시작");
			}
		});
        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
    } // end of onCreate
    
    protected void startDialog() {
    	AlertDialog.Builder alt1 = new AlertDialog.Builder(this);  
        alt1.setMessage("카드 짝 맞추기 게임입니다. 카드패를 잘 기억하시고 카드를 2개씩 뒤집어 짝을 맞추면 됩니다. 모든 짝을 맞추면 완료됩니다.")
        .setCancelable(false)
        .setPositiveButton("닫기", new DialogInterface.OnClickListener() { 
        	public void onClick(DialogInterface dialog, int id) {          	
        		} 
        	});  
        AlertDialog alt2 = alt1.create();        
        alt2.setTitle("게임 설명");                 
        alt2.show();
    }

    protected void clearDialog() {
    	AlertDialog.Builder alt1 = new AlertDialog.Builder(this);  
        alt1.setMessage("모든 카드 짝을 맞추셨습니다. 축하합니다.")
        .setCancelable(false)
        .setPositiveButton("닫기", new DialogInterface.OnClickListener() { 
        	public void onClick(DialogInterface dialog, int id) {          	
        		} 
        	});  
        AlertDialog alt2 = alt1.create();       
        alt2.setTitle("짝 맞추기 완료");                  
        alt2.show();
    }
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		startDialog();
	}

	public void onClick(View v) {
		if (INPLAY) {			
			switch (CLICK_CNT) {
			case 0: // 카드 하나만 뒤집었을 경우
				for (int i=0; i<TOTAL_CARD_NUM; i++) {
					if (cardArray[i].card == (ImageButton) v) {
						first = cardArray[i];
						break;
					}
				}
				if (first.isBack) { // 이미 뒤집힌 카드는 처리 안함
					first.onFront();
					CLICK_CNT = 1;
				}
				break;
			case 1: // 카드 두개 뒤집었을 경우
				for (int i=0; i<TOTAL_CARD_NUM; i++) {
					if (cardArray[i].card == (ImageButton) v) {
						second = cardArray[i];
						break;
					}
				}
				if (second.isBack) { // 뒷면이 보이는 카드일 경우만 처리
					second.onFront();
					
					if (first.value == second.value) { // 짝이 맞은 경우
						SUCCESS_CNT++;
						Log.v("SUCCESS_CNT", "" + SUCCESS_CNT);
						if (SUCCESS_CNT == TOTAL_CARD_NUM/2) { // 모든 카드의 짝을 다 맞추었을 경우
							clearDialog();
						}
					}
					else { // 짝이 틀릴 경우
						Timer t = new Timer(0);
						t.start();
					}
					CLICK_CNT = 0;
				}
				break;
			}
		}
	}

	void startGame() {
		int[] random = new int[TOTAL_CARD_NUM];
		int x;
		
		for (int i=0; i<TOTAL_CARD_NUM; i++) { // 모든 카드의 뒷면이 보이게 함
			if (!cardArray[i].isBack)
				cardArray[i].onBack();
		}
		
		boolean dup;
		for (int i=0; i<TOTAL_CARD_NUM; i++) { // 0~15까지 랜덤한 순서로 random배열에 저장
			while(true) {
				dup = false;
				x = (int) (Math.random() * TOTAL_CARD_NUM);
				for (int j=0; j<i; j++) {
					if (random[j] == x) {
						dup = true;
						break;
					}
				}
				if (!dup) break;
			}
			random[i] = x;
		}
		
		start.setClickable(false);
		for (int i=0; i<TOTAL_CARD_NUM; i++) {
			cardArray[i].card = (ImageButton) findViewById(cardId[random[i]]);
			cardArray[i].onFront();
		}
		
		Log.v("timer", "start");
		Timer t = new Timer(1);
		//flag = false;
		t.start();
		/*
		while(true) {
			if (flag) break;
			//Log.v("flag", "" + flag);
		}
		Log.v("timer", "end");
		*/
		
		SUCCESS_CNT = 0;
		CLICK_CNT = 0;
		INPLAY = true;
	}
	
	class Timer extends Thread {
		int kind;
		
		Timer (int kind) {
			super();
			this.kind = kind;
		}
		@Override
		public void run() {
			INPLAY = false;
			// TODO Auto-generated method stub
			try {
				if (kind == 0) {
					Thread.sleep(1000);
					mHandler.sendEmptyMessage(0);
				}
				else if (kind == 1) {
					Thread.sleep(3000);
					mHandler.sendEmptyMessage(1);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			INPLAY = true;
		}
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				first.onBack();
				second.onBack();
				first.isBack = true;
				second.isBack = true;
			}
			else if (msg.what == 1) {
				//flag = true;
				for (int i=0; i<TOTAL_CARD_NUM; i++) {
					cardArray[i].onBack();
				}
				start.setClickable(true);
			}
		}
	};
	
}

class Card { // start of Card class
	private final static int backImageID = R.drawable.cardback;
	private final static int[] frontImageID = {R.drawable.card1, R.drawable.card2,
											   R.drawable.card3, R.drawable.card4,
								 		 	   R.drawable.card5, R.drawable.card6,
								 		 	   R.drawable.card7, R.drawable.card8};

	int value;
	boolean isBack;
	ImageButton card;
	
	Card(int value) {
		this.value = value;
	}

	public void onBack() { // 카드 뒷면이 보이게 뒤집음
		if (!isBack) {
			card.setBackgroundResource(backImageID);
			isBack = true;
		}
	}
	
	public void flip() { // 카드를 뒤집음
		if (!isBack) {
			card.setBackgroundResource(backImageID);
			isBack = true;
		}
		else {
			card.setBackgroundResource(frontImageID[value]);
			isBack = false;
		}
	}
	
	public void onFront() { // 카드 그림면을 보여줌
		if (isBack) {
			card.setBackgroundResource(frontImageID[value]);
			isBack = false;
		}
	}
} // end of Card class