package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Classes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpl.domain.Dept;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
public interface ClassesService extends IService<Classes> {
    IPage pageCC(IPage<Classes> page, Wrapper wrapper);
}
