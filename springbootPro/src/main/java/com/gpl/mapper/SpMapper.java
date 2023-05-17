package com.gpl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gpl.domain.Sp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gpl
 * @since 2023-01-29
 */
@Mapper
public interface SpMapper extends BaseMapper<Sp> {
    IPage pageCC(IPage<Sp> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
