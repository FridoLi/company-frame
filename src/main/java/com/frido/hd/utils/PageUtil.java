package com.frido.hd.utils;

import com.frido.hd.sys.vo.resp.PageVO;
import com.github.pagehelper.Page;

import java.util.List;

public class PageUtil {
    private PageUtil(){}
    public static <T>PageVO getPageVO(List<T> list){
        PageVO<T> result = new PageVO<>();
        if(list instanceof Page){
            Page<T> page = (Page<T>) list;
            result.setTotalRows(page.getTotal());
            result.setTotalPages(page.getPages());
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.size());
            result.setList(page.getResult());
        }
        return result;
    }
}
