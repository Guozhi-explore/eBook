import {
  RECEIVE_USERLIST,
  RECEIVE_BOOKLIST,
  RECEIVE_ORDERS,
  RECEIVE_CURRENT_USER,
  RECEIVE_CURRENT_STATUS,
  ADD_BOOK_TO_CART,
  SET_CURRENT_CLICK,
  ADD_USER, MODIFY_USER, MODIFY_BOOK, SET_CURRENT_SEARCH,
} from './mutation-types'

export default {
  [RECEIVE_BOOKLIST](state,{books}){
    state.books=books;
  },
  [RECEIVE_USERLIST](state,{users}){
    state.users=users;
  },
  [RECEIVE_ORDERS](state,{orders}){
    state.orders=orders;
  },
  [RECEIVE_CURRENT_USER](state,{current_user})
  {
    state.current_user=current_user;
  },
  [RECEIVE_CURRENT_STATUS](state,{current_status})
  {
    state.current_status=current_status;
  },
  [ADD_BOOK_TO_CART](state,{book})
  {
    state.current_user.cart.push(book);
  },
  [SET_CURRENT_CLICK](state,{book})
  {
    state.current_click=book;
  },
  [ADD_USER](state,{user})
  {
    state.users.push(user);
  },
  [MODIFY_USER](state,{index,user})
  {
    state.users[index]=user;
  },
  [MODIFY_BOOK](state,{index,book})
  {
    state.books[index]=book;
  },
  [SET_CURRENT_SEARCH](state,{current_search})
  {
    state.current_search=current_search;
  }


}
