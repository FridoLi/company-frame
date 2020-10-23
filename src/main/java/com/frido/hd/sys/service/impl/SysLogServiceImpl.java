package com.frido.hd.sys.service.impl;

import com.frido.hd.sys.entity.SysLog;
import com.frido.hd.sys.mapper.SysLogMapper;
import com.frido.hd.sys.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author ldhao
 * @since 2020-10-23
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
