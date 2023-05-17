package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Student;
import com.gpl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")
    public List<Student> list(){
        return studentService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Student student){
        return studentService.save(student)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Student dept){
        return studentService.updateById(dept)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String sno){
        return studentService.removeById(sno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String sname = (String)param.get("sname");

        Page<Student> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(sname) && !"null".equals(sname)){
            lambdaQueryWrapper.like(Student::getSname,sname);
        }

        IPage result = studentService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
