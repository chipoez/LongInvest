package com.longinvest.modules.financialdata.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longinvest.modules.financialdata.entity.FinancialData;
import com.longinvest.modules.financialdata.service.IFinancialDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 金融商品数据表
 * @Author: jeecg-boot
 * @Date:   2024-08-02
 * @Version: V1.0
 */
@Tag(name="金融商品数据表")
@RestController
@RequestMapping("/financialdata/financialData")
@Slf4j
public class FinancialDataController extends JeecgController<FinancialData, IFinancialDataService> {
	@Autowired
	private IFinancialDataService financialDataService;

	/**
	 * 分页列表查询
	 *
	 * @param financialData
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "金融商品数据表-分页列表查询")
	@Operation(summary="金融商品数据表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FinancialData>> queryPageList(FinancialData financialData,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FinancialData> queryWrapper = QueryGenerator.initQueryWrapper(financialData, req.getParameterMap());
		Page<FinancialData> page = new Page<FinancialData>(pageNo, pageSize);
		IPage<FinancialData> pageList = financialDataService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param financialData
	 * @return
	 */
	@AutoLog(value = "金融商品数据表-添加")
	@Operation(summary="金融商品数据表-添加")
	@RequiresPermissions("financialdata:financial_data:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FinancialData financialData) {
		financialDataService.save(financialData);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param financialData
	 * @return
	 */
	@AutoLog(value = "金融商品数据表-编辑")
	@Operation(summary="金融商品数据表-编辑")
	@RequiresPermissions("financialdata:financial_data:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FinancialData financialData) {
		financialDataService.updateById(financialData);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "金融商品数据表-通过id删除")
	@Operation(summary="金融商品数据表-通过id删除")
	@RequiresPermissions("financialdata:financial_data:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		financialDataService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "金融商品数据表-批量删除")
	@Operation(summary="金融商品数据表-批量删除")
	@RequiresPermissions("financialdata:financial_data:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.financialDataService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "金融商品数据表-通过id查询")
	@Operation(summary="金融商品数据表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FinancialData> queryById(@RequestParam(name="id",required=true) String id) {
		FinancialData financialData = financialDataService.getById(id);
		if(financialData==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(financialData);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param financialData
    */
    @RequiresPermissions("financialdata:financial_data:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FinancialData financialData) {
        return super.exportXls(request, financialData, FinancialData.class, "金融商品数据表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("financialdata:financial_data:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FinancialData.class);
    }

	 @RequiresPermissions("financialdata:financial_data:importExcel")
	 @RequestMapping(value = "/importExcelInvest", method = RequestMethod.POST)
	 public Result<?> importExcelInvest(HttpServletRequest request,MultipartFile file, HttpServletResponse response) {
		 List<Map<String, String>> records = new ArrayList<>();
		 try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
			  CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
			 for (CSVRecord csvRecord : csvParser) {
				 records.add(csvRecord.toMap());
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 return super.importExcel(request, response, FinancialData.class);
	 }
}
