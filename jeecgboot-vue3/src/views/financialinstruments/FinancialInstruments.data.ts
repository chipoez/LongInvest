import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '种类',
    align:"center",
    dataIndex: 'type_dictText'
   },
   {
    title: '代码',
    align:"center",
    dataIndex: 'tickerSymbol'
   },
   {
    title: '货币单位',
    align:"center",
    dataIndex: 'currency_dictText'
   },
   {
    title: '限购',
    align:"center",
    dataIndex: 'purchaseLimitAmount'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
     {
      label: "名称",
      field: "name",
      component: 'Input', //TODO 范围查询
      //colProps: {span: 6},
	},
	{
      label: "种类",
      field: 'type',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"financial_instrument_type_dict"
      },
      //colProps: {span: 6},
 	},
	{
      label: "代码",
      field: 'tickerSymbol',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入名称!'},
          ];
     },
  },
  {
    label: '种类',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"financial_instrument_type_dict"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入种类!'},
          ];
     },
  },
  {
    label: '代码',
    field: 'tickerSymbol',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入代码!'},
          ];
     },
  },
  {
    label: '货币单位',
    field: 'currency',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"currency_dict"
     },
  },
  {
    label: '限购',
    field: 'purchaseLimitAmount',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '名称',order: 0,view: 'text', type: 'string',},
  type: {title: '种类',order: 1,view: 'number', type: 'number',dictCode: 'financial_instrument_type_dict',},
  tickerSymbol: {title: '代码',order: 2,view: 'text', type: 'string',},
  currency: {title: '货币单位',order: 3,view: 'list', type: 'string',dictCode: 'currency_dict',},
  purchaseLimitAmount: {title: '限购',order: 4,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}