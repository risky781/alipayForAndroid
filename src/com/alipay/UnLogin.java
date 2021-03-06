package com.alipay;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class UnLogin extends Activity {
	private static final String TAG = "Alipay";
	private Menu myMenu;
	private AlipayDialog ad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_login);

		GridView gridView = (GridView) findViewById(R.id.grid);
		int[] appIcons = {
				R.drawable.sjcz, R.drawable.bl, R.drawable.sjzz, R.drawable.xykhk,
				R.drawable.cp, R.drawable.sf, R.drawable.df, R.drawable.rqf,
				R.drawable.ghkd, R.drawable.wysk, R.drawable.wyfk, R.drawable.yxp,
				R.drawable.tmzf, R.drawable.tmsy, R.drawable.axjz, R.drawable.yxdk
				};
		String[] appTitles = {
				getString(R.string.sjcz), getString(R.string.bl), getString(R.string.sjzz), getString(R.string.xykhk),
				getString(R.string.cp), getString(R.string.sf), getString(R.string.df), getString(R.string.rqf), 
				getString(R.string.ghkd), getString(R.string.wysk), getString(R.string.wyfk), getString(R.string.yxp), 
				getString(R.string.tmzf), getString(R.string.tmsy), getString(R.string.axjz), getString(R.string.yxdk), 
				};
		GridButton adapter = new GridButton(UnLogin.this, appIcons, appTitles);
		gridView.setAdapter(adapter);
		
		bindUI();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		this.myMenu = menu;
		addRegularMenuItems(menu);
		return true;
	}
	
	private void addRegularMenuItems(Menu menu){
		int base = Menu.FIRST;
		
		menu.add(0, base, base+1, "关于");
		menu.add(0, base+1, base+2, "退出");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 1:
			Intent intent = new Intent();
			intent.setClass(UnLogin.this, About.class);
			startActivity(intent);
			break;
		case 2:
			this.exit();
			break;
		}
		return true;
	}
	
	private void exit(){
		if(ad == null){
			ad = new AlipayDialog(UnLogin.this);
			ad.setOkBtn("确定", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					UnLogin.this.finish();
				}
			});
			ad.setCancelBtn("取消", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ad.dismiss();
					Toast.makeText(UnLogin.this, "您点击了取消", Toast.LENGTH_SHORT).show();
				}
			});
		}else{
			ad.show();
		}
		ad.setMessage("您是否要退出支付宝客户端？");
	}
	
	private void bindUI(){
		Button register = (Button) findViewById(R.id.register);
		Button login = (Button) findViewById(R.id.login);
		View.OnClickListener registerListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UnLogin.this, Register.class);
				startActivity(intent);
			}
		};
		View.OnClickListener loginListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UnLogin.this, Logins.class);
				startActivity(intent);
			}
		};
		register.setOnClickListener(registerListener);
		login.setOnClickListener(loginListener);
	}
}
