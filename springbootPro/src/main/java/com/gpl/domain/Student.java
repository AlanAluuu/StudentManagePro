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
@ApiModel(value="Student对象", description="")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Sno")
    private String sno;

    @TableField("Sname")
    private String sname;

    @TableField("Sage")
    private Integer sage;

    @TableField("SC")
    private String sc;


}
