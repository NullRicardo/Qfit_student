package com.maomao.project.qfix;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private Button btnlog;   //login登陆按钮
    private Button btnRe;    //申请账号
    private ImageView imageView;   //小彩蛋
    private EditText editpass;    //密码输入框
    private EditText editname;    //账号输入窗口
    private String username;
    private String password;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);




        //获取实例
        btnlog = (Button)findViewById(R.id.btn_login);
        btnRe = (Button)findViewById(R.id.btn_apply);
        editname = (EditText)findViewById(R.id.et_username);
        editpass = (EditText)findViewById(R.id.et_password);
        imageView = (ImageView)findViewById(R.id.img_welcome);
        //申请按钮的使用
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //用户体验
        findViewById(R.id.keybord_yin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        });

        //小彩蛋彩蛋
        editpass.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    imageView.setImageResource(R.drawable.wuyan2);
                  // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                  imageView.setImageResource(R.drawable.wuyan);
                }
            }

        });

        //马上登录事件的点击动作
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editname.getText().toString();
                password = editpass.getText().toString();
                if (!Util.isNetworkConnected(LoginActivity.this)) {
                    toast("Orzz小主没有网络！臣妾登陆不到");
                } else if (username.equals("") || password.equals("")) {
                    toast("Orzz小主您想偷渡吗？！！");
                } else {
                    BmobUser bu2 = new BmobUser();
                    bu2.setUsername(username);
                    bu2.setPassword(password);
                    bu2.login(new SaveListener<BmobUser>() {

                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e==null)
                            {
                                toast("登录成功");
                                saveUserInfo(username, password);
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                toast("密码与账号不匹配");
                            }

                        }

                    });
                }
            }
        });




        getUserInfo();
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
    private void getUserInfo() {
        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        editname.setText(sp.getString("username", null));
        editpass.setText(sp.getString("password", null));
    }

    //保存用户的登陆记录
    private void saveUserInfo(String username, String password) {
        SharedPreferences sp = getSharedPreferences("UserInfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }
}