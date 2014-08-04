package com.bld.store.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.bld.adapter.WaiterAdapter;
import com.bld.data.DataBuilder;
import com.bld.object.Product;
import com.bld.object.Waiter;
import com.bld.store.R;
import com.bld.task.TaskQueue;
import com.bld.utils.UserInfoCache;



@SuppressLint("HandlerLeak")
public class EmployeeActivity extends Activity {
	
	public ArrayList<Waiter> WaiterList = new ArrayList<Waiter>();
	ListView list;
	WaiterAdapter listadpter;
	View header;
	View footer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee);
		header=LayoutInflater.from(this).inflate(R.layout.store_info, null);
		footer=LayoutInflater.from(this).inflate(R.layout.add_employee, null);
		
		TextView storename= (TextView)header.findViewById(R.id.storename);
		storename.setText("可购便利");
		((TextView)header.findViewById(R.id.storeadd)).setText(UserInfoCache.getInstance().getAddress());
		((TextView)header.findViewById(R.id.storephone)).setText("023-88688688");
		list = (ListView) findViewById(R.id.employee_list);
		TextView add_waiter = (TextView)footer.findViewById(R.id.new_employee);
		add_waiter.setOnClickListener(add_waiter_click);
		
		
		list.addHeaderView(header);
		list.addFooterView(footer);
		listadpter = new WaiterAdapter(this);
		new Thread(getWaiter).start();
	}


	Runnable getWaiter = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				WaiterList = DataBuilder.GetInstence().getWaiterByStore("ST000002");
				handler.sendMessage(handler.obtainMessage(25, ""));
			} catch (UnsupportedEncodingException e) {
				// / TODO Auto-generated catch block
				Log.e("错误", e.getMessage());
			}

		}
	};

	Handler handler = new Handler() {
		@Override
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			list.setAdapter(listadpter);
		}
	};

	OnClickListener add_waiter_click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
            View view=inflater.inflate(R.layout.waiter_dialog, null);  
            AlertDialog.Builder builder2=new AlertDialog.Builder(EmployeeActivity.this);  
            builder2.setView(view);  
            builder2.setTitle("添加店小二").setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                  
                @Override  
                public void onClick(DialogInterface dialog, int which) {  
                    // TODO Auto-generated method stub  
                    dialog.cancel();  
                }  
            });
            builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
                
              @Override  
              public void onClick(DialogInterface dialog, int which) {  
                  // TODO Auto-generated method stub  
                  dialog.cancel();  
              }  
          }).create().show();  
			
		}
	};
}
