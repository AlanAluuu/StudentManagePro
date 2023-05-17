package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Dept;
import com.gpl.service.DeptService;
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
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping("/list")
    public List<Dept> list(){
        return deptService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Dept dept){
        return deptService.save(dept)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String deptno){
        return deptService.removeById(deptno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String Deptname = (String)param.get("deptname");

        Page<Dept> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(Deptname) && !"null".equals(Deptname)){
            lambdaQueryWrapper.like(Dept::getDeptname,Deptname);
        }

        IPage result = deptService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
