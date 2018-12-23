package com.tony.erp.domain.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tony
 * @date 2018/11/18 4:35 PM
 */
@Data
public class DataEntity {
    private int userCount;
    private int productCount;
    private int manPlanCount;
    private int customCount;
    private int manOrderCount;
    private int orderCount;
    public static Map<String,Object> map=new HashMap<>(8);
}
