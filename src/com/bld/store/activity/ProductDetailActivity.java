package com.bld.store.activity;


import com.bld.store.R;
import com.bld.data.DataBuilder;
import com.bld.object.Product;
import com.bld.task.HttpResourcesTask;
import com.bld.task.HttpResourcesTask.CacheType;
import com.bld.task.HttpResourcesTask.HttpType;
import com.bld.task.Task;
import com.bld.task.Task.OnFinishListen;
import com.bld.utils.ConnectionUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductDetailActivity extends Activity {

	Product product;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.activity_product_detail);
		}
		catch(Exception ex)
		{
			 Log.e("更新", ex.getMessage());
		}
		Intent intent=getIntent();
		product = (Product)intent.getSerializableExtra("selected_product");  
	   
	    TextView  title=(TextView) findViewById(R.id.info_name);
	    title.setText(product.getName());
	    TextView  description=(TextView) findViewById(R.id.info_text);
	    description.setText(product.getDescription());
	    final EditText num_text = ((EditText) findViewById(R.id.num));
	    num_text.setText(String.valueOf(1));
	    Button add = (Button) findViewById(R.id.btn_plus);
		add.setOnClickListener(numButtonClickListen);

		Button subtract = (Button) findViewById(R.id.btn_subtract);
		subtract.setOnClickListener(numButtonClickListen);
		
	    HttpResourcesTask task = new HttpResourcesTask(this, HttpType.Img,
				CacheType.saveInSDcard);
		task.setParameter(ConnectionUtils.getInstance().ImageUrl+product.getImg())
				.setOnFinishListen(new OnFinishListen() {

					@Override
					public void OnFinish(Task t, Object data) {
						// TODO Auto-generated method stub
						if (data != null
								&& !(data instanceof Exception)) {
							ImageView imageView = (ImageView) findViewById(R.id.proImg);
							Drawable d= (Drawable) t.getResult();
							int sWidth = d.getIntrinsicWidth();

							int height = (int) (d.getIntrinsicHeight());
							
							imageView.setImageDrawable(d);

						}
						
					}
				});
		task.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}
	
	OnClickListener numButtonClickListen = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			LinearLayout vwParentRow = (LinearLayout) view.getParent();
			LinearLayout parentRow = (LinearLayout) view.getParent()
					.getParent().getParent();
			EditText child = (EditText) vwParentRow.getChildAt(1);
		
			int num = Integer.parseInt(child.getText().toString());
		
			if (view.getId() == R.id.btn_plus) {

				num++;
			} else {
				if (num > 1) {
					num--;
				}

			}
		
			child.setText(String.valueOf(num));

		}
	};
}
