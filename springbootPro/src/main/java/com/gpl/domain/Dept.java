package com.gpl.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Dept对象", description="")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Deptno")
    private String deptno;

    @TableField("Deptname")
    private String deptname;

    @TableField("Deptnum")
    private Integer deptnum;

    @TableField("Deptlc")
    private String deptlc;

    @TableField("DeptA")
    private String depta;


}
