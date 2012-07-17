package com.corea.londonolympic;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 


public class GameInfo extends Activity {
    /** Called when the activity is first created. */
    TextView selection;
    boolean hasFocus;
    
    public int checkedItem = 0;
    public String[] site;
    public String select_site = null;
    public TextView tv_postal_code;
    public TextView tv_condition;
    public TextView tv_temp_f;
    public TextView tv_temp_c;
    public TextView tv_humidity;
    public TextView tv_wind;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameinfo);
        
        Button btn_site 		= (Button)findViewById(R.id.btn_site);
        Button btn_parsing 		= (Button)findViewById(R.id.btn_parsing);
        Button bnt_game           = (Button)findViewById(R.id.gameinfo);

        tv_postal_code  = (TextView)findViewById(R.id.tv_postal_code);
        tv_condition 	= (TextView)findViewById(R.id.tv_condition);
        tv_temp_f 		= (TextView)findViewById(R.id.tv_temp_f);
        tv_temp_c 		= (TextView)findViewById(R.id.tv_temp_c);
        tv_humidity 	= (TextView)findViewById(R.id.tv_humidity);
        tv_wind 		= (TextView)findViewById(R.id.tv_wind);
        
        btn_site.setOnClickListener(click);
        btn_parsing.setOnClickListener(click);
       bnt_game.setOnClickListener(click);

       Resources res = getResources();
        site= res.getStringArray(R.array.Site);
        
    }
    
    private OnClickListener click = new OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.btn_site:
					AlertDialog.Builder builder = new AlertDialog.Builder(GameInfo.this);
					builder.setTitle("지역선택");
					builder.setSingleChoiceItems(site, checkedItem, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							checkedItem = which;
						}
					});
					builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							select_site = site[checkedItem];
						}
					});
					builder.show();
					break;
				case R.id.btn_parsing:
					if(select_site == null) {
						Toast.makeText(GameInfo.this, "지역을 선택해 주세요", 0).show();
					} else {
						Parsing(select_site);
					}
					break;
					
				case R.id.gameinfo:
				{
					  Intent i = new Intent(GameInfo.this, InfoList.class);
					  startActivity(i);
					break;	
				}

			}
		}
    };
    
    public void Parsing(String site) {
    	
    	String xml = "http://www.google.co.kr/ig/api?weather=" + site;
    	
    	URL url = null;
    	InputStream in;
    	try {
			url = new URL(xml);
			in = url.openStream();
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(in, "euc-kr");
			Receive(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	
    public void Receive(XmlPullParser parser) {
    	boolean flag = false;
    	String sTag;
    	
    	try { 
	    	int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
	
				// Wait(10);
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
	
				case XmlPullParser.END_DOCUMENT:
					break;
	
				case XmlPullParser.START_TAG:
					// items.add(xpp.getAttributeValue(0));
					sTag = parser.getName();
	
					if (sTag.equals("current_conditions")) {
						flag = true;
					}
						else if (sTag.equals("forecast_information")) {
							flag = true;
					}
					
					if (flag == true) {
						if (sTag.equals("condition")) {
							String sValue = parser.getAttributeValue(0);
							tv_condition.setText("현재 날씨 : " + sValue);
						} else if (sTag.equals("postal_code")) {
							String sValue = parser.getAttributeValue(0);
							tv_postal_code .setText("도시명 : " + sValue);
						} else if (sTag.equals("temp_f")) {
							String sValue = parser.getAttributeValue(0);
							tv_temp_f.setText("화씨 온도 : " + sValue);
						} else if (sTag.equals("temp_c")) {
							String sValue = parser.getAttributeValue(0);
							tv_temp_c.setText("섭씨 온도 : " + sValue);
						} else if (sTag.equals("humidity")) {
							String sValue = parser.getAttributeValue(0);
							tv_humidity.setText("" + sValue);
						} else if (sTag.equals("wind_condition")) {
							String sValue = parser.getAttributeValue(0);
							tv_wind.setText("" + sValue);
						}
					}
					break;
					
				
				case XmlPullParser.END_TAG:
					sTag = parser.getName();
	
					if (sTag.equals("current_conditions")) {
						flag = false;
					}
					break;
	
				case XmlPullParser.TEXT:
					break;
				}
	
				eventType = parser.next();
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}