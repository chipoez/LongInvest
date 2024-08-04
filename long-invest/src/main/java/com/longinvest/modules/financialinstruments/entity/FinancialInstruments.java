package com.longinvest.modules.financialinstruments.entity;

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
 * @Description: 金融商品
 * @Author: jeecg-boot
 * @Date:   2024-08-03
 * @Version: V1.0
 */
@Data
@TableName("financial_instruments")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="金融商品")
public class FinancialInstruments implements Serializable {
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
	/**种类*/
	@Excel(name = "种类", width = 15, dicCode = "instrument_dict")
	@Dict(dicCode = "instrument_dict")
    @Schema(description = "种类")
    private java.lang.String type;
	/**代码*/
	@Excel(name = "代码", width = 15)
    @Schema(description = "代码")
    private java.lang.String tickerSymbol;
	/**货币*/
	@Excel(name = "货币", width = 15, dicCode = "currency_dict")
	@Dict(dicCode = "currency_dict")
    @Schema(description = "货币")
    private java.lang.String currency;
	/**限购金额*/
	@Excel(name = "限购金额", width = 15)
    @Schema(description = "限购金额")
    private java.math.BigDecimal purchaseLimit;
}
