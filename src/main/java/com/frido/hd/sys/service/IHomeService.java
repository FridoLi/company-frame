package com.frido.hd.sys.service;

import com.frido.hd.sys.vo.resp.HomeRespVO;

public interface IHomeService {
    HomeRespVO getHomeInfo(String userId);
}
