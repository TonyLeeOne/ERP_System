package com.tony.erp.domain;

import lombok.Data;

@Data
public class MMNeededWithBLOBs extends MMNeeded {
    private String mmnNeeded;

    private String mmnGet;

    private String mmnConsume;

}