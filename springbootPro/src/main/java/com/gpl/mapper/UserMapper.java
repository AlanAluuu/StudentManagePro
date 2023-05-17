package com.gpl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gpl
 * @since 2023-01-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage pageC(Page<User> page);

    IPage pageCC(IPage<User> page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
