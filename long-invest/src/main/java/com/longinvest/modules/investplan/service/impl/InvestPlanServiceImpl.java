package com.longinvest.modules.investplan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investplan.mapper.InvestPlanMapper;
import com.longinvest.modules.investplan.service.IInvestPlanService;
import com.longinvest.modules.investrecord.entity.InvestRecord;
import com.longinvest.modules.investrecord.mapper.InvestRecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Description: 投资计划
 * @Author: jeecg-boot
 * @Date:   2024-08-05
 * @Version: V1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InvestPlanServiceImpl extends ServiceImpl<InvestPlanMapper, InvestPlan> implements IInvestPlanService {
    private final InvestRecordMapper investRecordMapper;
    @Override
    public void calculateData(InvestPlan investPlan) {
        List<InvestRecord> investRecords = investRecordMapper.selectList(Wrappers.<InvestRecord>lambdaQuery().eq(InvestRecord::getPlan, investPlan.getId()).orderByDesc(InvestRecord::getInvestTime));
        investPlan.setTotalInvestTimes(investRecords.size());
        investPlan.setTotalInvest(investRecords.stream().map(InvestRecord::getFund).reduce(BigDecimal.ZERO, BigDecimal::add));
        investPlan.setAverageTotal(getAverage(investRecords,InvestRecord::getPrice,investRecords.size()));
        investPlan.setAverageCorrelatedTotal(getAverage(investRecords,InvestRecord::getCorrelatePrice,investRecords.size()));
        investPlan.setCurrentAvailable(investRecords.stream().map(record-> record.getBudget().subtract(record.getFund())).reduce(BigDecimal.ZERO,BigDecimal::add));
        if (investRecords.size() >= 10){
            BigDecimal average10 = getAverage(investRecords, InvestRecord::getPrice ,10);
            investPlan.setAverage10(average10);
            BigDecimal averageCorrelated10 = getAverage(investRecords, InvestRecord::getCorrelatePrice, 10);
            investPlan.setAverageCorrelated10(averageCorrelated10);
        }
        if (investRecords.size() >= 20){
            BigDecimal average20 = getAverage(investRecords, InvestRecord::getPrice ,20);
            investPlan.setAverage20(average20);
            BigDecimal averageCorrelated20 = getAverage(investRecords, InvestRecord::getCorrelatePrice, 20);
            investPlan.setAverageCorrelated20(averageCorrelated20);
        }
        if (investRecords.size() >= 30){
            BigDecimal average30 = getAverage(investRecords, InvestRecord::getPrice ,30);
            investPlan.setAverage30(average30);
            BigDecimal averageCorrelated30 = getAverage(investRecords, InvestRecord::getCorrelatePrice, 30);
            investPlan.setAverageCorrelated30(averageCorrelated30);
        }
        if (investRecords.size() >= 60){
            BigDecimal average60 = getAverage(investRecords, InvestRecord::getPrice ,60);
            investPlan.setAverage60(average60);
            BigDecimal averageCorrelated60 = getAverage(investRecords, InvestRecord::getCorrelatePrice, 60);
            investPlan.setAverageCorrelated60(averageCorrelated60);
        }
        if (investRecords.size() >= 120){
            BigDecimal average120 = getAverage(investRecords, InvestRecord::getPrice ,120);
            investPlan.setAverage120(average120);
            BigDecimal averageCorrelated120 = getAverage(investRecords, InvestRecord::getCorrelatePrice, 120);
            investPlan.setAverageCorrelated120(averageCorrelated120);
        }
//        investRecord.setSettleFlag("settled");
//        this.updateById(investPlan);
    }

    private BigDecimal getAverage(List<InvestRecord> investRecords, Function<InvestRecord, BigDecimal> param, int count) {
        if (investRecords.isEmpty() || investRecords.size() < count) {
            return BigDecimal.ZERO;
        }

        // 使用两个变量来分别记录加权总和和基金总和
        BigDecimal weightTotal = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        // 遍历列表的前count个元素
        for (int i = 0; i < count; i++) {
            InvestRecord record = investRecords.get(i);
            if (ObjectUtils.isEmpty(record)){
                continue;
            }
            BigDecimal value = param.apply(record);
            if (ObjectUtils.isEmpty(value)){
                continue;
            }
            BigDecimal fund = record.getFund();
            if (ObjectUtils.isEmpty(fund)){
                continue;
            }
            weightTotal = weightTotal.add(value.multiply(fund));
            total = total.add(fund);
        }

        // 防止除以零的情况
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return weightTotal.divide(total, 4, RoundingMode.HALF_UP);
    }



}
