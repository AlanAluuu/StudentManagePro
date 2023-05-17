package com.gpl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpl.common.QueryPageParam;
import com.gpl.common.Result;
import com.gpl.domain.Party;
import com.gpl.service.PartyService;
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
@RequestMapping("/party")
public class PartyController {
    @Autowired
    private PartyService partyService;
    @GetMapping("/list")
    public List<Party> list(){
        return partyService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Party party){
        return partyService.save(party)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Party party){
        return partyService.updateById(party)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String pno){
        return partyService.removeById(pno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String pname = (String)param.get("pname");

        Page<Party> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Party> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(pname) && !"null".equals(pname)){
            lambdaQueryWrapper.like(Party::getPname,pname);
        }

        IPage result = partyService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }
}
