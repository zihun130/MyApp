package com.wgheng.myapp.common;

import com.wgheng.myapp.shop.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgheng on 2017/7/11.
 */

public class CartDataHelper {

    private List<CartBean> cartBeans;

    private CartDataHelper() {
        cartBeans = new ArrayList<>();
    }

    public static CartDataHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static CartDataHelper instance = new CartDataHelper();
    }


    //获取集合数据
    public List<CartBean> getCartBeans() {
        return cartBeans;
    }

    //查询数据库添加到集合中
    public void init() {

    }

    //保存到数据库一份
    public void addToDB(CartBean cartBean) {

    }

    //从数据库删除
    public void deleteFromDB(int position) {

    }

    //更新数据库
    public void updateDB(CartBean cartBean) {

    }


}
