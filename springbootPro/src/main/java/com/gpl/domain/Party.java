package com.gpl.domain;

import java.time.LocalDate;
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
@ApiModel(value="Party对象", description="")
public class Party implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Pno")
    private String pno;

    @TableField("Pname")
    private String pname;

    @TableField("Pdate")
    private LocalDate pdate;

    @TableField("Plc")
    private String plc;


}
