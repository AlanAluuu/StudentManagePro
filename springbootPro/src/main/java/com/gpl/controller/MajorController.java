package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Major;
import com.gpl.service.MajorService;
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
 * @since 2023-01-28
 */
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;
    @GetMapping("/list")
    public List<Major> list(){
        return majorService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Major major){
        return majorService.save(major)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Major major){
        return majorService.updateById(major)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String mno){
        return majorService.removeById(mno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String mname = (String)param.get("mname");

        Page<Major> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Major> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(mname) && !"null".equals(mname)){
            lambdaQueryWrapper.like(Major::getMname,mname);
        }

        IPage result = majorService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
