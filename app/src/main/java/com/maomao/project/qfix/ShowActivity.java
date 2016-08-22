package com.maomao.project.qfix;

        import android.content.Intent;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import cn.bmob.v3.Bmob;
/*
主活动  开启活动
 */

public class ShowActivity extends AppCompatActivity {

    private static final String APPID = "a816a77868e6ca75cacbb28d5f67a720";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, APPID);
        setContentView(R.layout.activity_show);

//		// 使用推送服务时的初始化操作
//		BmobInstallation.getCurrentInstallation(this).save();
//		// 启动推送服务
//		BmobPush.startWork(this, APPID);

        mHandler.sendEmptyMessageDelayed(GO_LOGIN, 3000);
    }

    private static final int GO_HOME = 100;
    private static final int GO_LOGIN = 200;

    @SuppressWarnings("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    break;
                case GO_LOGIN:
                    Intent intent = new Intent(ShowActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

}
