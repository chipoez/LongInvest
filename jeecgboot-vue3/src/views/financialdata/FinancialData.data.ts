import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '金融商品',
    align:"center",
    dataIndex: 'instrumentId_dictText'
   },
   {
    title: '金融商品类型',
    align:"center",
    dataIndex: 'instrumentType_dictText'
   },
   {
    title: '数据日期',
    align:"center",
    dataIndex: 'date',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '开盘价',
    align:"center",
    dataIndex: 'openPrice'
   },
   {
    title: '收盘价',
    align:"center",
    dataIndex: 'closePrice'
   },
   {
    title: '最高价',
    align:"center",
    dataIndex: 'highPrice'
   },
   {
    title: '最低价',
    align:"center",
    dataIndex: 'lowPrice'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "金融商品",
      field: 'instrumentId',
      component: 'JSearchSelect',
      componentProps:{
         dict:"financial_instruments,name,id"
      },
      //colProps: {span: 6},
 	},
	{
      label: "金融商品类型",
      field: 'instrumentType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"financial_instrument_type_dict"
      },
      //colProps: {span: 6},
 	},
	{
      label: "数据日期",
      field: 'date',
      component: 'DatePicker',
      componentProps: {
        valueFormat: 'YYYY-MM-DD'
      },
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '金融商品',
    field: 'instrumentId',
    component: 'JSearchSelect',
    componentProps:{
       dict:"financial_instruments,name,id"
    },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入金融商品!'},
          ];
     },
    dynamicDisabled:true
  },
  {
    label: '金融商品类型',
    field: 'instrumentType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"financial_instrument_type_dict"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入金融商品类型!'},
          ];
     },
    dynamicDisabled:true
  },
  {
    label: '数据日期',
    field: 'date',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
    dynamicDisabled:true
  },
  {
    label: '开盘价',
    field: 'openPrice',
    component: 'InputNumber',
    dynamicDisabled:true
  },
  {
    label: '收盘价',
    field: 'closePrice',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入收盘价!'},
          ];
     },
    dynamicDisabled:true
  },
  {
    label: '最高价',
    field: 'highPrice',
    component: 'InputNumber',
    dynamicDisabled:true
  },
  {
    label: '最低价',
    field: 'lowPrice',
    component: 'InputNumber',
    dynamicDisabled:true
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
  instrumentId: {title: '金融商品',order: 0,view: 'sel_search', type: 'string',dictTable: "financial_instruments", dictCode: 'id', dictText: 'name',},
  instrumentType: {title: '金融商品类型',order: 1,view: 'number', type: 'number',dictCode: 'financial_instrument_type_dict',},
  date: {title: '数据日期',order: 2,view: 'date', type: 'string',},
  openPrice: {title: '开盘价',order: 3,view: 'number', type: 'number',},
  closePrice: {title: '收盘价',order: 4,view: 'number', type: 'number',},
  highPrice: {title: '最高价',order: 5,view: 'number', type: 'number',},
  lowPrice: {title: '最低价',order: 6,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}