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
 * @since 2023-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Sp对象", description="")
public class Sp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("spid")
    private Integer spid;

    @TableField("Pno")
    private String pno;

    @TableField("Sno")
    private String sno;

    @TableField("Edate")
    private LocalDate edate;


}
