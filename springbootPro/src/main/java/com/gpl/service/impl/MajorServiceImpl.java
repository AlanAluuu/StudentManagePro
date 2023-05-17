package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Major;
import com.gpl.mapper.MajorMapper;
import com.gpl.service.MajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gpl
 * @since 2023-01-28
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
    @Resource
    private MajorMapper majorMapper;
    @Override
    public IPage pageCC(IPage<Major> page, Wrapper wrapper) {
        return majorMapper.pageCC(page,wrapper);
    }
}
