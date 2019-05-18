<template>
    <div>
      <nav-header></nav-header>
      <div>
        <div id="footer" style="padding-left: 20px">
          <span class="badge badge-light" style="padding-left: 10px;font-size: 18px" v-on:click="this.books=new_book">新书</span>
          <span class="badge badge-light"style="padding-left: 10px;font-size: 18px" v-on:click="this.books=hot_book">热门</span>
          <span class="badge badge-light"style="padding-left: 10px;font-size: 18px" v-on:click="this.books=cheap_book">特价</span>
        </div>
      </div>
      <div v-for="book in books">
        <nav-book v-bind:book="book" v-if="current_search===''||book.name===current_search||book.author===current_search"></nav-book>
      </div>
      <nav-message></nav-message>
    </div>
</template>
<script>
    import navheaders from '../../components/header'
    import navbook from '../../components/book'
    import navmessage from '../../components/message'
    import {mapState} from 'vuex'
    export default {
        name: "mainpage.vue",
        components:{
          NavHeader:navheaders,
          NavBook:navbook,
          NavMessage:navmessage
        },
      data(){
          return{
           target_books:[],
          }
      },
      computed:{
        ...mapState(['books']),
        ...mapState(['current_search']),
        new_book:function () {
          return this.books.sort((book1, book2) => book1.isbn > book2.isbn ? -1 : 1);
        },
        hot_book:function () {
          return this.books.sort((book1, book2) => book1.sales > book2.sales? -1 : 1);
        },
        cheap_book:function () {
          return this.books.sort((book1, book2) => book1.price < book2.price ? -1 : 1);
        },
      }
    }
</script>

<style>

</style>
