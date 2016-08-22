package com.maomao.project.qfix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class RepairMainActivity extends AppCompatActivity {

    private Repairs repairs;
    ImageView imageView;
    private String repairID;
    private String tupian;
    private TextView textView_title;
    private TextView textView_xiangxi;
    private TextView textView_name;
    private TextView textView_address;
    private TextView textView_phone;
    private TextView textView_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repair);
        imageView = (ImageView) findViewById(R.id.tutu);
        textView_title = (TextView)findViewById(R.id.tv_title);
        textView_xiangxi = (TextView)findViewById(R.id.tv_xiangxi);
        textView_address = (TextView)findViewById(R.id.tv_address);
        textView_name = (TextView)findViewById(R.id.tv_name);
        textView_phone = (TextView)findViewById(R.id.tv_telephone);
        textView_time = (TextView)findViewById(R.id.tv_time);
        repairs = (Repairs) getIntent().getSerializableExtra("repair");
        repairID = getIntent().getStringExtra("ID");
        tupian = repairs.getPhoto().getFileUrl();
        textView_title.setText(repairs.getTitle());
        textView_phone.setText(repairs.getPhonenum());
        textView_address.setText(repairs.getAddress());
        textView_name.setText(repairs.getName());
        textView_xiangxi.setText(repairs.getDetailed());
        textView_time.setText(repairs.getCreatedAt());
        Picasso.with(this)
                .load(tupian)
                .resize(600, 400)
                .centerCrop()
                .into(imageView);

        Log.d("BBB", repairID);



        //4个小图标的使用

        //地图图标
        findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RepairMainActivity.this,MainMapActivity.class);
                intent.putExtra("lat",repairs.getLat());
                intent.putExtra("lon",repairs.getLon());
                startActivity(intent);

            }
        });

        //删除列表
        findViewById(R.id.del_mes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repairs repairs = new Repairs();
                repairs.setObjectId(repairID);
                repairs.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null)
                        {
                            toast("删除成功请返回下拉刷新");
                        }
                        else
                        {
                            toast("数据删除失败！");
                        }
                    }
                });

            }
        });

    }


    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

}
