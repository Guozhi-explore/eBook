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
  CLERR_USER_PERSONAL_DATA, CLEAR_USER_CART, CLEAR_USER_PERSONAL_DATA
  //异步获取书籍列表
} from './mutation-types'

export default {
    getbooklist({commit,state}) {
        axios.get("/allBook",{
          /*params:{
            search_text:this.state.current_search
          }*/
        }).then(res=>{
          const books=res.data;            //data是axios自带的
          commit(RECEIVE_BOOKLIST,{books});
        });
    },
    getuserlist({commit,state}) {
      axios.get("/allUser").then(res=>{
        const users=res.data;            //data是axios自带的
        //console.log(this.books);
        commit(RECEIVE_USERLIST,{users});
      });
    },
    getorderlist({commit,state}) {
      axios.get("/allOrder").then(res=>{
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
    addbooktocart({commit},book){
      let existFlag=false;
      this.state.cart.forEach(book1=>{
        if(book1.book_id===book.book_id)
        {
          book1.number++;
          existFlag=true;
        }
        },
      )
      if(existFlag===false){
         commit(ADD_BOOK_TO_CART,{book});
      }
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
        axios.get("/userOrder",{
          params:{
            user_id:user_id,
          }}
        ).then(res=>{
          const orders=res.data;            //data是axios自带的
          commit(RECEIVE_USER_ORDER,{orders});
        });
        },

    clearuserorderlist({commit,state}){
      commit(CLEAR_USER_PERSONAL_DATA);
    },
    clearusercart({commit,state}){
      commit(CLEAR_USER_CART);
    }
}
