package com.maomao.project.qfix;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/*
* 最简单的报修界面的列表
*
* */
public class RepairListAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	
	private List<Repairs> mRepairList; // 新闻列表
	private LayoutInflater mInflater = null;

	public RepairListAdapter(Context context, List<Repairs> newsList) {

		mRepairList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mRepairList.size();
	}

	@Override
	public Object getItem(int position) {
		return mRepairList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<Repairs> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mRepairList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RepairHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.repair_item, null);
			newsHolder = new RepairHolder();
			newsHolder.tvRepairLabel = (TextView) convertView.findViewById(R.id.tv_photo);          //修理标签
			newsHolder.tvRepairTitle = (TextView) convertView.findViewById(R.id.tv_title);         //修理标题
			newsHolder.tvRepairDate = (TextView)convertView.findViewById(R.id.tv_time);            //报修时间
			newsHolder.tvRepairDetailed = (TextView)convertView.findViewById(R.id.tv_describe);    //修理详情
			convertView.setTag(newsHolder);
		} else {
			newsHolder = (RepairHolder) convertView.getTag();
		}
		newsHolder.tvRepairTitle.setText(mRepairList.get(position).getTitle());
		newsHolder.tvRepairDetailed.setText(mRepairList.get(position).getDetailed());
		newsHolder.tvRepairDate.setText(mRepairList.get(position).getCreatedAt());

		
		//设置标签
		String label =getLabelsText(mRepairList.get(position).getState());
		Log.d("Maomoa",label);
		newsHolder.tvRepairLabel.setText(label);
		if (label.equals("已修理")) {
			newsHolder.tvRepairLabel.setBackgroundColor(0xFF3CB371);
		} else if (label.equals("未修理")) {
			newsHolder.tvRepairLabel.setBackgroundColor(0xFFDC143C);
		} else{
			newsHolder.tvRepairLabel.setBackgroundColor(0xFFDC143C);
		}
		return convertView;
	}

	private String getLabelsText(String code) {
		if (code.equals("1")) {
			return "已修理";
		} else if (code.equals("2")) {
			return "未修理";
		} else {
			return "未修理";
		}
	}

}
