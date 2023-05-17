package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Party;
import com.gpl.mapper.PartyMapper;
import com.gpl.service.PartyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@Service
public class PartyServiceImpl extends ServiceImpl<PartyMapper, Party> implements PartyService {
    @Resource
    private PartyMapper partyMapper;
    @Override
    public IPage pageCC(IPage<Party> page, Wrapper wrapper) {
        return partyMapper.pageCC(page,wrapper);
    }
}
