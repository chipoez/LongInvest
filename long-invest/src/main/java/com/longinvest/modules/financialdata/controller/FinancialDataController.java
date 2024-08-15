package com.longinvest.modules.financialdata.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: 金融商品数据表
 * @Author: jeecg-boot
 * @Date:   2024-08-13
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
	 public Result<?> importExcelInvest(HttpServletRequest request,MultipartFile file, String instrumentId,String model) {
		 List<Map<String, String>> records = new ArrayList<>();
		 try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
			  CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
			 Class<?> investDataDtoClass = Class.forName(model);
			 Field[] fields = investDataDtoClass.getDeclaredFields();
			 List<String> titles = Arrays.stream(fields).map(field -> {
				 Excel annotation = field.getAnnotation(Excel.class);
				 if (annotation == null) {
					 return null;
				 }
				 return annotation.name();
			 }).filter(Objects::nonNull).toList();

			 Map<String,Field> titleFieldMap = Arrays.stream(fields).filter(this::filterExel)
					 .collect(Collectors.toMap(field -> field.getAnnotation(Excel.class).name(), field -> field));
			 boolean toTitle = true;
			 List<String> titleList = new ArrayList<>();

			 for (CSVRecord csvRecord : csvParser) {
				 if (toTitle){
					 csvRecord.forEach(value->{
						 value = value.replaceAll("\"", "").replaceAll("\uFEFF","");
						 if (titles.contains(value)){
							 titleList.add(value);
						 }
					 });
					 toTitle = false;
					 continue;
				 }
				 Map<String, String> collect = IntStream.range(0, csvRecord.size()).boxed().collect(Collectors.toMap(titleList::get, csvRecord::get));
				 records.add(collect);
			 }
			 records.forEach(record -> {
				 FinancialData financialData = new FinancialData();
				 financialData.setInstrumentId(instrumentId);
				 titleFieldMap.forEach((title, field) -> {
					 try {
						 field.setAccessible(true);
						 field.set(financialData, record.get(title));
					 } catch (IllegalAccessException e) {
						 log.error("设置属性失败", e);
					 }
				 });
				 if (ObjectUtils.allNull(financialData.getDate(),financialData.getInstrumentId())){
					 return;
				 }
				 LambdaQueryChainWrapper<FinancialData> wrappers = financialDataService.lambdaQuery().eq(FinancialData::getDate, financialData.getDate()).eq(FinancialData::getInstrumentId, instrumentId);
				 if (wrappers.count() == 0){
					 financialDataService.save(financialData);
					 return;
				 }
//				 FinancialData one = wrappers.one();
				 financialDataService.update(financialData,wrappers);
			 });

		 } catch (Exception e) {
			 log.error("导入失败", e);
		 }
		 return Result.OK("导入成功！");

	 }

	private boolean filterExel(Field field) {
		Excel annotation = field.getAnnotation(Excel.class);
		if (annotation == null){
			return false;
		}
		String name = annotation.name();
        return !StringUtils.isBlank(name);
    }
}
