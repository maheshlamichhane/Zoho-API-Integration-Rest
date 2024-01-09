package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PageContext{
    private Integer page;
    private Integer per_page;
    private Boolean has_more_page;
    private String report_name;
    private String applied_filter;
    private ArrayList<Object> custom_fields;
    private String sort_column;
    private String sort_order;
}
