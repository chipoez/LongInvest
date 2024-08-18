import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '投资计划',
    align:"center",
    dataIndex: 'plan_dictText'
   },
   {
    title: '投资数额',
    align:"center",
    dataIndex: 'fund'
   },
   {
    title: '净值',
    align:"center",
    dataIndex: 'price'
   },
   {
    title: '相关净值',
    align:"center",
    dataIndex: 'correlatePrice'
   },
   {
    title: '当日预算',
    align:"center",
    dataIndex: 'budget'
   },
   {
    title: '投资日期',
    align:"center",
    sorter: true,
    dataIndex: 'investTime',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "投资计划",
      field: 'plan',
      component: 'JSearchSelect',
      componentProps:{
         dict:"invest_plan,name,id"
      },
      //colProps: {span: 6},
 	},
     {
      label: "投资日期",
      field: "investTime",
      component: 'RangePicker',
      componentProps: {
        valueType: 'Date',
      },
      //colProps: {span: 6},
	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '投资计划',
    field: 'plan',
    component: 'JSearchSelect',
    componentProps:{
       dict:"invest_plan,name,id"
    },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资计划!'},
          ];
     },
  },
  {
    label: '投资数额',
    field: 'fund',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资数额!'},
          ];
     },
  },
  {
    label: '净值',
    field: 'price',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入净值!'},
          ];
     },
  },
  {
    label: '相关净值',
    field: 'correlatePrice',
    component: 'InputNumber',
  },
  {
    label: '当日预算',
    field: 'budget',
    component: 'InputNumber',
  },
  {
    label: '投资日期',
    field: 'investTime',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
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
  plan: {title: '投资计划',order: 0,view: 'sel_search', type: 'string',dictTable: "invest_plan", dictCode: 'id', dictText: 'name',},
  fund: {title: '投资数额',order: 1,view: 'number', type: 'number',},
  price: {title: '净值',order: 2,view: 'number', type: 'number',},
  correlatePrice: {title: '相关净值',order: 3,view: 'number', type: 'number',},
  budget: {title: '当日预算',order: 4,view: 'number', type: 'number',},
  investTime: {title: '投资日期',order: 5,view: 'date', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}