package kr.actus;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Setting extends Activity {
	
	 protected void onCreate(Bundle savedInstanceState) {

		   // TODO Auto-generated method stub

		   super.onCreate(savedInstanceState);

		   //ȭ�� �����ϱ�

		   setContentView(R.layout.setting); 
		   findViewById(R.id.logo1).setOnClickListener(linkListener);
		}
		   View.OnClickListener linkListener = new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.actus.kr/")));

			}
		   };


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
