<template>
    <div>
      <nav-header></nav-header>
      <br>
      <p style="text-align: center;font-size: 30px">购物车</p>
      <div>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">封面</th>
            <th scope="col">书名</th>
            <th scope="col">作者</th>
            <th scope="col">库存</th>
            <th scope="col">数量</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="book in current_user.cart">
            <th scope="row">
              <img id="book_img" v-bind:src="'../../static/img/'+book.img_src">
            </th>
            <td>{{book.name}}</td>
            <td>{{book.author}}</td>
            <td>{{book.amount_in_store}}</td>
            <td>
              <button class="btn-outline-success" v-on:click="book.amount_in_cart-=1">-</button>
              {{book.amount_in_cart}}
              <button class="btn-outline-success" v-on:click="book.amount_in_cart+=1">+</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div id="cart_total">
        <p>共{{book_number}}本书籍，{{book_value}}元</p>
        <button>结算</button>
      </div>
    </div>
</template>

<script>
    import Header from '../../components/header'
    import {mapState} from 'vuex'
    import '../../assets/css/cart.css'
    export default {
        name: "cart",
        data(){
          return{
            cart:[],
            book_number:0,
            book_value:0,
          }
        },
        components:{
          NavHeader:Header,
        },
     computed:{
       ...mapState({
         current_user:state=>state.current_user,
       }),
     }
    }
</script>

<style>

</style>
