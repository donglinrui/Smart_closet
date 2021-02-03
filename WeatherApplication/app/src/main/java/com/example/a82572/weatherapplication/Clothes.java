package com.example.a82572.weatherapplication;


import org.litepal.crud.DataSupport;

public class Clothes extends DataSupport{
    private int id;
    private String my_kind;     //款式
    private int keep_warm;          //保暖属性
    private int love_value;         //偏好值
    private String address;         //图片地址
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String add)
    {
        address=add;
    }
    public String getMy_kind()
    {
        return my_kind;
    }
    public void setMy_kind(String k)
    {
        my_kind=k;
    }
    public int getKeep_warm()
    {
        return keep_warm;
    }
    public void setKeep_warm(int keep)
    {
        keep_warm=keep;
    }
    public int getLove_value()
    {
        return love_value;
    }
    public void setLove_value(int i)
    {
        love_value=i;
    }
}
