import {BasicColumn, FormSchema} from '/@/components/Table';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '投资目标',
    align:"center",
    dataIndex: 'target_dictText'
   },
   {
    title: '相关标的',
    align:"center",
    dataIndex: 'corelatedTarget_dictText'
   },
   {
    title: '投资策略',
    align:"center",
    dataIndex: 'strategy_dictText'
   },
   {
    title: '投资频率（天）',
    align:"center",
    dataIndex: 'frequency'
   },
   {
    title: '投资时长（天）',
    align:"center",
    dataIndex: 'duration'
   },
   {
    title: '总资金',
    align:"center",
    dataIndex: 'totalFunds'
   },
   {
    title: '单次资金',
    align:"center",
    dataIndex: 'singleFunds'
   },
   {
    title: '单次上浮',
    align:"center",
    dataIndex: 'singleRaise'
   },
   {
    title: '单次下沉',
    align:"center",
    dataIndex: 'singleFall'
   },
   {
    title: '当前头寸',
    align:"center",
    dataIndex: 'currentAvailable'
   },
   {
    title: '总投资',
    align:"center",
    dataIndex: 'totalInvest'
   },
   {
    title: '总期数',
    align:"center",
    dataIndex: 'totalInvestTimes'
   },
   {
    title: '持仓价格均线',
    align:"center",
    dataIndex: 'averageTotal'
   },
   {
    title: '持仓120日均线',
    align:"center",
    dataIndex: 'average120'
   },
   {
    title: '持仓60日均线',
    align:"center",
    dataIndex: 'average60'
   },
   {
    title: '30日均线',
    align:"center",
    dataIndex: 'average30'
   },
   {
    title: '20日均线',
    align:"center",
    dataIndex: 'average20'
   },
   {
    title: '10日均线',
    align:"center",
    dataIndex: 'average10'
   },
   {
    title: '相关标的均线',
    align:"center",
    dataIndex: 'averageCorrelatedTotal'
   },
   {
    title: '相关标的120日均线',
    align:"center",
    dataIndex: 'averageCorrelated120'
   },
   {
    title: '相关标的60均线',
    align:"center",
    dataIndex: 'averageCorrelated60'
   },
   {
    title: '相关标的30日均线',
    align:"center",
    dataIndex: 'averageCorrelated30'
   },
   {
    title: '相关标的20日均线',
    align:"center",
    dataIndex: 'averageCorrelated20'
   },
   {
    title: '相关标的10日均线',
    align:"center",
    dataIndex: 'averageCorrelated10'
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
      label: "投资目标",
      field: 'target',
      component: 'JSearchSelect',
      componentProps:{
         dict:"financial_instruments,name,id"
      },
      //colProps: {span: 6},
 	},
	{
      label: "相关标的",
      field: 'corelatedTarget',
      component: 'JSearchSelect',
      componentProps:{
         dict:"financial_instruments,name,id"
      },
      //colProps: {span: 6},
 	},
	{
      label: "投资策略",
      field: 'strategy',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"invest_strategy_dict"
      },
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
    label: '投资目标',
    field: 'target',
    component: 'JSearchSelect',
    componentProps:{
       dict:"financial_instruments,name,id"
    },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资目标!'},
          ];
     },
  },
  {
    label: '相关标的',
    field: 'corelatedTarget',
    component: 'JSearchSelect',
    componentProps:{
       dict:"financial_instruments,name,id"
    },
  },
  {
    label: '投资策略',
    field: 'strategy',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"invest_strategy_dict"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资策略!'},
          ];
     },
  },
  {
    label: '投资频率（天）',
    field: 'frequency',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资频率（天）!'},
          ];
     },
  },
  {
    label: '投资时长（天）',
    field: 'duration',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入投资时长（天）!'},
          ];
     },
  },
  {
    label: '总资金',
    field: 'totalFunds',
    component: 'InputNumber',
  },
  {
    label: '单次资金',
    field: 'singleFunds',
    component: 'InputNumber',
  },
  {
    label: '单次上浮',
    field: 'singleRaise',
    component: 'InputNumber',
  },
  {
    label: '单次下沉',
    field: 'singleFall',
    component: 'InputNumber',
  },
  {
    label: '当前头寸',
    field: 'currentAvailable',
    component: 'InputNumber',
  },
  {
    label: '总投资',
    field: 'totalInvest',
    component: 'InputNumber',
  },
  {
    label: '总期数',
    field: 'totalInvestTimes',
    component: 'InputNumber',
  },
  {
    label: '持仓价格均线',
    field: 'averageTotal',
    component: 'InputNumber',
  },
  {
    label: '持仓120日均线',
    field: 'average120',
    component: 'InputNumber',
  },
  {
    label: '持仓60日均线',
    field: 'average60',
    component: 'InputNumber',
  },
  {
    label: '30日均线',
    field: 'average30',
    component: 'InputNumber',
  },
  {
    label: '20日均线',
    field: 'average20',
    component: 'InputNumber',
  },
  {
    label: '10日均线',
    field: 'average10',
    component: 'InputNumber',
  },
  {
    label: '相关标的均线',
    field: 'averageCorrelatedTotal',
    component: 'InputNumber',
  },
  {
    label: '相关标的120日均线',
    field: 'averageCorrelated120',
    component: 'InputNumber',
  },
  {
    label: '相关标的60均线',
    field: 'averageCorrelated60',
    component: 'InputNumber',
  },
  {
    label: '相关标的30日均线',
    field: 'averageCorrelated30',
    component: 'InputNumber',
  },
  {
    label: '相关标的20日均线',
    field: 'averageCorrelated20',
    component: 'InputNumber',
  },
  {
    label: '相关标的10日均线',
    field: 'averageCorrelated10',
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
  target: {title: '投资目标',order: 1,view: 'sel_search', type: 'string',dictTable: "financial_instruments", dictCode: 'id', dictText: 'name',},
  corelatedTarget: {title: '相关标的',order: 2,view: 'sel_search', type: 'string',dictTable: "financial_instruments", dictCode: 'id', dictText: 'name',},
  strategy: {title: '投资策略',order: 3,view: 'list', type: 'string',dictCode: 'invest_strategy_dict',},
  frequency: {title: '投资频率（天）',order: 4,view: 'number', type: 'number',},
  duration: {title: '投资时长（天）',order: 5,view: 'number', type: 'number',},
  totalFunds: {title: '总资金',order: 6,view: 'number', type: 'number',},
  singleFunds: {title: '单次资金',order: 7,view: 'number', type: 'number',},
  singleRaise: {title: '单次上浮',order: 8,view: 'number', type: 'number',},
  singleFall: {title: '单次下沉',order: 9,view: 'number', type: 'number',},
  currentAvailable: {title: '当前头寸',order: 10,view: 'number', type: 'number',},
  totalInvest: {title: '总投资',order: 12,view: 'number', type: 'number',},
  totalInvestTimes: {title: '总期数',order: 13,view: 'number', type: 'number',},
  averageTotal: {title: '持仓价格均线',order: 14,view: 'number', type: 'number',},
  average120: {title: '持仓120日均线',order: 15,view: 'number', type: 'number',},
  average60: {title: '持仓60日均线',order: 16,view: 'number', type: 'number',},
  average30: {title: '30日均线',order: 17,view: 'number', type: 'number',},
  average20: {title: '20日均线',order: 18,view: 'number', type: 'number',},
  average10: {title: '10日均线',order: 19,view: 'number', type: 'number',},
  averageCorrelatedTotal: {title: '相关标的均线',order: 20,view: 'number', type: 'number',},
  averageCorrelated120: {title: '相关标的120日均线',order: 21,view: 'number', type: 'number',},
  averageCorrelated60: {title: '相关标的60均线',order: 22,view: 'number', type: 'number',},
  averageCorrelated30: {title: '相关标的30日均线',order: 23,view: 'number', type: 'number',},
  averageCorrelated20: {title: '相关标的20日均线',order: 24,view: 'number', type: 'number',},
  averageCorrelated10: {title: '相关标的10日均线',order: 25,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
