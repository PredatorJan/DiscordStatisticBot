<template>
  <div id="menu" class="div closeMenu">
    
    
    <ul class="menuListList">
      <li class="menuListItem clickable" v-on:click="toggleMenu()">
        <font-awesome-icon icon="bars" size="2x"/>
      </li>
      <li class="menuListItem clickable" @click="navigateTo('/')">
        <font-awesome-icon icon="house" size="2x" />
        <h2 id="homeTitle" class="menuTitles hideTitles">Home</h2>
      </li>
      <li class="menuListItem clickable" @click="navigateTo('/setup')">
        <font-awesome-icon icon="wrench" size="2x"/>
        <h2 id="setupTitle" class="menuTitles hideTitles">Setup</h2>
      </li>
      <li class="menuListItem clickable" @click="navigateTo('/userMonitor')">
        <font-awesome-icon icon="desktop" size="2x"/>
        <h2 id="monitoringTitle" class="menuTitles hideTitles">Monitoring</h2>
      </li>
      <li class="menuListItem clickable" @click="navigateTo('/settings')">
        <font-awesome-icon icon="gear" size="2x"/>
        <h2 id="settingsTitle" class="menuTitles hideTitles">Settings</h2>
      </li>
      <li class="menuListItem clickable" @click="navigateTo('/profile')">
        <font-awesome-icon icon="gear" size="2x"/>
        <h2 id="settingsTitle" class="menuTitles hideTitles">Profile</h2>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter();

let isMenuOpen = false;


function navigateTo(routePath: string) {
  router.push(routePath);
}

function toggleMenu() {
  const menu = document.getElementById("menu");
  const home = document.getElementById("homeTitle");
  const setup = document.getElementById("setupTitle");
  const monitoring = document.getElementById("monitoringTitle");
  const settings = document.getElementById("settingsTitle");

  if(isMenuOpen) {
    // close menu
    menu?.classList.toggle("openMenu");
    menu?.classList.toggle("closeMenu");

    home?.classList.toggle("hideTitles");
    setup?.classList.toggle("hideTitles");
    monitoring?.classList.toggle("hideTitles");
    settings?.classList.toggle("hideTitles");

    isMenuOpen = false;
  } else {
    // open menu
    menu?.classList.toggle("closeMenu");
    menu?.classList.toggle("openMenu");

    home?.classList.toggle("hideTitles");
    setup?.classList.toggle("hideTitles");
    monitoring?.classList.toggle("hideTitles");
    settings?.classList.toggle("hideTitles");

    isMenuOpen = true;
  }
}

</script>

<style scoped>
.div {
  text-align: left;
  background-color: var(--background-first-depth);

  padding-top: 20px;
  padding-bottom: 10px;

  transition: width 0.5s;
}
.openMenu {
  width: 300px;
}
.openMenu>ul>li {
  grid-template-columns: 60px auto;
  justify-content: left;
}
.closeMenu {
  width: 100px;
}
.closeMenu>ul>li {
  grid-template-columns: min-content;
  justify-content: center;
}

.menuTitles {
  grid-column: 2;

  margin: 0px;
}
.hideTitles {
  display: none;
}

.menuListList {
  list-style: none;
  padding: 15px;
  margin: 0px;
}
.menuListItem {
  display: grid;

  padding: 10px;
  margin-top: 15px;
  margin-bottom: 15px;
}
.menuListItem:first-child {
  margin-top: 0px;
}
.menuListItem:last-child {
  margin-bottom: 0px;
}
.menuListItem:hover {
  background-color: var(--menu-hover-color);
}
</style>