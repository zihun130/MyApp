package com.wgheng.myapp.common;

/**
 * Created by wgheng on 2017/7/12.
 */

public class TableCart {

    public static final String TABLE_NAME ="cart_list";
    public static final String KEY = "key";
    public static final String GOODS_NAME = "goodsName";
    public static final String PRICE = "price";
    public static final String ORIGIN_PRICE = "originPrice";
    public static final String COUNT = "count";
    public static final String IMAGE_PATH = "imagePath";
    public static final String GOODS_PATH = "goodsPath";
    public static final String TYPES = "types";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( "
            + KEY + " text primary key,"
            + GOODS_NAME + " text,"
            + PRICE + " text,"
            + ORIGIN_PRICE + " text,"
            + COUNT + " integer,"
            + IMAGE_PATH + " text,"
            + GOODS_PATH + " text,"
            + TYPES + " text)";
}
