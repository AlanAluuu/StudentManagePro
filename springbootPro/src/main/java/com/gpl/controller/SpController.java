package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Sp;
import com.gpl.service.SpService;
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
 * @since 2023-01-29
 */
@RestController
@RequestMapping("/sp")
public class SpController {
    @Autowired
    private SpService spService;
    @GetMapping("/list")
    public List<Sp> list(){
        return spService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Sp sp){
        return spService.save(sp)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Sp sp){
        return spService.updateById(sp)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String spid){
        return spService.removeById(spid)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String sno = (String)param.get("sno");

        Page<Sp> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Sp> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(sno) && !"null".equals(sno)){
            lambdaQueryWrapper.like(Sp::getSno,sno);
        }

        IPage result = spService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
