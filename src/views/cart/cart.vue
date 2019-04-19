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
          <tr v-for="(book,index) in cart":key="index">
            <th scope="row">
              <img id="book_img" v-bind:src="'../../static/img/'+book.img_src">
            </th>
            <td>{{book.name}}</td>
            <td>{{book.author}}</td>
            <td>{{book.amount}}</td>
            <td>
              <button class="btn-outline-success" v-on:click="change_amount(book,false,index)">-</button>
              {{book.number}}
              <button class="btn-outline-success" v-on:click="change_amount(book,true,index)">+</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div id="cart_total">
        <button class="btn-outline-success" v-on:click="keepCart">保存</button>
        <p>共{{book_number}}本书籍，{{book_value}}元</p>
        <button class="btn-outline-success" v-on:click="createOrder">结算</button>
      </div>
    </div>
</template>

<script>
    import Header from '../../components/header'
    import {mapState} from 'vuex'
    import '../../assets/css/cart.css'
    import axios from 'axios'
    export default {
        name: "cart",
        data(){
          return{
            book_number:0,
            book_value:0,
          }
        },
        components:{
          NavHeader:Header,
        },
      methods:{
          keepCart(){
            this.cart.forEach(book => {         //把最新的一次购物车数据写入数据库
              axios.get("updateCartServlet",{
                params:{
                  user_id:this.current_user.user_id,
                  book_id:book.book_id,
                  update_num:book.number,
                }
              });
            })
          },
          createOrder(){
            this.keepCart();                      //存在的问题，从用户登录到用户提交购物车的时间内，用户的数据都是死的，如果这个时候这本书被卖完了，用户还是能够下单，造成书的库存为负
                                                  //解决办法：1.在mysql中加入一个trigger，即任何一个购物车某一本书的数量不能超过这本书的库存量
            axios.get("/createOrderServlet", {    //2.要求用户先保存再提交，中间能够根据实时数据修改订单数量
                params: {
                  user_id: this.current_user.user_id
                }
              }
            ).then(res => {
              this.$store.dispatch("getuserorderlist",this.current_user.user_id);
              this.$store.dispatch("getusercartlist",this.current_user.user_id);
              this.book_number=0;
              this.book_value=0;
              this.$notify({
                title:'购买成功'
              });
            });
          },
        change_amount(book,flag,index){
            if(flag==true)
            {
              if(book.number<book.amount){
              book.number++;
              this.book_number++;
              this.book_value+=book.price;}
            }
            else{
              if(book.number>0){
              book.number--;
              this.book_number--;
              this.book_value-=book.price;
                if(book.number==0)
                {
                  this.cart.splice(index);
                }
              }
            }
        },
        calculate(){
          this.cart.forEach(book=>{
              this.book_number+=book.number,
                this.book_value+=book.number*book.price
            }
          )
        }
      },
      mounted(){
        this.calculate();
      },
     computed:{
       ...mapState({
         cart:state=>state.cart,
         current_user:state=>state.current_user
       }),
     }
    }
</script>

<style>

</style>
