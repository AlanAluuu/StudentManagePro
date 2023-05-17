package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Classes;
import com.gpl.domain.Dept;
import com.gpl.mapper.ClassesMapper;
import com.gpl.mapper.DeptMapper;
import com.gpl.service.ClassesService;
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
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;
    @Override
    public IPage pageCC(IPage<Classes> page, Wrapper wrapper) {
        return classesMapper.pageCC(page,wrapper);
    }
}
