<template>
  <div class="wrapperDiv">
    <div class="header">
      <MainHeader/>
      <MainMenuBar/>
    </div>
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

<script setup lang="ts">
import MainHeader from "./components/MainHeader.vue"
import MainMenuBar from "./components/MainMenuBar.vue"

</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  height: 100%;
}
.wrapperDiv {
  height: 100%;
}
.header{
  position: sticky;
  top: 0px;

  background-color: var(--background-header-color);
  color: var(--text-header-color);
}
.content {
  padding: 20px;
}
</style>
