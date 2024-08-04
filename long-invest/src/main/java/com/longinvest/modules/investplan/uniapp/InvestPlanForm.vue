<template>
    <view>
        <!--标题和返回-->
		<cu-custom :bgColor="NavBarColor" isBack :backRouterName="backRouteName">
			<block slot="backText">返回</block>
			<block slot="content">投资计划</block>
		</cu-custom>
		 <!--表单区域-->
		<view>
			<form>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">名称：</text></view>
                  <input  placeholder="请输入名称" v-model="model.name"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">投资目标：</text></view>
                  <input  placeholder="请输入投资目标" v-model="model.target"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的：</text></view>
                  <input  placeholder="请输入相关标的" v-model="model.corelatedTarget"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">投资策略：</text></view>
                  <input  placeholder="请输入投资策略" v-model="model.strategy"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">投资频率（天）：</text></view>
                  <input type="number" placeholder="请输入投资频率（天）" v-model="model.frequency"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">投资时长（天）：</text></view>
                  <input type="number" placeholder="请输入投资时长（天）" v-model="model.duration"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">总资金：</text></view>
                  <input type="number" placeholder="请输入总资金" v-model="model.totalFunds"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">单次资金：</text></view>
                  <input type="number" placeholder="请输入单次资金" v-model="model.singleFunds"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">单次上浮：</text></view>
                  <input type="number" placeholder="请输入单次上浮" v-model="model.singleRaise"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">单次下沉：</text></view>
                  <input type="number" placeholder="请输入单次下沉" v-model="model.singleFall"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">当前头寸：</text></view>
                  <input type="number" placeholder="请输入当前头寸" v-model="model.singlePosition"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">当前次数：</text></view>
                  <input type="number" placeholder="请输入当前次数" v-model="model.currentInvestTimes"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">总投资：</text></view>
                  <input type="number" placeholder="请输入总投资" v-model="model.totalInvest"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">总期数：</text></view>
                  <input type="number" placeholder="请输入总期数" v-model="model.totalInvestTimes"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">持仓价格均线：</text></view>
                  <input type="number" placeholder="请输入持仓价格均线" v-model="model.averageTotal"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">持仓120日均线：</text></view>
                  <input type="number" placeholder="请输入持仓120日均线" v-model="model.average120"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">持仓60日均线：</text></view>
                  <input type="number" placeholder="请输入持仓60日均线" v-model="model.average60"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">30日均线：</text></view>
                  <input type="number" placeholder="请输入30日均线" v-model="model.average30"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">20日均线：</text></view>
                  <input type="number" placeholder="请输入20日均线" v-model="model.average20"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">10日均线：</text></view>
                  <input type="number" placeholder="请输入10日均线" v-model="model.average10"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的均线：</text></view>
                  <input type="number" placeholder="请输入相关标的均线" v-model="model.averageCorrelatedTotal"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的120日均线：</text></view>
                  <input type="number" placeholder="请输入相关标的120日均线" v-model="model.averageCorrelated120"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的60均线：</text></view>
                  <input type="number" placeholder="请输入相关标的60均线" v-model="model.averageCorrelated60"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的30日均线：</text></view>
                  <input type="number" placeholder="请输入相关标的30日均线" v-model="model.averageCorrelated30"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的20日均线：</text></view>
                  <input type="number" placeholder="请输入相关标的20日均线" v-model="model.averageCorrelated20"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">相关标的10日均线：</text></view>
                  <input type="number" placeholder="请输入相关标的10日均线" v-model="model.averageCorrelated10"/>
                </view>
              </view>
				<view class="padding">
					<button class="cu-btn block bg-blue margin-tb-sm lg" @click="onSubmit">
						<text v-if="loading" class="cuIcon-loading2 cuIconfont-spin"></text>提交
					</button>
				</view>
			</form>
		</view>
    </view>
</template>

<script>
import myDate from '@/components/my-componets/my-date.vue'

export default {
        name: "InvestPlanForm",
        components:{ myDate },
        props:{
          formData:{
              type:Object,
              default:()=>{},
              required:false
          }
        },
        data(){
            return {
				CustomBar: this.CustomBar,
				NavBarColor: this.NavBarColor,
				loading:false,
                model: {},
                backRouteName:'index',
                url: {
                  queryById: "/investplan/investPlan/queryById",
                  add: "/investplan/investPlan/add",
                  edit: "/investplan/investPlan/edit",
                },
            }
        },
        created(){
             this.initFormData();
        },
        methods:{
           initFormData(){
               if(this.formData){
                    let dataId = this.formData.dataId;
                    this.$http.get(this.url.queryById,{params:{id:dataId}}).then((res)=>{
                        if(res.data.success){
                            console.log("表单数据",res);
                            this.model = res.data.result;
                        }
                    })
                }
            },
            onSubmit() {
                let myForm = {...this.model};
                this.loading = true;
                let url = myForm.id?this.url.edit:this.url.add;
				this.$http.post(url,myForm).then(res=>{
				   console.log("res",res)
				   this.loading = false
				   this.$Router.push({name:this.backRouteName})
				}).catch(()=>{
					this.loading = false
				});
            }
        }
    }
</script>
