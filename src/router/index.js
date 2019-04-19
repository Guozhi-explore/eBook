import Vue from 'vue'
import Router from 'vue-router'
import Mainpage from '../views/mainpage/mainpage.vue'
import Book from '../views/book_intro/book_intro'
import Cart from '../views/cart/cart'
import Manager from '../views/manager/manager'
import Log from '../views/log/log'
import Entrance from '../components/entrance'
import Order from '../views/order/order'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'mainpage',
      component:Mainpage
    },
    {
      path:'/books',
      name:'book_intro',
      component:Book
    },
    {
      path:'/cart',
      component: Cart
    },
    {
      path:'/order',
      component:Order
    },
    {
      path:'/root',
      component:Manager
    },
    {
      path:'/log',
      component:Log
    }
  ]
})
