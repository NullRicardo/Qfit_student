package com.maomao.project.qfix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private Button btnReg;          //注册按钮
    private EditText etUsername;    //用户名
    private EditText etPassword;    // 密码框
    private EditText etComfirmPsd;  //2密码
    private EditText etPhone;       //手机号框

    private String username = null;    //用户名
    private String password = null;   //密码
    private String comfirmPsd = null;  //验证密码
    private String phone = null;        //电话号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
        etPhone = (EditText) findViewById(R.id.et_phone);

        btnReg = (Button) findViewById(R.id.btn_reg_now);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                comfirmPsd = etComfirmPsd.getText().toString();
                phone = etPhone.getText().toString();
                if(!Util.isNetworkConnected(RegisterActivity.this)) {
                    toast("亲, 木有网络 ( ⊙ o ⊙ ) ");
                } else if (username.equals("") || password.equals("")
                        || comfirmPsd.equals("") || phone.equals("")) {
                    toast("亲, 不填完整, 不能拿到身份证, ~~~~(>_<)~~~~ ");
                } else if (!comfirmPsd.equals(password)) {
                    toast("亲, 说你手抖了下, 两次密码输入不一致");
                } else if(!Util.isPhoneNumberValid(phone)) {
                    toast("亲, 请输入正确的手机号码");
                }else {
                    BmobUser bu = new BmobUser();
                    bu.setUsername(username);
                    bu.setPassword(password);
                    bu.setMobilePhoneNumber(phone);
                    bu.signUp(new SaveListener<User>() {

                        @Override
                        public void done(User user, BmobException e) {
                            if(e==null)
                            {
                                toast("拿到通行证了, 一起登陆里约奥运会去吧");
                                Intent backLogin = new Intent(RegisterActivity.this,
                                        LoginActivity.class);
                                startActivity(backLogin);
                                RegisterActivity.this.finish();
                            }
                            else {
                                toast("亲, 被人捷足先登了, 换个名字吧.");

                            }


                        }
                    });

                }

            }
        });


    }

    //重写的Toast
    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    };
}
