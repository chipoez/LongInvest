package com.longinvest.modules.investplan.entity;

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
 * @Description: 投资计划
 * @Author: jeecg-boot
 * @Date:   2024-08-04
 * @Version: V1.0
 */
@Data
@TableName("invest_plan")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="投资计划")
public class InvestPlan implements Serializable {
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
	/**名称*/
	@Excel(name = "名称", width = 15)
    @Schema(description = "名称")
    private java.lang.String name;
	/**投资目标*/
	@Excel(name = "投资目标", width = 15, dictTable = "financial_instruments", dicText = "name", dicCode = "id")
	@Dict(dictTable = "financial_instruments", dicText = "name", dicCode = "id")
    @Schema(description = "投资目标")
    private java.lang.String target;
	/**相关标的*/
	@Excel(name = "相关标的", width = 15, dictTable = "financial_instruments", dicText = "name", dicCode = "id")
	@Dict(dictTable = "financial_instruments", dicText = "name", dicCode = "id")
    @Schema(description = "相关标的")
    private java.lang.String corelatedTarget;
	/**投资策略*/
	@Excel(name = "投资策略", width = 15, dicCode = "invest_strategy_dict")
	@Dict(dicCode = "invest_strategy_dict")
    @Schema(description = "投资策略")
    private java.lang.String strategy;
	/**投资频率（天）*/
	@Excel(name = "投资频率（天）", width = 15)
    @Schema(description = "投资频率（天）")
    private java.lang.Integer frequency;
	/**投资时长（天）*/
	@Excel(name = "投资时长（天）", width = 15)
    @Schema(description = "投资时长（天）")
    private java.lang.Integer duration;
	/**总资金*/
	@Excel(name = "总资金", width = 15)
    @Schema(description = "总资金")
    private java.math.BigDecimal totalFunds;
	/**单次资金*/
	@Excel(name = "单次资金", width = 15)
    @Schema(description = "单次资金")
    private java.math.BigDecimal singleFunds;
	/**单次上浮*/
	@Excel(name = "单次上浮", width = 15)
    @Schema(description = "单次上浮")
    private java.math.BigDecimal singleRaise;
	/**单次下沉*/
	@Excel(name = "单次下沉", width = 15)
    @Schema(description = "单次下沉")
    private java.math.BigDecimal singleFall;
	/**当前头寸*/
	@Excel(name = "当前头寸", width = 15)
    @Schema(description = "当前头寸")
    private java.math.BigDecimal singlePosition;
	/**当前次数*/
	@Excel(name = "当前次数", width = 15)
    @Schema(description = "当前次数")
    private java.lang.Integer currentInvestTimes;
	/**总投资*/
	@Excel(name = "总投资", width = 15)
    @Schema(description = "总投资")
    private java.math.BigDecimal totalInvest;
	/**总期数*/
	@Excel(name = "总期数", width = 15)
    @Schema(description = "总期数")
    private java.lang.Integer totalInvestTimes;
	/**持仓价格均线*/
	@Excel(name = "持仓价格均线", width = 15)
    @Schema(description = "持仓价格均线")
    private java.math.BigDecimal averageTotal;
	/**持仓120日均线*/
	@Excel(name = "持仓120日均线", width = 15)
    @Schema(description = "持仓120日均线")
    private java.math.BigDecimal average120;
	/**持仓60日均线*/
	@Excel(name = "持仓60日均线", width = 15)
    @Schema(description = "持仓60日均线")
    private java.math.BigDecimal average60;
	/**30日均线*/
	@Excel(name = "30日均线", width = 15)
    @Schema(description = "30日均线")
    private java.math.BigDecimal average30;
	/**20日均线*/
	@Excel(name = "20日均线", width = 15)
    @Schema(description = "20日均线")
    private java.math.BigDecimal average20;
	/**10日均线*/
	@Excel(name = "10日均线", width = 15)
    @Schema(description = "10日均线")
    private java.math.BigDecimal average10;
	/**相关标的均线*/
	@Excel(name = "相关标的均线", width = 15)
    @Schema(description = "相关标的均线")
    private java.math.BigDecimal averageCorrelatedTotal;
	/**相关标的120日均线*/
	@Excel(name = "相关标的120日均线", width = 15)
    @Schema(description = "相关标的120日均线")
    private java.math.BigDecimal averageCorrelated120;
	/**相关标的60均线*/
	@Excel(name = "相关标的60均线", width = 15)
    @Schema(description = "相关标的60均线")
    private java.math.BigDecimal averageCorrelated60;
	/**相关标的30日均线*/
	@Excel(name = "相关标的30日均线", width = 15)
    @Schema(description = "相关标的30日均线")
    private java.math.BigDecimal averageCorrelated30;
	/**相关标的20日均线*/
	@Excel(name = "相关标的20日均线", width = 15)
    @Schema(description = "相关标的20日均线")
    private java.math.BigDecimal averageCorrelated20;
	/**相关标的10日均线*/
	@Excel(name = "相关标的10日均线", width = 15)
    @Schema(description = "相关标的10日均线")
    private java.math.BigDecimal averageCorrelated10;
}
