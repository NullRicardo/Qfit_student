package com.maomao.project.qfix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainMineActivity extends Activity implements OnItemClickListener {
    @SuppressWarnings("unused")
    private static final String TAG = "MineActivity" ;

    private String[] userItemNames = {"stonekity"} ;
    private String[] userItemContents = {""} ;
    private String[] orderItemNames = {"当前报修", "历史报修"};
    private String[] orderItemContents = {"*", ""};
    private String[] aboutItemNames = {"通知中心", "软件相关", "推荐给朋友", "退出账号"};
    private String[] aboutItemContents = {"", "", "", ""};

    private int[] userImgIds = {R.drawable.ic_menu_myplaces};
    private int[] orderImgIds = {R.drawable.ic_menu_find_holo_light, R.drawable.ic_menu_copy_holo_light};
    private int[] aboutImgIds = {R.drawable.ic_menu_notifications, R.drawable.ic_menu_info_details, R.drawable.ic_menu_share, R.drawable.ic_star_yes};

    private ListView lvMineUser;
    private ListView lvMineOrder;
    private ListView lvMineAbout;
    private MineListAdapter userListAdapter;
    private MineListAdapter orderListAdapter;
    private MineListAdapter aboutListAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageDef.MINE_FINISH_LOAD_DATA:
                    toast("Handler 收到数据加载完成的消息");
                    orderListAdapter.notifyDataSetChanged();
                    /*外部方法控制适配器内容的更新*/
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        Bmob.initialize(this,"a816a77868e6ca75cacbb28d5f67a720");
        initData();
        initView();
    }

    private void initView() {

        lvMineUser = (ListView) findViewById(R.id.lv_mine_user);
        lvMineOrder = (ListView) findViewById(R.id.lv_mine_order);
        lvMineAbout = (ListView) findViewById(R.id.lv_mine_about);

        userListAdapter = new MineListAdapter(this, userItemNames, userItemContents, userImgIds);
        orderListAdapter = new MineListAdapter(this, orderItemNames, orderItemContents, orderImgIds);
        aboutListAdapter = new MineListAdapter(this, aboutItemNames, aboutItemContents, aboutImgIds);

        lvMineUser.setAdapter(userListAdapter);
        lvMineOrder.setAdapter(orderListAdapter);
        lvMineAbout.setAdapter(aboutListAdapter);

        lvMineUser.setOnItemClickListener(this);
        lvMineOrder.setOnItemClickListener(this);
        lvMineAbout.setOnItemClickListener(this);

    }

    //初始化你的资料
    public void initData() {
        //获取用户-----------注意 不完善
        BmobUser user = BmobUser.getCurrentUser();

        //userItemNames[0] = user.getUsername();
        userItemNames[0]=(String)BmobUser.getObjectByKey("username");
        //userItemNames[0]="maomao";



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //个人资料
        if(parent.getId() == R.id.lv_mine_user) {
            switch (position) {
                case 0:		//资料卡
                    //toast("点击个人资料");
//                    Intent toMineInfo = new Intent(MineActivity.this, MineInfoActivity.class);
//                    startActivity(toMineInfo);
                    break;

                default:
                    break;
            }
        }

        //订单
        if(parent.getId() == R.id.lv_mine_order) {
            //toast("点击了订单区域");
            Intent toOrderInfo;
            switch (position) {
                case 0:
                    toOrderInfo = new Intent(MainMineActivity.this, RepairActivity1.class);
                    startActivity(toOrderInfo);
                    break;
                case 1:
                    toOrderInfo = new Intent(MainMineActivity.this, RepairActivity.class);
                    startActivity(toOrderInfo);
                    break;
                default:
                    break;
            }
        }

        //其他
        if(parent.getId() == R.id.lv_mine_about) {

            switch (position) {
                case 1: 	//软件相关
//                    Intent toMineSoft = new Intent(MineActivity.this, MineSoftActivity.class);
//                    startActivity(toMineSoft);
                    break;
                case 2:		//推荐给朋友
                    Intent toShare = new Intent(Intent.ACTION_SEND);
                    toShare.setType("text/plain");
                    toShare.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    toShare.putExtra(Intent.EXTRA_TEXT, "Qfit最火爆的校园类修理app" +"\n" + "针对校园修理的软件"
                            + "http://qfixer.bmob.cn");
                    startActivity(Intent.createChooser(toShare, "分享到"));
                    break;
                case 3:		//退出当期账号
//                    BmobUser.logOut(this);
//                    Intent toLogin = new Intent(MineActivity.this, LoginActivity.class);
//                    startActivity(toLogin);
                    finish();
                    break;

                default:
                    //toast("点击了通知区域");
                    break;
            }

        }

    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }


}
