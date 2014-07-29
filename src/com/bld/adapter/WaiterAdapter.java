package com.bld.adapter;

import com.bld.object.Waiter;
import com.bld.store.R;
import com.bld.store.activity.EmployeeActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class WaiterAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater = null;
	EmployeeActivity ac;
	ArrayAdapter adapter;
	Integer tasksOver = 0;
	Integer tasksLength = 0;
	public int count = 1;  
	private static final String[] m = { "现付", "网络支付" };

	public WaiterAdapter(Context c) {
		mContext = c;
		// multiChoose = isMulti;
		this.mInflater = LayoutInflater.from(c);
		ac = (EmployeeActivity) mContext;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ac.WaiterList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Waiter waiter = (Waiter) ac.WaiterList.get(position);
		
		Log.e("position",String.valueOf(position));
		if (convertView == null) {
			convertView = this.mInflater.inflate(R.layout.employee_item,
					null);
		}
		
		
	
		
		
		((TextView) convertView.findViewById(R.id.employee_name)).setText(waiter.getName());
			

		final CheckBox radio1 = (CheckBox) convertView
				.findViewById(R.id.accept_role);
		radio1.setTag(position);
		if(waiter.getAccept()==1){
			radio1.setChecked(true);
		}else{
			radio1.setChecked(false);
		}
		
		
		radio1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// 重置，确保最多只有一项被选中
				CheckBox temp = (CheckBox) v;
				if(temp.isChecked()){
					ac.WaiterList.get((Integer)v.getTag()).setAccept((short) 1);
				}
				else{
					ac.WaiterList.get((Integer)v.getTag()).setAccept((short) 0);
				}
				WaiterAdapter.this.notifyDataSetChanged();
			}
		});

		
		final CheckBox radio2 = (CheckBox) convertView
				.findViewById(R.id.stock_role);
		radio2.setTag(position);
		if(waiter.getStock()==1){
			radio2.setChecked(true);
		}else{
			radio2.setChecked(false);
		}
		
		
		radio2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// 重置，确保最多只有一项被选中
				CheckBox temp = (CheckBox) v;
				if(temp.isChecked()){
					ac.WaiterList.get((Integer)v.getTag()).setStock((short) 1);
				}
				else{
					ac.WaiterList.get((Integer)v.getTag()).setStock((short) 0);
				}
				WaiterAdapter.this.notifyDataSetChanged();
			}
		});
		return convertView;
	}

	public void changeState(int position) {

		notifyDataSetChanged();
	}

}
