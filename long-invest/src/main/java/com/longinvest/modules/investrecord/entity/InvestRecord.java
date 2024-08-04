package com.longinvest.modules.investrecord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 投资记录
 * @Author: jeecg-boot
 * @Date:   2024-08-04
 * @Version: V1.0
 */
@Data
@TableName("invest_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="投资记录")
public class InvestRecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**创建人*/
    @Schema(description = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private java.lang.String sysOrgCode;
	/**投资计划*/
	@Excel(name = "投资计划", width = 15, dictTable = "invest_plan", dicText = "name", dicCode = "id")
	@Dict(dictTable = "invest_plan", dicText = "name", dicCode = "id")
    @Schema(description = "投资计划")
    private java.lang.String plan;
	/**投资数额*/
	@Excel(name = "投资数额", width = 15)
    @Schema(description = "投资数额")
    private java.math.BigDecimal fund;
	/**净值*/
	@Excel(name = "净值", width = 15)
    @Schema(description = "净值")
    private java.math.BigDecimal price;
	/**相关净值*/
	@Excel(name = "相关净值", width = 15)
    @Schema(description = "相关净值")
    private java.math.BigDecimal correlatePrice;
}
