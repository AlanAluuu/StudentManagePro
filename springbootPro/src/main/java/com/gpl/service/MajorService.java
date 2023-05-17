package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gpl.domain.Dept;
import com.gpl.domain.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-28
 */
public interface MajorService extends IService<Major> {
    IPage pageCC(IPage<Major> page, Wrapper wrapper);
}
