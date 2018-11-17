package com.tony.erp.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 * 集合工具类
 */
public class ListUtils {

    public static <T> List<T> difference(List<? extends T> origin, List<? extends T> now){
        List<T> result=new ArrayList<>(origin);
        Iterator iterator=now.iterator();
        while (iterator.hasNext()){
            result.remove(iterator.next());
        }
        return result;
    }

    /**
     * 分页处理工具
     * @param total 获取pageInfo total值
     * @param pageSize 每页显示的数量
     * @return 返回总页数
     */
    public static int getPageNum(long total,int pageSize){
        return (int)total%pageSize>0?(int)total/pageSize+1:(int)total/pageSize;
    }

}
