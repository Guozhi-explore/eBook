<template>
    <div>
      <nav-header></nav-header>
      <div class="dataselector">
        <div class="block">
          <span class="demonstration">日期筛选</span>
          <el-date-picker
            v-model="value1"
            type="datetimerange"
            value-format="yyyy-MM-dd hh:mm:ss"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </div>
        <el-button type="success" v-on:click="screenOrder">筛选</el-button>
        <el-button type="success" v-on:click="allOrder" style="margin-left: 10px">全部订单</el-button>
      </div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">时间</th>
          <th scope="col">总价</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(order,index) in user_order" :key="index" v-if="order.orderTime<time2&&order.orderTime>time1">
          <th scope="row">{{index+1}}</th>
          <td>{{order.orderTime}}</td>
          <td>{{order.orderMoney}}</td>
        </tr>
        </tbody>
      </table>
    </div>
</template>

<script>
    import NavHead from '../../components/header'
    import {mapState} from 'vuex'
    import axios from 'axios'
    export default {
        name: "order",
        data(){
          return{
            value1:'',
            valuetmp: ['1000-01-01 00:00:00','9999-01-01 00:00:00'],
            time1:'1000-01-01 00:00:00',
            time2:'9999-99-99 00:00:00'
          }

        },
      methods:{
        allOrder(){
          this.valuetmp=['1000-01-01 00:00:00','9999-01-01 00:00:00'];
          this.time1='1000-01-01 00:00:00';
          this.time2='9999-99-99 00:00:00';
          this.$store.dispatch("getuserorderlist",this.user.user_id);
        },
        screenOrder(){
          this.valuetmp=this.value1;
          this.time1=this.valuetmp[0];
          this.time2=this.valuetmp[1];
          this.$store.dispatch("getuserorderlist",this.user.user_id);
        }
      },
      components:{
          NavHeader:NavHead,
      },
      computed:{
          ...mapState({
            user:state=>state.current_user,
            user_order:state=>state.current_user_order,
          })
      }
    }
</script>

<style>
.dataselector{
  display: flex;
  flex-direction: row;
}
  .block{
    flex: 2;
  }
</style>
