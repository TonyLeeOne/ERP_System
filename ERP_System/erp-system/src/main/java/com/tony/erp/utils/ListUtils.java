package com.tony.erp.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;

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


    public static Map<String,Object> splitToArray(List<String> original){
        if(!CollectionUtils.isEmpty(original)){
            String[] date=new String[original.size()];
            String[] data=new String[original.size()];
            Map<String,Object> result=new HashMap<>(2);
            for (int i = 0; i <original.size() ; i++) {
                String[] array=original.get(i).split(",");
                data[i]=array[0];
                date[i]=array[1];
            }
            result.put("date",date);
            result.put("data",data);
            return result;
        }
        return null;
    }

}
