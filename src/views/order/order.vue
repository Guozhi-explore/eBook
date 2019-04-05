<template>
    <div>
      <nav-header></nav-header>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text">根据时间段筛选</span>
        </div>
        <input type="text" aria-label="First name" class="form-control">
        <input type="text" aria-label="Last name" class="form-control">
      </div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">时间</th>
          <th scope="col">书籍</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="order in current_user.orders">
          <th scope="row">{{order.id}}</th>
          <td>{{order.time}}</td>
          <td >
            <span v-for="book in order.books">{{book.name}},</span>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
</template>

<script>
    import NavHead from '../../components/header'
    import {mapState} from 'vuex'
    export default {
        name: "order",
      components:{
          NavHeader:NavHead,
      },
      computed:{
          ...mapState({
            current_user:state=>state.current_user,
          })
      }
    }
</script>

<style>

</style>
