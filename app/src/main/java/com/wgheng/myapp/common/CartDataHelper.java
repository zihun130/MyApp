package com.wgheng.myapp.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wgheng.myapp.shop.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgheng on 2017/7/11.
 */

public class CartDataHelper {

    private List<CartBean> cartBeans;
    private DbHelper dbHelper;

    private CartDataHelper() {
    }

    public static CartDataHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static CartDataHelper instance = new CartDataHelper();
    }

    public void initDB(Context context) {
        dbHelper = new DbHelper(context);
    }

//    public static final String KEY = "key";
//    public static final String GOODS_NAME = "goodsName";
//    public static final String PRICE = "price";
//    public static final String ORIGIN_PRICE = "originPrice";
//    public static final String IMAGE_PATH = "imagePath";
//    public static final String GOODS_PATH = "goodsPath";
//    public static final String TYPES = "types";
//    public static final String COUNT = "count";

    //查询数据库添加到集合中
    public void initData() {

        cartBeans = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TableCart.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int count = cursor.getInt(cursor.getColumnIndex(TableCart.COUNT));
                String key = cursor.getString(cursor.getColumnIndex(TableCart.KEY));
                String goodsName = cursor.getString(cursor.getColumnIndex(TableCart.GOODS_NAME));
                String price = cursor.getString(cursor.getColumnIndex(TableCart.PRICE));
                String originPrice = cursor.getString(cursor.getColumnIndex(TableCart.ORIGIN_PRICE));
                String imagePath = cursor.getString(cursor.getColumnIndex(TableCart.IMAGE_PATH));
                String goodsPath = cursor.getString(cursor.getColumnIndex(TableCart.GOODS_PATH));
                String types = cursor.getString(cursor.getColumnIndex(TableCart.TYPES));

                CartBean cartBean = new CartBean();
                cartBean.setCount(count);
                cartBean.setKey(key);
                cartBean.setGoodsName(goodsName);
                cartBean.setPrice(price);
                cartBean.setOriginPrice(originPrice);
                cartBean.setImagePath(imagePath);
                cartBean.setGoodsPath(goodsPath);
                // cartBean.setTypes();
                cartBeans.add(cartBean);
            }
        }
        cursor.close();

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
                int newCount = cartBeans.get(i).getCount() + cartBean.getCount();
                //更新内存
                cartBeans.get(i).setCount(newCount);
                //更新数据库
                updateDB(cartBeans.get(i).getKey(),newCount);
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

    public void updateData(int position, int count) {
        //更新内存
        cartBeans.get(position).setCount(count);
        //更新数据库
        updateDB(cartBeans.get(position).getKey(),count);
    }

    //保存到数据库一份
    private void addToDB(CartBean cartBean) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableCart.COUNT, cartBean.getCount());
        values.put(TableCart.KEY, cartBean.getKey());
        values.put(TableCart.GOODS_NAME, cartBean.getGoodsName());
        values.put(TableCart.PRICE, cartBean.getPrice());
        values.put(TableCart.ORIGIN_PRICE, cartBean.getOriginPrice());
        values.put(TableCart.IMAGE_PATH, cartBean.getImagePath());
        values.put(TableCart.GOODS_PATH, cartBean.getGoodsPath());
        //  values.put(TableCart.TYPES,);
        db.insert(TableCart.TABLE_NAME, null, values);
    }

    //从数据库删除
    private void deleteFromDB(CartBean cartBean) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(TableCart.TABLE_NAME, "key=?", new String[]{cartBean.getKey()});
    }

    //更新数据库
    private void updateDB(String key,int newCount) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableCart.COUNT, newCount);
        db.update(TableCart.TABLE_NAME, values, "key=?", new String[]{key});
    }


}
