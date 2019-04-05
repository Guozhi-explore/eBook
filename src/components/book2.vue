<template>
  <div>
    <div id="book2_body">
        <div id="book2_img">
          <img v-bind:src="'../../static/img/'+current_click.img_src">
        </div>
        <div id="book2_intro">
          <div>
            <p>{{current_click.name}}</p>
            <p>{{current_click.author}}</p>
          </div>
          <div>
            <p>IBNS编号：{{current_click.esbn}}</p>
            <p>售价：{{current_click.price}}</p>
          </div>
        </div>
        <div id="book2_addtocart">
          <button type="button" class="btn btn-outline-success" v-on:click="add_book_to_cart">加入购物车</button>
        </div>
    </div>
    <div id="book2_abstract">
          <p> {{current_click.abstract}}</p>
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
        add_book_to_cart(){
          if(this.current_status==="unload")
          {
            alert("请先登录");
          }
          else
          {
            const book_for_cart={
              "img_src":this.current_click.img_src,
              "name":this.current_click.name,
              "author":this.current_click.author,
              "esbn":this.current_click.esbn,
              "price":this.current_click.price,
              "amount_in_store":this.current_click.amount_in_store,
              "amount_in_cart":1
            }
            this.$store.dispatch("addbooktocart",book_for_cart);
          }

        }
      },
      computed:{
          ...mapState(["current_click","current_status"])
      }

    }
</script>

<style>

</style>
