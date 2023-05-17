package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Sp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-29
 */
public interface SpService extends IService<Sp> {
    IPage pageCC(IPage<Sp> page, Wrapper wrapper);
}
