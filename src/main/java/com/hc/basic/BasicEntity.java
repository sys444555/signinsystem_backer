package com.hc.basic;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("基础实体类")
public class BasicEntity implements Serializable {


    @ApiModelProperty(value = "创建时间",name = "createTime")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人",name = "createBy")
    @TableField(value = "create_by")
    private  String createBy;

    @ApiModelProperty(value = "修改时间",name = "updateTime")
    @TableField(value = "update_time")
    private  Date updateTime;

    @ApiModelProperty(value = "修改人",name = "updateBy")
    @TableField(value = "update_by")
    private  String updateBy;




}
