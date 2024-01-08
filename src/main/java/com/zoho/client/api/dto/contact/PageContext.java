package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PageContext{
    public Integer page;
    public Integer per_page;
    public Boolean has_more_page;
    public String report_name;
    public String applied_filter;
    public ArrayList<Object> custom_fields;
    public String sort_column;
    public String sort_order;
}
