<template>
    <div>
      <nav-header></nav-header>
      <nav-book></nav-book>
    </div>
</template>

<script>
    import Header from '../../components/header'
    import Book from '../../components/book2'
    export default {
        name: "book_intro",
      data(){
          return{
            book:[]
          }
      },
      components:{
          NavHeader: Header,
        NavBook:Book
      }
    }
</script>

<style>

</style>
