package com.tony.erp.domain.pagehelper;

import lombok.Data;

import java.util.List;

/**
 * @author jli2
 * @date 11/12/2018 4:56 PM
 **/
@Data
public class PageHelperEntity {
    private long total;
    private List<?> rows;
    private int pageNum;
    private int currentPage;
}
