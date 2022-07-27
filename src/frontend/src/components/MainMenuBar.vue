<template>
  <div id="menu" class="div closeMenu">
    
    <div class="menuListItem clickable" @click="navigateTo('/')">
      <div class="menuListTitle">
        <font-awesome-icon icon="house" size="xl" />
        <h2 class="menuTitles">Home</h2>
      </div>
    </div>
    <div class="menuListItem clickable" @click="navigateTo('/setup')">
      <div class="menuListTitle">
        <font-awesome-icon icon="wrench" size="xl"/>
        <h2 class="menuTitles">Setup</h2>
      </div>
    </div>
    <div class="menuListItem">
      <div class="menuListTitle">
        <font-awesome-icon icon="desktop" size="xl"/>
        <h2 class="menuTitles">Monitoring</h2>
      </div>

      <div class="subMenu">
        <div class="subMenuItem clickable" @click.stop="navigateTo('/monitoring/guilds')">
          <font-awesome-icon icon="server" size="xl"/>
          <h3 class="menuTitles">Guilds</h3>
        </div>
        <div class="subMenuItem clickable" @click.stop="navigateTo('/monitoring/users')">
          <font-awesome-icon icon="users" size="xl"/>
          <h3 class="menuTitles">Users</h3>
        </div>
      </div>
    </div>
    <div class="menuListItem">
      <div class="menuListTitle">
        <font-awesome-icon icon="gear" size="xl"/>
        <h2 class="menuTitles">Settings</h2>
      </div>

      <div class="subMenu">
        <div class="subMenuItem clickable" @click.stop="navigateTo('/settings/profile')">
          <font-awesome-icon icon="user" size="xl"/>
          <h3 class="menuTitles">Profile</h3>
        </div>
        <div class="subMenuItem clickable" @click.stop="navigateTo('/settings/guilds')">
          <font-awesome-icon icon="server" size="xl"/>
          <h3 class="menuTitles">Guilds</h3>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter();

function navigateTo(routePath: string) {
  router.push(routePath);
}


</script>

<style scoped>
.div {
  display: flex;
  justify-content: space-evenly;
}

.menuTitles {
  margin: 0px;
  margin-left: 15px;
}
.menuListItem {
  position: relative;

  border-radius: 5px 5px 0px 0px;
}
.menuListItem:hover {
  background-color: var(--menu-hover-color);
}

.menuListItem:hover > .subMenu {
  display: block;
}

.menuListTitle {
  display: flex;
  padding: 10px 20px 10px 20px;
}


.subMenu {
  display: none;
  width: 100%;

  background-color: var(--background-header-color);
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.5);

  border-radius: 0px 0px 5px 5px;

  position: absolute;
  z-index: 1;
}
.subMenuItem {
  display: flex;
  padding: 15px 25px 15px 25px;
}
.subMenuItem:last-child {
  border-radius: 0px 0px 5px 5px;
}
.subMenuItem:hover {
  background-color: var(--menu-hover-color);
}


@media screen and (max-width: 750px) {
  .menuTitles {
    display: none;
  }
}
</style>