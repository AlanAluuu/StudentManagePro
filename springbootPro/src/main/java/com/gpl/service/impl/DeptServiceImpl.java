package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Dept;
import com.gpl.mapper.DeptMapper;
import com.gpl.service.DeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Resource
    private DeptMapper deptMapper;
    @Override
    public IPage pageCC(IPage<Dept> page, Wrapper wrapper) {
        return deptMapper.pageCC(page,wrapper);
    }
}
