<template>
    <div>
      <div id="book">
        <div id="div_img">
          <img id="book_img" v-bind:src="book.img_src"  v-on:click="book_message">
        </div>
        <div id="div_message">
          <p>
            {{book.name}}
          </p>
          <p>
            {{book.author}}
          </p>
          <p>
            {{book.price}}
          </p>
        </div>
        <div id="shoping_cart">
          <el-button type="success"v-on:click="addtocart(book)" >加入购物车</el-button>
        </div>
    </div>
      <hr>
    </div>
</template>

<script>
    import '../assets/css/book.css'
    import {mapState} from 'vuex'
    export default {
        name: "book",
        data(){
          return{
            book:[]
          }
        },
        props:["book"],
        methods:{
               book_message() {
                this.$store.dispatch("setcurrentclick",this.book);
                this.$router.push({path:'books'});
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
                    "book_id":book.book_id,
                    "img_src":book.img_src,
                    "name":book.name,
                    "author":book.author,
                    "isbn":book.isbn,
                    "price":book.price,
                    "amount":book.amount,
                    "number":1

                  };
                  this.$store.dispatch("addbooktocart",book_for_cart);

                  this.$notify({
                    title: '添加成功',
                  });
                }

          }
        },
      computed:{
          ...mapState(['current_status','current_user'])
      }
    }
</script>

<style>

</style>
