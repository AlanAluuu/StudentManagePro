package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Sp;
import com.gpl.mapper.SpMapper;
import com.gpl.service.SpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gpl
 * @since 2023-01-29
 */
@Service
public class SpServiceImpl extends ServiceImpl<SpMapper, Sp> implements SpService {
    @Resource
    private SpMapper spMapper;
    @Override
    public IPage pageCC(IPage<Sp> page, Wrapper wrapper) {
        return spMapper.pageCC(page,wrapper);
    }
}
