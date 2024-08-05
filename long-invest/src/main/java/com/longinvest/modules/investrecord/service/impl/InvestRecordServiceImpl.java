package com.longinvest.modules.investrecord.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investplan.mapper.InvestPlanMapper;
import com.longinvest.modules.investplan.service.IInvestPlanService;
import com.longinvest.modules.investrecord.entity.InvestRecord;
import com.longinvest.modules.investrecord.mapper.InvestRecordMapper;
import com.longinvest.modules.investrecord.service.IInvestRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 投资记录
 * @Author: jeecg-boot
 * @Date:   2024-08-05
 * @Version: V1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InvestRecordServiceImpl extends ServiceImpl<InvestRecordMapper, InvestRecord> implements IInvestRecordService {
    private final InvestPlanMapper investPlanMapper;
    private final IInvestPlanService investPlanService;

    @Override
    @Transactional
    public void updateInvestPlan(InvestRecord investRecord) {
        if ("settled".equals(investRecord.getSettleFlag())){
            return;
        }
        InvestPlan investPlan = investPlanMapper.selectById(investRecord.getPlan());
        investPlanService.calculateData(investPlan,investRecord);

    }
}
