<template>
  <div>
    <div id="book2_body">
        <div id="book2_img">
          <img v-bind:src="book.img_src">
        </div>
        <div id="book2_intro">
          <div>
            <p>{{book.name}}</p>
            <p>{{book.author}}</p>
          </div>
          <div>
            <p>IBNS编号：{{book.isbn}}</p>
            <p>售价：{{book.price}}</p>
          </div>
        </div>
        <div id="book2_addtocart">
          <button type="button" class="btn btn-outline-success" v-on:click="addtocart">加入购物车</button>
        </div>
    </div>
    <div id="book2_abstract">
          <p> {{book.abstrac}}</p>
    </div>

    <div id="book2_comment">
      <button class="btn btn-outline-success"v-on:click="setcommenttextvisible">
        我要点评
      </button>
      <button class="btn btn-outline-success" v-if="commenttext" style="margin-left: 5%" v-on:click="uploadcomment">
        提交评论
      </button>
      <el-input v-if="this.clicktocomment" style="width: 50%;margin-left: 5%"
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 3}"
        placeholder="请输入内容"
        v-model="commenttext">
      </el-input>
      <div style="margin: 60px 0;"></div>
      <div class="block">
        <div class="radio"style="font-size: 20px" :type="success">
          用户评论：
          <el-radio-group v-model="reverse">
            <el-radio :label="true" style="font-size: 20px">最新评论</el-radio>
            <el-radio :label="false"style="font-size: 20px">按时间序列查看</el-radio>
          </el-radio-group>
        </div>
        <el-timeline :reverse="reverse">
          <el-timeline-item
            v-for="(comment, index) in book.comments"
            :key="index"
            :timestamp="comment.time">
            <h5>评论者:{{comment.user_id}}</h5>
            <h5>评论内容:{{comment.content}}</h5>
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>
  </div>

</template>

<script>
    import '../assets/css/book2.css'
    import {mapState} from 'vuex'
    import axios from 'axios'
    export default {
        name: "book2",
      data(){
          return {
            reverse:true,
             clicktocomment:false,
            commenttext:""
          }
      },

      methods:{
        uploadcomment(){
          if(this.current_status==="unload")
          {
            this.$message({
              message: '请先登录',
              type: 'warning'
            });
          }
          else
            {
            axios.get("/uploadComment", {
              params: {
                book_id:this.book.book_id,
                user_id: this.user_id,
                content: this.commenttext
              }
            }).then(res => {
              if (res.data === "success") {
                this.$notify({
                  title: "提交成功"
                })
                this.$store.dispatch("getbooklist");
              }
            })
          }
        },
        setcommenttextvisible(){
          this.clicktocomment=true;
        },
        addtocart(book){
          if(this.current_status==="unload")
          {
            this.$message({
              message: '请先登录',
              type: 'warning'
            });
          }
          else
          {
            const book_for_cart={
              "book_id":this.book.book_id,
              "img_src":this.book.img_src,
              "name":this.book.name,
              "author":this.book.author,
              "esbn":this.book.esbn,
              "price":this.book.price,
              "amount":this.book.amount,
              "number":1
            }
            this.$store.dispatch("addbooktocart",book_for_cart);

            this.$notify({
              title: '添加成功',
            });
          }

        }
      },
      watch:{
        ...mapState({
          user_id:state=>state.current_user.account,
          book:state=>state.current_click,
          current_status:state=>state.current_status
        }),
      },
      computed:{
        ...mapState({
          user_id:state=>state.current_user.account,
          book:state=>state.current_click,
          current_status:state=>state.current_status
        }),
      }

    }
</script>

<style>

</style>
