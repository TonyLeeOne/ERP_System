package com.tony.erp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
@EqualsAndHashCode
public class ManOrder {
    private String moId;

    private String moSn;

    private String moMpSn;

    private String moStartDate;

    private String moEndDate;

    private Integer moCount;

    private Integer moWaitCount;

    private String moStatus;

}