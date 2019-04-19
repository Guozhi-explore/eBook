/*
  通过mutation间接更新state的多个方法的对象
 */
import axios from 'axios'
import {
  RECEIVE_USERLIST,
  RECEIVE_BOOKLIST,
  RECEIVE_ORDERS,
  RECEIVE_CURRENT_USER,
  RECEIVE_CURRENT_STATUS,
  ADD_BOOK_TO_CART,
  SET_CURRENT_CLICK,
  ADD_USER,
  MODIFY_USER,
  MODIFY_BOOK,
  SET_CURRENT_SEARCH,
  RECEIVE_USER_CART,
  RECEIVE_USER_ORDER,
  CLERR_USER_PERSONAL_DATA
  //异步获取书籍列表
} from './mutation-types'

export default {
    getbooklist({commit,state}) {
        axios.get("/bookServlet",{
          params:{
            search_text:this.state.current_search
          }
        }).then(res=>{
          const books=res.data;            //data是axios自带的
          commit(RECEIVE_BOOKLIST,{books});
        });
    },
    getuserlist({commit,state}) {
      axios.get("/userServlet").then(res=>{
        const users=res.data;            //data是axios自带的
        console.log(this.books);
        commit(RECEIVE_USERLIST,{users});
      });
    },
    getorderlist({commit,state}) {
      axios.get("/orderManagerServlet").then(res=>{
        const orders=res.data;            //data是axios自带的
        commit(RECEIVE_ORDERS,{orders});
      });
    },
    getcurrentuser({commit},current_user){
      commit(RECEIVE_CURRENT_USER,{current_user});
    },
    setcurrentstatus({commit},current_status){
      commit(RECEIVE_CURRENT_STATUS,{current_status});
    },
    addbooktocart({commit},book_insert){
     /* axios.get("/updateCartServlet", {
          params: {
            user_id: this.state.current_user.user_id,
            book_id:book.book_id,
            update_num:book.number,
          }
        }                                       //由于经常数据异常（可能是异步访问的原因)
      )*/                                        //在浏览页和详情页点击加入购物车后，将不会直接写入cart数据库，而是保存到vuex里的cart中，于是只有在view-cart页面中点击保存或者提交按钮才会将数据保存至数据库
      //this.actions.getusercartlist(this.state.current_user.user_id);
      let existFlag=false;
      this.state.cart.forEach(book=>{
        if(book.book_id===book_insert.book_id)
        {
          book.number++;
          existFlag=true;
        }
        }
      )
      if(existFlag===false){
        this.state.cart.push(book_insert);                  //直接在actions里面更改状态
      }
      //commit(ADD_BOOK_TO_CART,{this.state.cart});
    },
    setcurrentclick({commit},book){
      commit(SET_CURRENT_CLICK,{book});
    },
    adduser({commit},user){
      commit(ADD_USER,{user});
    },
    modifyuser({commit},index,user)
    {
      commit(MODIFY_USER,{index,user});
    },
    modifybooks({commit},index,book)
    {
      commit(MODIFY_BOOK,{index,book});
    },
    setcurrentsearch({commit},current_search)
    {
      commit(SET_CURRENT_SEARCH,{current_search})
    },
  //异步获取用户列表

    getuserorderlist({commit,state},user_id) {
        axios.get("/orderItemsServlet",{
          params:{
            user_id:user_id
          }}
        ).then(res=>{
          const orders=res.data;            //data是axios自带的
          commit(RECEIVE_USER_ORDER,{orders});
        });
        },
    selectuserorderlist({commit,state},{time1,time2}) {
      axios.get("/selectorderItemsServlet",{
        params:{
          user_id:this.state.current_user.user_id,
          time1:time1,
          time2:time2
        }}
      ).then(res=>{
        const orders=res.data;            //data是axios自带的
        commit(RECEIVE_USER_ORDER,{orders});
      });
    },
    getusercartlist({commit,state},user_id) {
      axios.get("/cartServlet", {
          params: {
            user_id: user_id
          }
        }
      ).then(res => {
        const cart = res.data;            //data是axios自带的
        commit(RECEIVE_USER_CART, {cart});
      });
    },
    clearuserorderlist({commit,state}){
      commit(CLERR_USER_PERSONAL_DATA);
    }
}
