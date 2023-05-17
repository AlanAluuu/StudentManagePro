package com.gpl.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=5;
    private static int PAGE_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    //封装的数据
    private HashMap param = new HashMap();

}
