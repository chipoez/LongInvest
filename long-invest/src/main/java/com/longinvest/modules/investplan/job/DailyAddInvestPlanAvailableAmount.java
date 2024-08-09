package com.longinvest.modules.investplan.job;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investplan.mapper.InvestPlanMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DailyAddInvestPlanAvailableAmount implements Job {
    @Resource
    private InvestPlanMapper investPlanMapper;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        investPlanMapper.update(null, Wrappers.<InvestPlan>lambdaUpdate().setSql("current_available = current_available + single_funds"));
    }
}
