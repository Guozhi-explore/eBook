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
            <th scope="col">isbn</th>
            <th scope="col">价格</th>
            <th scope="col">库存</th>
            <th scope="col">销量</th>
            <th scope="col">数量</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(book,index) in cart":key="index">
            <th scope="row">
              <img id="cart_book_img" v-bind:src="book.img_src">
            </th>
            <td>{{book.name}}</td>
            <td>{{book.author}}</td>
            <td>{{book.isbn}}</td>
            <td>{{book.price}}</td>
            <td>{{book.amount}}</td>
            <td>{{book.sales}}</td>
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

        createRequest(){
          //拼接request
          var request="/saveOrder?user_id=" +this.current_user.user_id.toString()
            +"&books=";
          this.cart.forEach(book=>{
              request+=book.book_id.toString();
              request+=','
            }
          )
          request=request.substring(0,request.length-1);
          request+="&bookNum="
          this.cart.forEach(book=>{
              request+=book.number.toString();
              request+=','
            }
          )
          request=request.substring(0,request.length-1);
          return request;
        },

        createOrder(){
          if(this.cart.length==0)
          {
            this.$notify({
              title:'购物车为空'
            });
            return;
          }
          var request=this.createRequest();

          axios.get(request,
          ).then(res => {
            this.$store.dispatch("getuserorderlist",this.current_user.user_id);
            this.calculate();
            this.$notify({
              title:'购买成功'
            });
            this.$store.dispatch("getbooklist")
            this.$store.dispatch("clearusercart");
            this.calculate();
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
                this.cart.splice(index,1);
              }
            }
          }
        },
        calculate(){
          this.book_number=0;
          this.book_value=0;
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
