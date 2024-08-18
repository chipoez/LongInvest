package com.longinvest.modules.investrecord.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.longinvest.modules.investplan.entity.InvestPlan;
import com.longinvest.modules.investplan.service.IInvestPlanService;
import com.longinvest.modules.service.InvestPlanRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.longinvest.modules.investrecord.entity.InvestRecord;
import com.longinvest.modules.investrecord.service.IInvestRecordService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 投资记录
 * @Author: jeecg-boot
 * @Date:   2024-08-18
 * @Version: V1.0
 */
@Tag(name="投资记录")
@RestController
@RequestMapping("/investrecord/investRecord")
@Slf4j
public class InvestRecordController extends JeecgController<InvestRecord, IInvestRecordService> {
	@Autowired
	private IInvestRecordService investRecordService;
	@Autowired
	private InvestPlanRecordService investPlanRecordService;
	@Autowired
	private IInvestPlanService investPlanService;


	/**
	 * 分页列表查询
	 *
	 * @param investRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "投资记录-分页列表查询")
	@Operation(summary="投资记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<InvestRecord>> queryPageList(InvestRecord investRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InvestRecord> queryWrapper = QueryGenerator.initQueryWrapper(investRecord, req.getParameterMap());
		Page<InvestRecord> page = new Page<>(pageNo, pageSize);
		IPage<InvestRecord> pageList = investRecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param investRecord
	 * @return
	 */
	@AutoLog(value = "投资记录-添加")
	@Operation(summary="投资记录-添加")
	@RequiresPermissions("investrecord:invest_record:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody InvestRecord investRecord) {
		investRecordService.save(investRecord);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param investRecord
	 * @return
	 */
	@AutoLog(value = "投资记录-编辑")
	@Operation(summary="投资记录-编辑")
	@RequiresPermissions("investrecord:invest_record:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody InvestRecord investRecord) {
		if (ObjectUtils.isEmpty(investRecord.getBudget())){
			InvestPlan plan = investPlanService.getById(investRecord.getPlan());
			investRecord.setBudget(plan.getSingleFunds());
		}
		investRecordService.updateById(investRecord);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "投资记录-通过id删除")
	@Operation(summary="投资记录-通过id删除")
	@RequiresPermissions("investrecord:invest_record:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		investRecordService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "投资记录-批量删除")
	@Operation(summary="投资记录-批量删除")
	@RequiresPermissions("investrecord:invest_record:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.investRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "投资记录-通过id查询")
	@Operation(summary="投资记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<InvestRecord> queryById(@RequestParam(name="id",required=true) String id) {
		InvestRecord investRecord = investRecordService.getById(id);
		if(investRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(investRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param investRecord
    */
    @RequiresPermissions("investrecord:invest_record:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InvestRecord investRecord) {
        return super.exportXls(request, investRecord, InvestRecord.class, "投资记录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("investrecord:invest_record:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, InvestRecord.class);
    }

}
