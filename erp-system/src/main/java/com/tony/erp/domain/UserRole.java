package com.tony.erp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jli2
 * @date  2018/11/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    private String uid;

    private String rid;
}