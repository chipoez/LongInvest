import {BasicColumn, FormSchema} from '/@/components/Table';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '金融商品',
    align:"center",
    dataIndex: 'instrumentId_dictText'
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
   {
    title: '交易量',
    align:"center",
    dataIndex: 'tradeVolume'
   },
   {
    title: '涨跌率',
    align:"center",
    dataIndex: 'changeRate'
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
  },
  {
    label: '数据日期',
    field: 'date',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '开盘价',
    field: 'openPrice',
    component: 'InputNumber',
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
  },
  {
    label: '最高价',
    field: 'highPrice',
    component: 'InputNumber',
  },
  {
    label: '最低价',
    field: 'lowPrice',
    component: 'InputNumber',
  },
  {
    label: '交易量',
    field: 'tradeVolume',
    component: 'InputNumber',
  },
  {
    label: '涨跌率',
    field: 'changeRate',
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
  instrumentId: {title: '金融商品',order: 0,view: 'sel_search', type: 'string',dictTable: "financial_instruments", dictCode: 'id', dictText: 'name',},
  date: {title: '数据日期',order: 1,view: 'date', type: 'string',},
  openPrice: {title: '开盘价',order: 2,view: 'number', type: 'number',},
  closePrice: {title: '收盘价',order: 3,view: 'number', type: 'number',},
  highPrice: {title: '最高价',order: 4,view: 'number', type: 'number',},
  lowPrice: {title: '最低价',order: 5,view: 'number', type: 'number',},
  tradeVolume: {title: '交易量',order: 6,view: 'number', type: 'number',},
  changeRate: {title: '涨跌率',order: 7,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
