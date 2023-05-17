package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.domain.User;
import com.gpl.mapper.UserMapper;
import com.gpl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gpl
 * @since 2023-01-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public IPage pageCC(IPage<User> page, Wrapper wrapper) {
        return userMapper.pageCC(page,wrapper);
    }

    @Override
    public IPage pageC(Page<User> page) {
        return userMapper.pageC(page);
    }
}
