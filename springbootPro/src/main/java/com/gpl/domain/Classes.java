package com.gpl.domain;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="Classes对象", description="")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Cno")
    private String cno;

    @TableField("Cnum")
    private Integer cnum;

    @TableField("Cdate")
    private LocalDate cdate;

    @TableField("CMN")
    private String cmn;


}
