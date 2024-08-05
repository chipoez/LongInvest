package com.longinvest.modules.investplan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investplan.service.IInvestPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 投资计划
 * @Author: jeecg-boot
 * @Date:   2024-08-05
 * @Version: V1.0
 */
@Tag(name="投资计划")
@RestController
@RequestMapping("/investplan/investPlan")
@Slf4j
public class InvestPlanController extends JeecgController<InvestPlan, IInvestPlanService> {
	@Autowired
	private IInvestPlanService investPlanService;

	/**
	 * 分页列表查询
	 *
	 * @param investPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "投资计划-分页列表查询")
	@Operation(summary="投资计划-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<InvestPlan>> queryPageList(InvestPlan investPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InvestPlan> queryWrapper = QueryGenerator.initQueryWrapper(investPlan, req.getParameterMap());
		Page<InvestPlan> page = new Page<InvestPlan>(pageNo, pageSize);
		IPage<InvestPlan> pageList = investPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param investPlan
	 * @return
	 */
	@AutoLog(value = "投资计划-添加")
	@Operation(summary="投资计划-添加")
	@RequiresPermissions("investplan:invest_plan:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody InvestPlan investPlan) {
		investPlanService.save(investPlan);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param investPlan
	 * @return
	 */
	@AutoLog(value = "投资计划-编辑")
	@Operation(summary="投资计划-编辑")
	@RequiresPermissions("investplan:invest_plan:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody InvestPlan investPlan) {
		investPlanService.updateById(investPlan);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "投资计划-通过id删除")
	@Operation(summary="投资计划-通过id删除")
	@RequiresPermissions("investplan:invest_plan:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		investPlanService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "投资计划-批量删除")
	@Operation(summary="投资计划-批量删除")
	@RequiresPermissions("investplan:invest_plan:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.investPlanService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "投资计划-通过id查询")
	@Operation(summary="投资计划-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<InvestPlan> queryById(@RequestParam(name="id",required=true) String id) {
		InvestPlan investPlan = investPlanService.getById(id);
		if(investPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(investPlan);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param investPlan
    */
    @RequiresPermissions("investplan:invest_plan:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InvestPlan investPlan) {
        return super.exportXls(request, investPlan, InvestPlan.class, "投资计划");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("investplan:invest_plan:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, InvestPlan.class);
    }

}
