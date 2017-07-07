package com.wgheng.myapp.shop.bean;

import java.util.List;

/**
 * Created by wgheng on 2017/7/7.
 */

public class GoodsInfo {
    /**
     * meta : {"status":0,"server_time":"2017-07-07 21:03:36","account_id":0,"cost":0.019361972808838,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":false,"num_items":1,"items":{"goods_id":"34230","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/34/34230.jpg","goods_url":"http://www.iliangcang.com/i/goods/?id=34230","goods_name":"水晶雪球 | 纯净无暇质感 雪花闪烁效果（银/白）","goods_desc":"尺寸：直径220mm x 高165mm \r\n填充物：泡沫（白色款）；闪光片（银色款）","price":"1550.00","comment_count":"0","like_count":"1253","liked":"0","owner_id":"0","shipping_type":"1","shipping_time":"3","shipping_str":"现货商品3天发货","is_overseas_goods":0,"brand_info":{"brand_id":"14","brand_name":"Maison Martin Margiela Line 13","brand_desc":"自从2009年离开自己的服装品牌Maison Martin Margiela后，这位解构大师似乎更加钟情于打造充满趣味又颠覆传统美学思维的日常之物。以0-23之间的数字命名的产品线中的\u201c13\u201d即\u201c物件及出版物\u201d就是这样的一个系列，在无数看似\u201c无用\u201d之物的设计中，重新点缀普通人的生活，创造专属于Ｍartin Margiela的白色世界。","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/14.jpg"},"owner_name":"Maison Martin Margiela Line 13","owner_desc":"","is_daren":"0","headimg":"http://imgs-qn.iliangcang.com/ware/brand/14.jpg","rec_reason":"这是一只超大号的透明水晶球，除了细小精致的闪光片或泡沫，没有任何内部装饰。反而更使人心灵澄净，当闪着灵光的雪花飘落，希望和自由也跟随着降临。","is_gift":"1","promotion_imgurl":"","discount_price":"","promotion_note":"","sku_info":[{"type_id":"1","type_name":"颜色","isColor":"1","attrList":[{"attr_id":"0","attr_name":"银色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg?80296.01348098367"},{"attr_id":"1","attr_name":"白色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg?9119.99496165663"}]}],"good_ship_rule":"","good_guide":{"title":"购物指南","content":"所有商品均为正品保证。\r\n中国大陆地区免运费，默认商家合作快递。\r\n蜡烛、液态品、手表等含电池产品无法空运，运输时间相较普通空运件会更久。\r\n如出现产品质量问题请在签收后72小时内联系客服。"},"category_id":"0","able_buy":"1","able_buy_note":"","images_item":["http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58107.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58113.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58119.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58124.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58117.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58120.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58149.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg"],"is_sold_out":"0","sold_out_img_url":"","sku_inv":[{"goods_sku_sn":"0101000000000000034230000100000000000000000000000000000000000000","type_keys":"1","attr_keys":"0","price":"1550.00","discount_price":"","amount":"13"},{"goods_sku_sn":"0101000000000000034230000100010000000000000000000000000000000000","type_keys":"1","attr_keys":"1","price":"1550.00","discount_price":"","amount":"16"}],"goods_info":[{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797691419657.jpg","width":1000,"height":1660,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797694436828.jpg","width":958,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797696240459.jpg","width":757,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797706907008.jpg","width":1000,"height":1000,"length":79}}]}}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-07 21:03:36
         * account_id : 0
         * cost : 0.019361972808838
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : false
         * num_items : 1
         * items : {"goods_id":"34230","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/34/34230.jpg","goods_url":"http://www.iliangcang.com/i/goods/?id=34230","goods_name":"水晶雪球 | 纯净无暇质感 雪花闪烁效果（银/白）","goods_desc":"尺寸：直径220mm x 高165mm \r\n填充物：泡沫（白色款）；闪光片（银色款）","price":"1550.00","comment_count":"0","like_count":"1253","liked":"0","owner_id":"0","shipping_type":"1","shipping_time":"3","shipping_str":"现货商品3天发货","is_overseas_goods":0,"brand_info":{"brand_id":"14","brand_name":"Maison Martin Margiela Line 13","brand_desc":"自从2009年离开自己的服装品牌Maison Martin Margiela后，这位解构大师似乎更加钟情于打造充满趣味又颠覆传统美学思维的日常之物。以0-23之间的数字命名的产品线中的\u201c13\u201d即\u201c物件及出版物\u201d就是这样的一个系列，在无数看似\u201c无用\u201d之物的设计中，重新点缀普通人的生活，创造专属于Ｍartin Margiela的白色世界。","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/14.jpg"},"owner_name":"Maison Martin Margiela Line 13","owner_desc":"","is_daren":"0","headimg":"http://imgs-qn.iliangcang.com/ware/brand/14.jpg","rec_reason":"这是一只超大号的透明水晶球，除了细小精致的闪光片或泡沫，没有任何内部装饰。反而更使人心灵澄净，当闪着灵光的雪花飘落，希望和自由也跟随着降临。","is_gift":"1","promotion_imgurl":"","discount_price":"","promotion_note":"","sku_info":[{"type_id":"1","type_name":"颜色","isColor":"1","attrList":[{"attr_id":"0","attr_name":"银色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg?80296.01348098367"},{"attr_id":"1","attr_name":"白色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg?9119.99496165663"}]}],"good_ship_rule":"","good_guide":{"title":"购物指南","content":"所有商品均为正品保证。\r\n中国大陆地区免运费，默认商家合作快递。\r\n蜡烛、液态品、手表等含电池产品无法空运，运输时间相较普通空运件会更久。\r\n如出现产品质量问题请在签收后72小时内联系客服。"},"category_id":"0","able_buy":"1","able_buy_note":"","images_item":["http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58107.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58113.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58119.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58124.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58117.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58120.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58149.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg"],"is_sold_out":"0","sold_out_img_url":"","sku_inv":[{"goods_sku_sn":"0101000000000000034230000100000000000000000000000000000000000000","type_keys":"1","attr_keys":"0","price":"1550.00","discount_price":"","amount":"13"},{"goods_sku_sn":"0101000000000000034230000100010000000000000000000000000000000000","type_keys":"1","attr_keys":"1","price":"1550.00","discount_price":"","amount":"16"}],"goods_info":[{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797691419657.jpg","width":1000,"height":1660,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797694436828.jpg","width":958,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797696240459.jpg","width":757,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797706907008.jpg","width":1000,"height":1000,"length":79}}]}
         */

