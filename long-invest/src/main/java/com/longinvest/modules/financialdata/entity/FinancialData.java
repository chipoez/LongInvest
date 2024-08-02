package com.longinvest.modules.financialdata.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 金融商品数据表
 * @Author: jeecg-boot
 * @Date:   2024-08-02
 * @Version: V1.0
 */
@Data
@TableName("financial_data")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="金融商品数据表")
public class FinancialData implements Serializable {
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
	/**金融商品*/
	@Excel(name = "金融商品", width = 15, dictTable = "financial_instruments", dicText = "name", dicCode = "id")
	@Dict(dictTable = "financial_instruments", dicText = "name", dicCode = "id")
    @Schema(description = "金融商品")
    private java.lang.String instrumentId;
	/**金融商品类型*/
	@Excel(name = "金融商品类型", width = 15, dicCode = "financial_instrument_type_dict")
	@Dict(dicCode = "financial_instrument_type_dict")
    @Schema(description = "金融商品类型")
    private java.lang.Integer instrumentType;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "数据日期")
    private java.util.Date date;
	/**开盘价*/
	@Excel(name = "开盘价", width = 15)
    @Schema(description = "开盘价")
    private java.math.BigDecimal openPrice;
	/**收盘价*/
	@Excel(name = "收盘价", width = 15)
    @Schema(description = "收盘价")
    private java.math.BigDecimal closePrice;
	/**最高价*/
	@Excel(name = "最高价", width = 15)
    @Schema(description = "最高价")
    private java.math.BigDecimal highPrice;
	/**最低价*/
	@Excel(name = "最低价", width = 15)
    @Schema(description = "最低价")
    private java.math.BigDecimal lowPrice;
}
