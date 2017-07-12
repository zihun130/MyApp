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

    public void addData(CartBean cartBean) {

        //添加到内存
        boolean hasSame = false;
        for (int i = 0; i < cartBeans.size(); i++) {
            if (cartBeans.get(i).getKey().equals(cartBean.getKey())) {
                cartBeans.get(i).setCount(cartBeans.get(i).getCount() + cartBean.getCount());
                updateDB(cartBean);
                hasSame = true;
            }
        }

        if (!hasSame) {
            cartBeans.add(cartBean);
            //添加到数据库
            addToDB(cartBean);
        }
    }

    public void removeData(CartBean cartBean) {
        //更新内存
        cartBeans.remove(cartBean);
        //更新数据库
        deleteFromDB(cartBean);
    }

    public void updateData(int position,int count) {
        //更新内存
        cartBeans.get(position).setCount(count);
        //更新数据库
        updateDB(cartBeans.get(position));
    }

    //查询数据库添加到集合中
    public void init() {

    }

    //保存到数据库一份
    private void addToDB(CartBean cartBean) {

    }

    //从数据库删除
    private void deleteFromDB(CartBean cartBean) {

    }

    //更新数据库
    private void updateDB(CartBean cartBean) {

    }


}
