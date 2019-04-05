<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    this.$store.dispatch("getorderlist");
    this.$store.dispatch('getbooklist');
    this.$store.dispatch('getuserlist');
  }
}
</script>