        private boolean has_more;
        private int num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getNum_items() {
            return num_items;
        }

        public void setNum_items(int num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * goods_id : 34230
             * goods_image : http://imgs-qn.iliangcang.com/ware/goods/big/2/34/34230.jpg
             * goods_url : http://www.iliangcang.com/i/goods/?id=34230
             * goods_name : 水晶雪球 | 纯净无暇质感 雪花闪烁效果（银/白）
             * goods_desc : 尺寸：直径220mm x 高165mm
             填充物：泡沫（白色款）；闪光片（银色款）
             * price : 1550.00
             * comment_count : 0
             * like_count : 1253
             * liked : 0
             * owner_id : 0
             * shipping_type : 1
             * shipping_time : 3
             * shipping_str : 现货商品3天发货
             * is_overseas_goods : 0
             * brand_info : {"brand_id":"14","brand_name":"Maison Martin Margiela Line 13","brand_desc":"自从2009年离开自己的服装品牌Maison Martin Margiela后，这位解构大师似乎更加钟情于打造充满趣味又颠覆传统美学思维的日常之物。以0-23之间的数字命名的产品线中的\u201c13\u201d即\u201c物件及出版物\u201d就是这样的一个系列，在无数看似\u201c无用\u201d之物的设计中，重新点缀普通人的生活，创造专属于Ｍartin Margiela的白色世界。","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/14.jpg"}
             * owner_name : Maison Martin Margiela Line 13
             * owner_desc :
             * is_daren : 0
             * headimg : http://imgs-qn.iliangcang.com/ware/brand/14.jpg
             * rec_reason : 这是一只超大号的透明水晶球，除了细小精致的闪光片或泡沫，没有任何内部装饰。反而更使人心灵澄净，当闪着灵光的雪花飘落，希望和自由也跟随着降临。
             * is_gift : 1
             * promotion_imgurl :
             * discount_price :
             * promotion_note :
             * sku_info : [{"type_id":"1","type_name":"颜色","isColor":"1","attrList":[{"attr_id":"0","attr_name":"银色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg?80296.01348098367"},{"attr_id":"1","attr_name":"白色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg?9119.99496165663"}]}]
             * good_ship_rule :
             * good_guide : {"title":"购物指南","content":"所有商品均为正品保证。\r\n中国大陆地区免运费，默认商家合作快递。\r\n蜡烛、液态品、手表等含电池产品无法空运，运输时间相较普通空运件会更久。\r\n如出现产品质量问题请在签收后72小时内联系客服。"}
             * category_id : 0
             * able_buy : 1
             * able_buy_note :
             * images_item : ["http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58107.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58113.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58119.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58124.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58117.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58120.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58149.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg"]
             * is_sold_out : 0
             * sold_out_img_url :
             * sku_inv : [{"goods_sku_sn":"0101000000000000034230000100000000000000000000000000000000000000","type_keys":"1","attr_keys":"0","price":"1550.00","discount_price":"","amount":"13"},{"goods_sku_sn":"0101000000000000034230000100010000000000000000000000000000000000","type_keys":"1","attr_keys":"1","price":"1550.00","discount_price":"","amount":"16"}]
             * goods_info : [{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797691419657.jpg","width":1000,"height":1660,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797694436828.jpg","width":958,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797696240459.jpg","width":757,"height":1600,"length":79}},{"type":1,"content":{"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797706907008.jpg","width":1000,"height":1000,"length":79}}]
             */

            private String goods_id;
            private String goods_image;
            private String goods_url;
            private String goods_name;
            private String goods_desc;
            private String price;
            private String comment_count;
            private String like_count;
            private String liked;
            private String owner_id;
            private String shipping_type;
            private String shipping_time;
            private String shipping_str;
            private int is_overseas_goods;
            private BrandInfoBean brand_info;
            private String owner_name;
            private String owner_desc;
            private String is_daren;
            private String headimg;
            private String rec_reason;
            private String is_gift;
            private String promotion_imgurl;
            private String discount_price;
            private String promotion_note;
            private String good_ship_rule;
            private GoodGuideBean good_guide;
            private String category_id;
            private String able_buy;
            private String able_buy_note;
            private String is_sold_out;
            private String sold_out_img_url;
            private List<SkuInfoBean> sku_info;
            private List<String> images_item;
            private List<SkuInvBean> sku_inv;
            private List<GoodsInfoBean> goods_info;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_url() {
                return goods_url;
            }

            public void setGoods_url(String goods_url) {
                this.goods_url = goods_url;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getLiked() {
                return liked;
            }

            public void setLiked(String liked) {
                this.liked = liked;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getShipping_type() {
                return shipping_type;
            }

            public void setShipping_type(String shipping_type) {
                this.shipping_type = shipping_type;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getShipping_str() {
                return shipping_str;
            }

            public void setShipping_str(String shipping_str) {
                this.shipping_str = shipping_str;
            }

            public int getIs_overseas_goods() {
                return is_overseas_goods;
            }

            public void setIs_overseas_goods(int is_overseas_goods) {
                this.is_overseas_goods = is_overseas_goods;
            }

            public BrandInfoBean getBrand_info() {
                return brand_info;
            }

            public void setBrand_info(BrandInfoBean brand_info) {
                this.brand_info = brand_info;
            }

            public String getOwner_name() {
                return owner_name;
            }

            public void setOwner_name(String owner_name) {
                this.owner_name = owner_name;
            }

            public String getOwner_desc() {
                return owner_desc;
            }

            public void setOwner_desc(String owner_desc) {
                this.owner_desc = owner_desc;
            }

            public String getIs_daren() {
                return is_daren;
            }

            public void setIs_daren(String is_daren) {
                this.is_daren = is_daren;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getRec_reason() {
                return rec_reason;
            }

            public void setRec_reason(String rec_reason) {
                this.rec_reason = rec_reason;
            }

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getPromotion_imgurl() {
                return promotion_imgurl;
            }

            public void setPromotion_imgurl(String promotion_imgurl) {
                this.promotion_imgurl = promotion_imgurl;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public String getPromotion_note() {
                return promotion_note;
            }

            public void setPromotion_note(String promotion_note) {
                this.promotion_note = promotion_note;
            }

            public String getGood_ship_rule() {
                return good_ship_rule;
            }

            public void setGood_ship_rule(String good_ship_rule) {
                this.good_ship_rule = good_ship_rule;
            }

            public GoodGuideBean getGood_guide() {
                return good_guide;
            }

            public void setGood_guide(GoodGuideBean good_guide) {
                this.good_guide = good_guide;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getAble_buy() {
                return able_buy;
            }

            public void setAble_buy(String able_buy) {
                this.able_buy = able_buy;
            }

            public String getAble_buy_note() {
                return able_buy_note;
            }

            public void setAble_buy_note(String able_buy_note) {
                this.able_buy_note = able_buy_note;
            }

            public String getIs_sold_out() {
                return is_sold_out;
            }

            public void setIs_sold_out(String is_sold_out) {
                this.is_sold_out = is_sold_out;
            }

            public String getSold_out_img_url() {
                return sold_out_img_url;
            }

            public void setSold_out_img_url(String sold_out_img_url) {
                this.sold_out_img_url = sold_out_img_url;
            }

            public List<SkuInfoBean> getSku_info() {
                return sku_info;
            }

            public void setSku_info(List<SkuInfoBean> sku_info) {
                this.sku_info = sku_info;
            }

            public List<String> getImages_item() {
                return images_item;
            }

            public void setImages_item(List<String> images_item) {
                this.images_item = images_item;
            }

            public List<SkuInvBean> getSku_inv() {
                return sku_inv;
            }

            public void setSku_inv(List<SkuInvBean> sku_inv) {
                this.sku_inv = sku_inv;
            }

            public List<GoodsInfoBean> getGoods_info() {
                return goods_info;
            }

            public void setGoods_info(List<GoodsInfoBean> goods_info) {
                this.goods_info = goods_info;
            }

            public static class BrandInfoBean {
                /**
                 * brand_id : 14
                 * brand_name : Maison Martin Margiela Line 13
                 * brand_desc : 自从2009年离开自己的服装品牌Maison Martin Margiela后，这位解构大师似乎更加钟情于打造充满趣味又颠覆传统美学思维的日常之物。以0-23之间的数字命名的产品线中的“13”即“物件及出版物”就是这样的一个系列，在无数看似“无用”之物的设计中，重新点缀普通人的生活，创造专属于Ｍartin Margiela的白色世界。
                 * brand_logo : http://imgs-qn.iliangcang.com/ware/brand/14.jpg
                 */

                private String brand_id;
                private String brand_name;
                private String brand_desc;
                private String brand_logo;

                public String getBrand_id() {
                    return brand_id;
                }

                public void setBrand_id(String brand_id) {
                    this.brand_id = brand_id;
                }

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
                }

                public String getBrand_desc() {
                    return brand_desc;
                }

                public void setBrand_desc(String brand_desc) {
                    this.brand_desc = brand_desc;
                }

                public String getBrand_logo() {
                    return brand_logo;
                }

                public void setBrand_logo(String brand_logo) {
                    this.brand_logo = brand_logo;
                }
            }

            public static class GoodGuideBean {
                /**
                 * title : 购物指南
                 * content : 所有商品均为正品保证。
                 中国大陆地区免运费，默认商家合作快递。
                 蜡烛、液态品、手表等含电池产品无法空运，运输时间相较普通空运件会更久。
                 如出现产品质量问题请在签收后72小时内联系客服。
                 */

                private String title;
                private String content;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class SkuInfoBean {
                /**
                 * type_id : 1
                 * type_name : 颜色
                 * isColor : 1
                 * attrList : [{"attr_id":"0","attr_name":"银色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg?80296.01348098367"},{"attr_id":"1","attr_name":"白色","img_path":"http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58125.jpg?9119.99496165663"}]
                 */

                private String type_id;
                private String type_name;
                private String isColor;
                private List<AttrListBean> attrList;

                public String getType_id() {
                    return type_id;
                }

                public void setType_id(String type_id) {
                    this.type_id = type_id;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }

                public String getIsColor() {
                    return isColor;
                }

                public void setIsColor(String isColor) {
                    this.isColor = isColor;
                }

                public List<AttrListBean> getAttrList() {
                    return attrList;
                }

                public void setAttrList(List<AttrListBean> attrList) {
                    this.attrList = attrList;
                }

                public static class AttrListBean {
                    /**
                     * attr_id : 0
                     * attr_name : 银色
                     * img_path : http://imgs-qn.iliangcang.com/ware/upload/big/2/58/58126.jpg?80296.01348098367
                     */

                    private String attr_id;
                    private String attr_name;
                    private String img_path;

                    public String getAttr_id() {
                        return attr_id;
                    }

                    public void setAttr_id(String attr_id) {
                        this.attr_id = attr_id;
                    }

                    public String getAttr_name() {
                        return attr_name;
                    }

                    public void setAttr_name(String attr_name) {
                        this.attr_name = attr_name;
                    }

                    public String getImg_path() {
                        return img_path;
                    }

                    public void setImg_path(String img_path) {
                        this.img_path = img_path;
                    }
                }
            }

            public static class SkuInvBean {
                /**
                 * goods_sku_sn : 0101000000000000034230000100000000000000000000000000000000000000
                 * type_keys : 1
                 * attr_keys : 0
                 * price : 1550.00
                 * discount_price :
                 * amount : 13
                 */

                private String goods_sku_sn;
                private String type_keys;
                private String attr_keys;
                private String price;
                private String discount_price;
                private String amount;

                public String getGoods_sku_sn() {
                    return goods_sku_sn;
                }

                public void setGoods_sku_sn(String goods_sku_sn) {
                    this.goods_sku_sn = goods_sku_sn;
                }

                public String getType_keys() {
                    return type_keys;
                }

                public void setType_keys(String type_keys) {
                    this.type_keys = type_keys;
                }

                public String getAttr_keys() {
                    return attr_keys;
                }

                public void setAttr_keys(String attr_keys) {
                    this.attr_keys = attr_keys;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getDiscount_price() {
                    return discount_price;
                }

                public void setDiscount_price(String discount_price) {
                    this.discount_price = discount_price;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }
            }

            public static class GoodsInfoBean {
                /**
                 * type : 1
                 * content : {"img":"http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797691419657.jpg","width":1000,"height":1660,"length":79}
                 */

                private int type;
                private ContentBean content;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public ContentBean getContent() {
                    return content;
                }

                public void setContent(ContentBean content) {
                    this.content = content;
                }

                public static class ContentBean {
                    /**
                     * img : http://imgs-qn.iliangcang.com//ware/ueditor/image/20170503/1493797691419657.jpg
                     * width : 1000
                     * height : 1660
                     * length : 79
                     */

                    private String img;
                    private int width;
                    private int height;
                    private int length;

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getLength() {
                        return length;
                    }

                    public void setLength(int length) {
                        this.length = length;
                    }
                }
            }
        }
    }
}
