package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Dept;
import com.gpl.domain.Student;
import com.gpl.mapper.DeptMapper;
import com.gpl.mapper.StudentMapper;
import com.gpl.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public IPage pageCC(IPage<Student> page, Wrapper wrapper) {
        return studentMapper.pageCC(page,wrapper);
    }
}
