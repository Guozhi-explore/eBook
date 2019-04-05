<template>
  <div>
    <nav class="navbar  bg-light justify-content-between navbar-expand-lg navbar-light ">
      <a class="navbar-brand" style="padding-left: 2%">eBook</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <button type="button" class="btn btn-outline-primary" style="margin-left: 20px" v-on:click="back_home">主页</button>
          </li>
          <li class="nav-item" v-if="current_status==='user'">
            <button type="button" class="btn btn-outline-primary"style="margin-left: 20px"v-on:click="move_cart">我的购物车</button>
          </li>
          <li class="nav-item" v-if="current_status==='manager'">
            <button type="button" class="btn btn-outline-primary"style="margin-left: 20px" v-on:click="move_root">管理员</button>
          </li>
          <li class="nav-item" v-if="current_status==='unload'">
            <button type="button" class="btn btn-outline-primary"style="margin-left: 20px" v-on:click="move_log">登录/注册</button>
          </li>
          <li class="nav-item" v-if="current_status==='user'">
            <button type="button" class="btn btn-outline-primary"style="margin-left: 20px" v-on:click="move_order">我的订单</button>
          </li>
          <li class="nav-item" v-if="current_status!='unload'">
            <button type="button" class="btn btn-outline-primary"style="margin-left: 20px" v-on:click="exit_load">退出登录</button>
          </li>
        </ul>
      </div>
      <form class="form-inline">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="search_text">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" v-on:click="search_commit">Search</button>
      </form>
    </nav>
  </div>
</template>
<script>
    import '../assets/css/header.css'
    import '../assets/css/bootstrap.min.css'
    import {mapState} from 'vuex'
    export default {
        name: "header_log",
        data(){
          return{
              search_text:"",
             status:user,
            books:[]
          }
        },
        methods: {
          exit_load(){
            this.$store.dispatch("setcurrentstatus","unload");
          },
          search_commit() {
            this.$store.dispatch("setcurrentsearch",this.search_text);
          },
          move_cart() {
            this.$router.push({path: '/cart'});
          },
          move_root() {
            this.$router.push({path: '/root'});
          },
          move_order(){
            this.$router.push({path:'/order'});
          },
          move_log() {
            this.$router.push({path: '/log'});
          },
          back_home() {
            this.$router.push({path: '/main'});
          }
        },
        computed:{
          ...mapState(['current_status','books'])
        }
    }
</script>

<style>

</style>
