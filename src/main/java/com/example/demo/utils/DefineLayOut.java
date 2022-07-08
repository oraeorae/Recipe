package com.example.demo.utils;

import org.apache.log4j.HTMLLayout;
public class DefineLayOut extends HTMLLayout{
    @Override
    public String getContentType() {
        return "text/html;charset=GBK";
    }
}