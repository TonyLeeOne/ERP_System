package com.tony.erp.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtils {

    public static <T> List<T> difference(List<? extends T> origin, List<? extends T> now){
        List<T> result=new ArrayList<>(origin);
        Iterator iterator=now.iterator();
        while (iterator.hasNext()){
            result.remove(iterator.next());
        }
        return result;
    }
}
