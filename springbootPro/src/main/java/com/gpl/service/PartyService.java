package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Dept;
import com.gpl.domain.Party;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpl.domain.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
public interface PartyService extends IService<Party> {
    IPage pageCC(IPage<Party> page, Wrapper wrapper);
}
