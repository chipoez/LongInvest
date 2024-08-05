package com.longinvest.modules.investplan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investrecord.entity.InvestRecord;

/**
 * @Description: 投资计划
 * @Author: jeecg-boot
 * @Date:   2024-08-05
 * @Version: V1.0
 */
public interface IInvestPlanService extends IService<InvestPlan> {

    void calculateData(InvestPlan investPlan, InvestRecord investRecord);
}
