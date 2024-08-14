package com.longinvest.modules.financialdata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description="金融商品导入实体类")
public class InvestDataDto {

    /**金融商品*/
    @Excel(name = "金融商品", width = 15, dictTable = "financial_instruments", dicText = "name", dicCode = "id")
    @Schema(description = "金融商品")
    private java.lang.String instrumentId;
    /**数据日期*/
    @Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "日期")
    private java.util.Date date;
    /**开盘价*/
    @Excel(name = "开盘", width = 15)
    @Schema(description = "开盘")
    private java.math.BigDecimal openPrice;
    /**收盘价*/
    @Excel(name = "收盘", width = 15)
    @Schema(description = "收盘")
    private java.math.BigDecimal closePrice;
    /**最高价*/
    @Excel(name = "高", width = 15)
    @Schema(description = "高")
    private java.math.BigDecimal highPrice;
    /**最低价*/
    @Excel(name = "低", width = 15)
    @Schema(description = "低")
    private java.math.BigDecimal lowPrice;
    /**交易量*/
    @Excel(name = "交易量", width = 15)
    @Schema(description = "交易量")
    private String tradeVolume;
    /**涨跌率*/
    @Excel(name = "涨跌幅", width = 15)
    @Schema(description = "涨跌幅")
    private String changeRate;
}
