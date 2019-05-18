<template>
    <div>
      <el-row>
        <el-col :span="6" offset="11">
          <div class="grid-content bg-purple-dark">
            <el-button
              plain
              @click="open" style="text-align: center" type="success">
              copyright
            </el-button>
          </div>
        </el-col>
      </el-row>


    </div>
</template>

<script>
    import '../assets/css/message.css'
    export default {
        name: "message",
      methods:{
        open() {
          const h = this.$createElement;

          this.$notify({
            title: 'copyright',
            message: h('i', { style: 'color: teal'}, 'ebook 上海交通大学软件学院郭志 Github  https://github.com/Guozhi-explore/Ebook')
          });
        },
      }
    }
</script>

<style>

</style>
