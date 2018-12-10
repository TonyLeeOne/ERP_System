package com.tony.erp.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author jli2
 * @date  2018/11/12
 * 常量辅助工具类
 */
public class Constant {

    /**
     * 辅助参数
     */
    public static final  String ALL_URLS="all_urls";
    public static final String ALL_MODULES="all_modules";
    public static final String ALL_ROLES="all_roles";

    public static final String USER_COUNTS="user_count";
    public static final String ORDERS_COUNTS="order_count";
    public static final String PRODUCT_COUNTS="productCount";
    public static final String MAN_PLANS="mps_count";
    public static final String MAN_ORDERS="orders_count";
    public static final String CUSTOM_COUNTS="cus_count";

    public static final String ORDER_DATA_COLLECTION="order_data";
    public static final String MATERIAL_DATA_COLLECTION="material_data";
    public static final String PRODUCT_DATA_COLLECTION="product_data";

    /**
     * 数据加密参数
     */
    public static final String ALGORITHM = "MD5";
    public static final int  ITERATORS = 1024;

    /**
     * 处理结果集
     */
    public static final String DATA_ADD_SUCCESS="数据新增成功";
    public static final String DATA_ADD_FAILED="数据新增失败";
    public static final String DATA_UPDATE_SUCCESS="数据更新成功";
    public static final String DATA_UPDATE_FAILED="数据更新失败";
    public static final String DATA_UDELETE_SUCCESS="数据删除成功";
    public static final String DATA_DELETE_FAILED="数据删除失败";
    public static final String ARG_EXCEPTION="参数不允许为空";
    public static final String RESPONSE_SUCCESS="请求成功";
    public static final String CHECK_INFO="当前角色因被用户使用，不可删除";
    public static final String ACOUNT_LOCAKED="你的账号已被锁定，请10分钟后重试！";
    public static final String ACOUNT_LOCAKED_ADMIN="你的账号已被锁定，请联系管理员解锁！";
    public static final String LOGIN_SUCCESS="登陆成功";
    public static final String UNAME_EXISTS="用户名已被占用";
    public static final String DEVICE_CODE_EXISTS="当前设备编号已被占用";
    public static final String PROD_CODE_EXISTS="当前设备编号已被占用";
    public static final String UNAME_NOT_EXISTS="用户名可用";
    public static final String NUMBER_BIG="表单数量不可大于仓库剩余数量";
    public static final String CANNOT_CHANGE="已出货记录不可更改";
    public static final String NEED_AUDIT="需要先审核通过";
    public static final String PRO_SHORTAGE="审核通过，但库存数量不足";
    public static final String PASSWORD_INCORRECT="原始密码错误";
    /**
     * 返回结果处理参数
     */
    public static final int ARG_NOT_MATCHED=-1;
    public static final int STATUS_CANNOT_CHANGED=-2;
    public static final int STATUS_NEED_AUDIT=-3;
    public static final String STRING_ONE="1";
    public static final String STRING_TWO="2";
    public static final String STRING_FIVE="5";
    public static final String STRING_THREE="3";
    public static final String STRING_FOUR="4";

    public static final String SPLITTER=",";

}
