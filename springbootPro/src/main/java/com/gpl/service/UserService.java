package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-24
 */
public interface UserService extends IService<User> {

    IPage pageCC(IPage<User> page, Wrapper wrapper);

    IPage pageC(Page<User> page);
}
