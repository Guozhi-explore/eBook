<template>
  <div>
    <div id="login_body">
      <br>
      <hr>
      <div id="intro">
        <p>
          你经常对着书店里的绵绵不绝的封面发呆吗？或者头晕脑涨地从音像店的琳琅满目中逃出？宽带下载和网上购物降临之后，即使在最小的城镇，你的选择也在每天成百上千地增加。这其中一定有你会喜爱的东西，但十有八九它们会在不知不觉中和你擦肩而过。媒体让老少咸宜的大片无处不在，对只适合一群人的东西却显得力不从心。
        </p>
        <p>
          eBook的发起者正是为了让更多人寻找到自己心仪的那一本书，在这里，你可以定制你的私人书单，我们精心为你挑选你最喜爱的书籍，最合适你的标签；在这里，你可以一步步实现你的读书计划，查询你的读书轨迹；在这里，你可以用享受最优质的服务，为了你的浏览体验，我们拒绝广告，拒绝弹窗。。。
        </p>
        <p>
          在这光怪陆离的世界里，eBook能成为你人生旅途中一个独一无二的港湾
        </p>
      </div>
      <div id="enter">
        <button class="btn-primary" v-on:click="move_mainpage">开启eBook之旅</button>
      </div>
    </div>
  </div>
</template>

<script>
    import  '../assets/css/bootstrap.min.css'
    import '../assets/css/entrance.css'
    export default {
        name: "entrance",
      methods:{
          move_mainpage(){
            this.$router.push({path:'/main'});
          }
      }
    }
</script>

<style>

</style>
