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
    <el-table v-if="book_show" :data="books" class="tb-edit" style="width: 100%" highlight-current-row @change="uploadmodification(scope)">
      <el-table-column label="书名" width="180">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.name" placeholder="请输入内容" @change="uploadmodification(scope)"></el-input> <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者" width="180">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.author" placeholder="请输入内容" @change="uploadmodification(scope)"></el-input> <span>{{scope.row.author}}</span>
        </template>
      </el-table-column>
      <el-table-column label="isbn" width="180">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.isbn" placeholder="请输入内容" @change="uploadmodification(scope)"></el-input> <span>{{scope.row.isbn}}</span>
        </template>
      </el-table-column>
      <el-table-column label="库存" width="180">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.amount" placeholder="请输入内容" @change="uploadmodification(scope)" ></el-input> <span>{{scope.row.amount}}</span>
        </template>
      </el-table-column>
      <el-table-column label="销量" width="180">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.sales" placeholder="请输入内容" @change="uploadmodification(scope)"></el-input> <span>{{scope.row.sales}}</span>
        </template>
      </el-table-column>
      <el-table-column  label="价格">
        <template scope="scope">
          <el-input size="small" v-model="scope.row.price" placeholder="请输入内容" @change="uploadmodification(scope)"></el-input> <span>{{scope.row.price}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template scope="scope">
          <!--<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
          <el-button size="small" type="danger" @click="deletebook(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
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
          <th scope="col">书籍名称与数量</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders">
            <th scope="row">{{order.order_id}}</th>
            <td>{{order.user_account}}</td>
            <td>{{order.time}}</td>
            <td><span v-for="book in order.books">{{book}},</span></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="add_show">
      <form>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label >书名</label>
            <input class="form-control"  placeholder="情书" v-model="new_book.name">
          </div>
          <div class="form-group col-md-6">
            <label >作者</label>
            <input class="form-control" placeholder="文芊又"v-model="new_book.author">
          </div>
        </div>
        <div class="form-row">
        <div class="form-group col-md-6">
          <label >isbn编号</label>
          <input type="text" class="form-control" id="inputAddress" placeholder="999999" v-model="new_book.isbn">
        </div>
        <div class="form-group col-md-6">
          <label for="inputAddress2">库存量</label>
          <input type="text" class="form-control" id="inputAddress2" placeholder="1" v-model="new_book.amount">
        </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label>价格</label>
            <input type="text" class="form-control"  placeholder="无价" v-model="new_book.price">
          </div>
          <div class="form-group col-md-6">
            <label for="inputZip">图片</label>
            <input type="text" class="form-control" id="inputZip" placeholder="功能未实现">
          </div>
        </div>
        <button type="submit" class="btn btn-primary" v-on:click="submit_new_book">提交</button>
      </form>
    </div>
    </div>
</template>

<script>
   import Header from '../../components/header'
   import '../../assets/css/manager.css'
   import {mapState} from 'vuex'
   import axios from 'axios'
    export default {
      name: "manager",
      data() {
        return {
          book_show: true,
          user_show: false,
          order_show: false,
          add_show: false,
          new_book: {
            name: "",
            author: "",
            price: 0,
            isbn: "0",
            amount: 0,
            sales: 0,
            type: "",
            img_src: '../../assets/img/活着.jpg'
          }
        }

    },
      methods:{
          uploadmodification(scope){
            const book=this.books[scope.$index];
            axios.get("UpdateBook",{
              params:{
                //Integer book_id,String name,String author,String  isbn,String abstrac,String img_src,Integer price,Integer amount,Integer sales
                book_id:book.book_id,
                name:book.name,
                author:book.author,
                isbn:book.isbn,
                abstrac:book.abstract,
                img_src:book.img_src,
                price:book.price,
                amount:book.amount,
                sales:book.sales,
              }
            }).then(res=>{
              this.$notify({
                title:"修改成功"
              })
            })
          },
          deletebook(scope){
            axios.get("deleteBook",{
              params:{
                book_id:this.books[scope.$index].book_id
              }
            }).then(res=> {
              this.books.splice(scope.$index,1);
              this.$notify({
                title: "删除成功"
              })
              this.books.splice(index, 1);
              this.$store.dispatch("getbooklist");
            })
          },
          submit_new_book()
          {
            axios.get("addBook",{
              params:{
                //name, author, isbn, abstrac, img_src, price, amount
                name:this.new_book.name,
                author:this.new_book.author,
                isbn:this.new_book.isbn,
                abstrac:"mongodb",
                img_src:"http://localhost:8089/tmp.jpg",
                price:this.new_book.price,
                amount:this.new_book.amount,
              }
            }).then(res=> {
              this.$notify({
                title: "添加成功"
              })
              this.$store.dispatch("getbooklist");
            })
          },
          change_user_status(index,user)
          {
            user.status=(user.status==='valid'?'forbid':'valid');
            this.$store.dispatch("modifyuser",(index,user));
            axios.get("/changeUserStatus",
              {
                params:{
                  "user_id":user.user_id,
                  "status":user.status
                }
              }).then(res=> {
              this.$notify({
                title: "修改成功"
              })})
          }
      },
      computed:{
        ...mapState({
          orders:state=>state.orders,
          books: state => state.books,
          users: state => state.users,              //mapmapState通过扩展运算符将store.state.orderList 映射this.orderList  这个this 很重要，这个映射直接映射到当前Vue的this对象上。所以通过this都能将这些对象点出来，同理，mapActions, mapMutations都是一样的道理
          }),
      },
      components:{
          NavHeader:Header,
      }
    }
</script>

<style>
  .tb-edit .el-input {     /*edit table*/
    display: none
  }
  .tb-edit .current-row .el-input {
    display: block
  }
  .tb-edit .current-row .el-input+span {
    display: none
  }
</style>
