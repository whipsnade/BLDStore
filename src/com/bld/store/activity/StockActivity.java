package com.bld.store.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bld.store.R;
import com.bld.task.TaskQueue;



@SuppressLint("HandlerLeak")
public class StockActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock);
		TextView text = (TextView)findViewById(R.id.add_product);
		text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				intent.setClass(StockActivity.this, CaptureActivity.class);

				intent.putExtra("str", "come from first activity");

				startActivity(intent);
			}
		});
		//
		//
//		
//		TextView today = (TextView)findViewById(R.id.todaysales);
//		today.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(context, TodayActivity.class);
//				startActivity(intent);
//			}
//		});
	}




	

}
