package com.wgheng.myapp.shop.bean;

import java.io.Serializable;

/**
 * Created by wgheng on 2017/7/11.
 */

public class CartBean implements Serializable{

    //固定字段
    private String key;
    private String goodsName;
    private String price;
    private String originPrice;
    private int count;
    private String imagePath;
    private String goodsId;
   // private HashMap<String, String> types;
    private String types;
    private boolean isChecked = true;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
//
//    public HashMap<String, String> getTypes() {
//        return types;
//    }
//
//    public void setTypes(HashMap<String, String> types) {
//        this.types = types;
//    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
