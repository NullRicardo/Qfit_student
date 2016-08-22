package com.maomao.project.qfix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;





//已经废弃工程
public class MenuMainActivity extends AppCompatActivity {

    private SlidingMenu slidingMenu;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_main);
        slidingMenu = new SlidingMenu(this);                         //创建一个slidingmenu对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设置左边对齐
        //slidingMenu.setShadowDrawable(R.drawable.png21);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenusize);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);        //全屏触摸
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);         //附加使用
        slidingMenu.setMenu(R.layout.slidingmenu);//布局资源的使用
        imageView1 = (ImageView) findViewById(R.id.btn1);
        imageView2 = (ImageView) findViewById(R.id.btn2);
        imageView3 = (ImageView) findViewById(R.id.btn3);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("hello");
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("你想干嘛？");
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("我的个人中心");
                Intent intent = new Intent(MenuMainActivity.this,MainMineActivity.class);
                startActivity(intent);
            }
        });




    }
    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        Toast.makeText(this, "确定要退出小fic?", Toast.LENGTH_LONG).show();
        //super.onBackPressed();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
        case KeyEvent.KEYCODE_MENU:
        slidingMenu.toggle(true);
            break;
        default:
            break;

        }
        return super.onKeyDown(keyCode,event);
    }
}

