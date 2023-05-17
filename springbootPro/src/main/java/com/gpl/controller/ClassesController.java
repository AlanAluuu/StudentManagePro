package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Classes;
import com.gpl.service.ClassesService;
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
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @GetMapping("/list")
    public List<Classes> list(){
        return classesService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Classes classes){
        return classesService.save(classes)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Classes classes){
        return classesService.updateById(classes)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String cno){
        return classesService.removeById(cno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String cno = (String)param.get("cno");

        Page<Classes> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Classes> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(cno) && !"null".equals(cno)){
            lambdaQueryWrapper.like(Classes::getCno,cno);
        }

        IPage result = classesService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
