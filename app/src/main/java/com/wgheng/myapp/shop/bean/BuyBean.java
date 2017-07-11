package com.wgheng.myapp.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wgheng on 2017/7/11.
 */

public class BuyBean implements Serializable {

    private String tag;
    private String brand;
    private String name;
    private String price;
    private String originPrice;
    private String goodsPath;

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getGoodsPath() {
        return goodsPath;
    }

    public void setGoodsPath(String goodsPath) {
        this.goodsPath = goodsPath;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    private String defaultImage;

    public List<GoodsBean.DataBean.ItemsBean.SkuInfoBean> getSkuInfoBeans() {
        return skuInfoBeans;
    }

    public void setSkuInfoBeans(List<GoodsBean.DataBean.ItemsBean.SkuInfoBean> skuInfoBeans) {
        this.skuInfoBeans = skuInfoBeans;
    }

    private List<GoodsBean.DataBean.ItemsBean.SkuInfoBean> skuInfoBeans;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    //public static class SkuInfoBean  {}



//    private ArrayList<TypeBean> colorBeans;
//    private ArrayList<TypeBean> clothesBeans;
//    private ArrayList<TypeBean> numberBeans;
//
//    public ArrayList<TypeBean> getColorBeans() {
//        return colorBeans;
//    }
//
//    public void setColorBeans(ArrayList<TypeBean> colorBeans) {
//        this.colorBeans = colorBeans;
//    }
//
//    public ArrayList<TypeBean> getClothesBeans() {
//        return clothesBeans;
//    }
//
//    public void setClothesBeans(ArrayList<TypeBean> clothesBeans) {
//        this.clothesBeans = clothesBeans;
//    }
//
//    public ArrayList<TypeBean> getNumberBeans() {
//        return numberBeans;
//    }
//
//    public void setNumberBeans(ArrayList<TypeBean> numberBeans) {
//        this.numberBeans = numberBeans;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public static class TypeBean implements Serializable{
//
//        /**
//         * attr_id : 1
//         * attr_name : 黑色
//         * img_path : http://imgs-qn.iliangcang.com/ware/upload/big/2/382/382249.jpg?59197.310684861535
//         */
//
//        private String attr_id;
//        private String attr_name;
//        private String img_path;
//
//        public String getAttr_id() {
//            return attr_id;
//        }
//
//        public void setAttr_id(String attr_id) {
//            this.attr_id = attr_id;
//        }
//
//        public String getAttr_name() {
//            return attr_name;
//        }
//
//        public void setAttr_name(String attr_name) {
//            this.attr_name = attr_name;
//        }
//
//        public String getImg_path() {
//            return img_path;
//        }
//
//        public void setImg_path(String img_path) {
//            this.img_path = img_path;
//        }
//    }
}
