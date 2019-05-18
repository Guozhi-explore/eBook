<template>
    <div>
      <nav-header></nav-header>
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
        <div id="logon">
          <div id="logon_head">
            <div id="logon_head_left">
              <a v-on:click="change_log">
                登录
              </a>
            </div>
            <div id="logon_head_right">
              <a v-on:click="change_reg">
                注册
              </a>
            </div>
          </div>
          <hr>
          <br>
          <div id="logon_input">
            <div id="log_account">
            账号：
            <input id="input-account-id" name="id_text" size="20" maxlength="20" placeholder="" value="" v-model="user_account">
            <br>
              <br>
            </div>
            <div id="log_password">
            密码：
            <input id="input-password-id" type="password" name="id_password" size="20" maxlength="20" placeholder="" value="" v-model="user_password">
            <br>
              <br>
            </div>
            <div v-if="!islog">
            确认：
            <input id="affirm-password-id" type="password" size="20" maxlength="20" v-model="confirm_password">
            <br>
              <br>
            </div>
            <div id="log_mailboc" v-if="!islog">
              邮箱：
              <input id="mailbox" type="email"size="20" maxlength="20" v-model="mailbox">
              <br><br>
            </div>
            <button v-on:click="user_logon">
              提交
            </button>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
  import '../../assets/css/log.css'
  import NavHead from '../../components/header'
  import {mapState} from 'vuex'
  import axios from 'axios'
  import qs from 'qs'
    export default {
        name: "log",
      data(){
          return{
            islog:true,
            user_account:"",
            user_password:"",
            confirm_password:"",
            mailbox:"",
            user:[]
          };
      },
      computed:{
          ...mapState(['users']),
      },
      methods:{
        change_log() {
          this.islog = true;
        },
        change_reg() {
          this.islog = false;
        },
        user_logon(){
          if(this.islog)                                   //登录功能实现
          {
            axios.get('/judgeLogin',{
              params:{
                account:this.user_account,
                password:this.user_password
              }
            }).then(res=> {
                const user=res.data;
                if(res.data===null||res.data==="")
                {
                  this.$message(
                    '账号密码不正确'
                );
                  return;
                }
                else{
                  if(user.status==="forbid")
                  {
                    this.$message("你的账号已经被系统管理员禁用");
                  }
                  else {
                    this.$store.dispatch("getcurrentuser", user);
                    this.$store.dispatch("getbooklist");
                    if (user.ismanager) {
                      this.$store.dispatch("setcurrentstatus", "manager");
                      this.$store.dispatch("getorderlist");
                      this.$store.dispatch("getuserlist");
                      this.$router.push({path: '/root'});
                    } else {
                      this.$store.dispatch("setcurrentstatus", "user");
                      this.$store.dispatch("getuserorderlist",user.user_id);
                      this.$router.push({path: '/'});
                    }
                  }
                }
              })
          }
          else{                            //注册功能实现
            if(this.user_password!=this.confirm_password)
            {
              this.$message("两次密码输入不相同");
              return;
            }
            var re = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;  //检测邮箱是否合格
            if(!re.test(this.mailbox))
            {
              this.$message("邮箱格式不正确");
              return;
            }
            const user={
              "account":this.user_account,
              "password":this.user_password,
              "mailbox":this.mailbox,
              "status":"valid",
              "ismanager":false
            }
            axios.get(
              "/judgeRegister",
              {params:{
                  "account":this.user_account,
                  "password":this.user_password,
                  "mailbox":this.mailbox,
                  "status":"valid",
                  "ismanager":false
            }}
            ).then(result => {
              console.log(result.data);
              if(result.data===0)
              {
                this.$message("用户名已存在");
                return;
              }else {
                user.user_id=result.data;
                this.$store.dispatch("getuserorderlist", user.user_id);
                this.$store.dispatch("setcurrentstatus","user");
                this.$store.dispatch("getcurrentuser",user);
                this.$router.push({path:'/main'})
              }
            })
          }
        }
      },
      components:{
          NavHeader:NavHead
      }
    }
</script>

<style>

</style>
