<template>
  <div class="div">
    <h1 class="title clickable" @click="navigateToHome">Discord Statistic Bot</h1>
    <font-awesome-icon uiTheme="darkMode" class="themeSwitch clickable" icon="circle-half-stroke" size="2x" @click="toggleTheme"/>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter();
let darkMode = false;

function navigateToHome() {
  router.push("/");
}

function toggleTheme() {
  darkMode = !darkMode;

  document.documentElement.classList.toggle("darkTheme");
}
</script>

<style scoped>
.title {    
    margin: 0px;
}
.div {
  padding: 20px 20px 10px 20px;

  text-align: center;
}
.themeSwitch {
  position: absolute;
  right: 10px;
  top: 10px;

  padding: 5px;

  background-color: var(--background-header-color);
  color: var(--text-color);
}
</style>