package com.gpl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gpl.domain.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gpl
 * @since 2023-01-28
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    IPage pageCC(IPage<Major> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
