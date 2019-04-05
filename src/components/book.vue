<template>
    <div>
      <div id="book">
        <div id="div_img">
          <img id="book_img" v-bind:src="'../../static/img/'+book.img_src"  v-on:click="book_message">
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
          <button type="button"v-on:click="addtocart(book)" class="btn btn-outline-success">加入购物车</button>
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
                  alert("请先登录");
                }
                else
                {
                  const book_for_cart={
                    "img_src":this.book.img_src,
                    "name":this.book.name,
                    "author":this.book.author,
                    "esbn":this.book.esbn,
                    "price":this.book.price,
                    "amount_in_store":this.book.amount,
                    "amount_in_cart":1
                  }
                  this.$store.dispatch("addbooktocart",book_for_cart);
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
