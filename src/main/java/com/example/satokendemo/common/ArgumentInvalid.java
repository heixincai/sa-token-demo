
package com.example.satokendemo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("参数错误结果")
@AllArgsConstructor
@Data
public class ArgumentInvalid {
    @ApiModelProperty("错误的参数字段")
    private String field;

    @ApiModelProperty("错误消息")
    private String message;
}

