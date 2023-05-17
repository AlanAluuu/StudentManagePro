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
 * @since 2023-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Major对象", description="")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Mno")
    private String mno;

    @TableField("Mname")
    private String mname;

    @TableField("Mdept")
    private String mdept;




}
