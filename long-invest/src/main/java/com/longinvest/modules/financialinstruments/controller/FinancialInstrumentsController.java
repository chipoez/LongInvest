package com.longinvest.modules.financialinstruments.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.longinvest.modules.financialinstruments.entity.FinancialInstruments;
import com.longinvest.modules.financialinstruments.service.IFinancialInstrumentsService;

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
 * @Description: 金融商品
 * @Author: jeecg-boot
 * @Date:   2024-08-02
 * @Version: V1.0
 */
@Tag(name="金融商品")
@RestController
@RequestMapping("/financialinstruments/financialInstruments")
@Slf4j
public class FinancialInstrumentsController extends JeecgController<FinancialInstruments, IFinancialInstrumentsService> {
	@Autowired
	private IFinancialInstrumentsService financialInstrumentsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param financialInstruments
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "金融商品-分页列表查询")
	@Operation(summary="金融商品-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FinancialInstruments>> queryPageList(FinancialInstruments financialInstruments,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FinancialInstruments> queryWrapper = QueryGenerator.initQueryWrapper(financialInstruments, req.getParameterMap());
		Page<FinancialInstruments> page = new Page<FinancialInstruments>(pageNo, pageSize);
		IPage<FinancialInstruments> pageList = financialInstrumentsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param financialInstruments
	 * @return
	 */
	@AutoLog(value = "金融商品-添加")
	@Operation(summary="金融商品-添加")
	@RequiresPermissions("financialinstruments:financial_instruments:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FinancialInstruments financialInstruments) {
		financialInstrumentsService.save(financialInstruments);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param financialInstruments
	 * @return
	 */
	@AutoLog(value = "金融商品-编辑")
	@Operation(summary="金融商品-编辑")
	@RequiresPermissions("financialinstruments:financial_instruments:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FinancialInstruments financialInstruments) {
		financialInstrumentsService.updateById(financialInstruments);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "金融商品-通过id删除")
	@Operation(summary="金融商品-通过id删除")
	@RequiresPermissions("financialinstruments:financial_instruments:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		financialInstrumentsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "金融商品-批量删除")
	@Operation(summary="金融商品-批量删除")
	@RequiresPermissions("financialinstruments:financial_instruments:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.financialInstrumentsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "金融商品-通过id查询")
	@Operation(summary="金融商品-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FinancialInstruments> queryById(@RequestParam(name="id",required=true) String id) {
		FinancialInstruments financialInstruments = financialInstrumentsService.getById(id);
		if(financialInstruments==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(financialInstruments);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param financialInstruments
    */
    @RequiresPermissions("financialinstruments:financial_instruments:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FinancialInstruments financialInstruments) {
        return super.exportXls(request, financialInstruments, FinancialInstruments.class, "金融商品");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("financialinstruments:financial_instruments:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FinancialInstruments.class);
    }

}
