<template>
  <div>
    <div id="book2_body">
        <div id="book2_img">
          <img v-bind:src="'../../static/img/'+book.img_src">
        </div>
        <div id="book2_intro">
          <div>
            <p>{{book.name}}</p>
            <p>{{book.author}}</p>
          </div>
          <div>
            <p>IBNS编号：{{book.esbn}}</p>
            <p>售价：{{book.price}}</p>
          </div>
        </div>
        <div id="book2_addtocart">
          <button type="button" class="btn btn-outline-success" v-on:click="add_book_to_cart">加入购物车</button>
        </div>
    </div>
    <div id="book2_abstract">
          <p> {{book.abstract}}</p>
    </div>
  </div>

</template>

<script>
    import '../assets/css/book2.css'
    import {mapState} from 'vuex'
    export default {
        name: "book2",
      data(){
          return {
          }
      },

      methods:{
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
      computed:{
        ...mapState({
          book:state=>state.current_click,
          current_status:state=>state.current_status
        }),
      }

    }
</script>

<style>

</style>
