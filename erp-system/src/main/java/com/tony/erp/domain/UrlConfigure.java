package com.tony.erp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlConfigure {
    private String id;

    private String url;

    private String authority;

}