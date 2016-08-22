package com.maomao.project.qfix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class RepairActivity extends Activity implements OnItemClickListener,
		SwipeRefreshLayout.OnRefreshListener {

	@SuppressWarnings("unused")
	private static final String TAG = "RepairActivity";

	private ListView lvRepairs;
	private RepairListAdapter mRepairListAdapter;
	private List<Repairs> mRepairsList;
	private SwipeRefreshLayout swipeLayout;
	private String name_order;

	// 下拉刷新
	private static final int STATE_REFRESH = 0;// 下拉刷新
	@SuppressWarnings("unused")
	private static final int STATE_MORE = 1;// 加载更多
	private int limit = 10; // 每页的数据是10条
	private int curPage = 0; // 当前页的编号，从0开始

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair);
		Bmob.initialize(this, "a816a77868e6ca75cacbb28d5f67a720");
		initView();
		queryData(0, STATE_REFRESH);

	}

	private void initView() {
		
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.lv_shop_all_swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
		
		lvRepairs = (ListView) findViewById(R.id.lv_bxt_news);
		mRepairsList = new ArrayList<Repairs>();
		mRepairListAdapter = new RepairListAdapter(this, mRepairsList);
		lvRepairs.setAdapter(mRepairListAdapter);
		lvRepairs.setOnItemClickListener(this);
	}

	@SuppressWarnings("unused")
	
	/**
	 * 分页获取数据
	 * @param page	页码
	 * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
	 */
	private void queryData(final int page, final int actionType){
		Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);
		BmobUser user = BmobUser.getCurrentUser();
		name_order=(String)BmobUser.getObjectByKey("username");
		BmobQuery<Repairs> query = new BmobQuery<Repairs>();
		query.addWhereEqualTo("username",name_order);
		query.addWhereEqualTo("state","1");
		//query.order("topic");
		//query.setLimit(limit);			// 设置每页多少条数据
		//query.setSkip(page*limit);		// 从第几条数据开始，
		query.findObjects(new FindListener<Repairs>() {
			@Override
			public void done(List<Repairs> list, BmobException e) {
			if(e==null){
				if (list.size() == 0)
					toast("亲, 您没有个人报修");
				else {
					mRepairsList = list;
					mRepairListAdapter.refresh(list);
					mRepairListAdapter.notifyDataSetChanged();
				}

			}else {
				Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
			}
			}
		});
	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Intent toShopItem = new Intent(RepairActivity.this, RepairMainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("repair", mRepairsList.get(position) );
		bundle.putString("ID", mRepairsList.get(position).getObjectId()); //ID需要单独传递,否则获取到的是null
		toShopItem.putExtras(bundle);
		startActivity(toShopItem);
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(true);
				queryData(curPage, STATE_REFRESH);
				swipeLayout.setRefreshing(false);
			}
		}, 1000);
	}

}
