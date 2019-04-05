<template>
  <div>
    <NavHeader></NavHeader>
    <div id="manager_footer">
       <button class="btn btn-outline-success"v-on:click="book_show=true,user_show=false,order_show=false,add_show=false">
         图书列表
       </button>
      <button class="btn btn-outline-success" v-on:click="book_show=false,user_show=true,order_show=false,add_show=false">
        用户列表
      </button>
      <button class="btn btn-outline-success" v-on:click="book_show=false,user_show=false,order_show=true,add_show=false">
        订单列表
      </button>
      <button class="btn btn-outline-success" v-on:click="book_show=false,user_show=false,order_show=false,add_show=true">
        添加书籍
      </button>
        </div>
    <div class="manager_table" v-if="book_show">
      <p class="manager_table_title" >图书列表(修改功能（可以包括增加书籍）尚未实现，预计后端实现时，使用bootstrap table edit</p>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">
            封面
          </th>
          <th scope="col">
            书名
          </th>
          <th scope="col">作者</th>
          <th scope="col">ESBN编号
            <button type="button" class="btn btn-outline-primary"@click="books=esbninc">↓</button>
            <button type="button" class="btn btn-outline-primary"v-on:click="books=esbndec">↑</button>
          </th>
          <th scope="col">销量
            <button type="button" class="btn btn-outline-primary"@click="books=salesinc">↓</button>
            <button type="button" class="btn btn-outline-primary"v-on:click="books=salesdec">↑</button>
          </th>
          <th scope="col">库存
            <button type="button" class="btn btn-outline-primary"v-on:click="books=amountinc">↓</button>
            <button type="button" class="btn btn-outline-primary" v-on:click="books=amountdec">↑</button>
          </th>
          <th scope="col">删除</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in books">
          <td scope="row" ><img v-bind:src="'../../static/img/'+item.img_src" class="manager_book_img"></td>
          <td>{{item.name}}</td>
          <td>{{item.author}}</td>
          <td>{{item.esbn}}</td>
          <td>{{item.sales}}</td>
          <td>{{item.amount}}</td>
          <td><button  type="button" class="btn btn-outline-primary">删除</button></td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-if="user_show">
      <p class="manager_table_title" >用户列表</p>
      <table class="table">
        <thead class="thead-light">
        <tr>
          <th scope="col">
            账号
          </th>
          <th scope="col">
            密码
          </th>
          <th scope="col">邮箱</th>
          <th scope="col">状态</th>
          <th scope="col">改变状态</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user,index) in users" :key="index">
          <th scope="row">{{user.account}}</th>
          <td>{{user.password}}</td>
          <td>{{user.mailbox}}</td>
          <td>{{user.status}}</td>
          <td>
            <button type="button" v-if="user.ismanager===false" class="btn btn-outline-primary" v-on:click="change_user_status(index,user)">
              <span v-if="user.status==='forbid'">启用</span>
              <span v-if="user.status==='valid'">禁用</span>
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-if="order_show">
      <p class="manager_table_title" >订单列表</p>
      <table class="table">
        <thead class="thead-light">
        <tr>
          <th scope="col">订单编号</th>
          <th scope="col">用户</th>
          <th scope="col">创建时间</th>
          <th scope="col">书籍名称</th>
          <th scope="col">书籍isbn</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders">
            <th scope="row"></th>
            <td>{{order.user.account}}</td>
            <td></td>
            <td><span v-for="book in order.books">{{book.name}},</span></td>
            <td><span v-for="book in order.books">{{book.esbn}},</span></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="add_show">
      <form>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="inputEmail4">书名</label>
            <input type="email" class="form-control" id="inputEmail4" placeholder="情书" v-model="new_book.name">
          </div>
          <div class="form-group col-md-6">
            <label for="inputPassword4">作者</label>
            <input type="password" class="form-control" id="inputPassword4" placeholder="文芊又"v-model="new_book.author">
          </div>
        </div>
        <div class="form-row">
        <div class="form-group col-md-6">
          <label for="inputAddress">esbn编号</label>
          <input type="text" class="form-control" id="inputAddress" placeholder="999999" v-model="new_book.esbn">
        </div>
        <div class="form-group col-md-6">
          <label for="inputAddress2">库存量</label>
          <input type="text" class="form-control" id="inputAddress2" placeholder="1" v-model="new_book.amount">
        </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="inputCity">类型</label>
            <input type="text" class="form-control" id="inputCity" placeholder="爱情" v-model="new_book.type">
          </div>
          <div class="form-group col-md-4">
            <label for="inputCity">价格</label>
            <input type="text" class="form-control"  placeholder="无价" v-model="new_book.price">
          </div>
          <div class="form-group col-md-2">
            <label for="inputZip">图片</label>
            <input type="text" class="form-control" id="inputZip" placeholder="功能未实现">
          </div>
        </div>
        <button type="submit" class="btn btn-primary">提交</button>
      </form>
    </div>
    </div>
</template>

<script>
   import Header from '../../components/header'
   import '../../assets/css/manager.css'
   import {mapState} from 'vuex'
    export default {
        name: "manager",
      data() {
        return {
            book_show:true,
            user_show:false,
            order_show:false,
            add_show:false,
            new_book:{
              name:"",
              author:"",
              price:0,
              esbn: "0",
              amount:0,
              sales:0,
              type:"",
              img_src: '../../assets/img/活着.jpg'
            }
        }
      },
      methods:{
          change_user_status(index,user)
          {
            console.log(index);
            console.log(user);
            user.status=(user.status==='valid'?'forbid':'valid');
            console.log(user.status);
            this.$store.dispatch("modifyuser",(index,user));
          }
      },
      computed:{
        ...mapState({
          orders:state=>state.orders,
          books: state => state.books,
          users: state => state.users,              //mapmapState通过扩展运算符将store.state.orderList 映射this.orderList  这个this 很重要，这个映射直接映射到当前Vue的this对象上。所以通过this都能将这些对象点出来，同理，mapActions, mapMutations都是一样的道理
          }),
            salesinc:function () {
              return this.books.sort((book1, book2) => book1.sales < book2.sales ? -1 : 1);
            },
            salesdec:function () {
              return this.books.sort((book1, book2) => book1.sales > book2.sales ? -1 : 1);
            },
            esbninc:function () {
              return this.books.sort((book1, book2) => book1.esbn < book2.esbn ? -1 : 1);
            },
            esbndec:function () {
              return this.books.sort((book1, book2) => book1.esbn > book2.esbn ? -1 : 1);
            },
            amountinc:function () {
              return this.books.sort((book1, book2) => book1.amount < book2.amount ? -1 : 1);
            },
            amountdec:function () {
              return this.books.sort((book1, book2) => book1.amount > book2.amount ? -1 : 1);
            }
      },
      components:{
          NavHeader:Header,
      }
    }
</script>

<style>

</style>
