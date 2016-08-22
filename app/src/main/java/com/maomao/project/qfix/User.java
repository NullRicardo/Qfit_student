package com.maomao.project.qfix;

import cn.bmob.v3.BmobUser;

/**
 * Created by Maomao on 2016/8/5.
 */
public class User extends BmobUser {
    // 父类中已经存在的属性
    // private String id;
    // private String username;
    // private String password;
    // private String email;
    // private String regTime;

    private  String phone;    //个人电话
    private  String dorPart;   //寝室楼
    private  String dorNum;     //寝室号
    private String state = "未登陆"; 		// 登录状态
    private String type = "普通用户";		// 用户类型(普通用户、黑名单、中奖者)

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDorNum() {
        return dorNum;
    }

    public String getDorPart() {
        return dorPart;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public void setDorNum(String dorNum) {
        this.dorNum = dorNum;
    }

    public void setDorPart(String dorPart) {
        this.dorPart = dorPart;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

}
