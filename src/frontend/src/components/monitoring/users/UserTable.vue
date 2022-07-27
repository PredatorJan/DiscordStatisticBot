<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>Discord ID</th>
          <th>Name</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in userData.users" :key="user.id" @click="selectRow(user.id); $emit('userSelectedEvent', selectedUserId.id);" v-bind:class="{selectedRow: selectedUserId.id == user.id, row: selectedUserId.id != user.id}">
          <td>{{ user.discordid }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.role.name }}</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <th>Max. Guilds: {{ maxUsers.count }}</th>
          <th>Page: {{ page.curPage }} / {{ maxPages }}</th>
          <th>
            <div class="tablePageButtons">
              <font-awesome-icon class="clickable" icon="circle-left" size="lg" v-on:click="prevPage()"/>
              <font-awesome-icon class="clickable" icon="circle-right" size="lg" v-on:click="nextPage()"/>
            </div>
          </th>
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script setup lang="ts">
import { reactive, computed } from 'vue';
import { User } from '@/components/util/types/Types';


let selectedUserId = reactive({ id: -1 });

const showUserEntries = 10;

let page = reactive({ curPage: 1 });
let maxUsers = reactive({ count: 0 });
let users: User[] = [];
let userData = reactive({ users });

// requeset users from api
try {
  fetch("/api/users?page=0&size=" + showUserEntries)
  .then(res => res.json())
  .then(json => userData.users = json);
} catch (error) {
  console.log(error);
}
// requeset users count from api
try {
  fetch("/api/users/count")
  .then(res => res.json())
  .then(json => maxUsers.count = json);
} catch (error) {
  console.log(error);
}

// computed

const maxPages = computed(() => {
  return Math.ceil(maxUsers.count / showUserEntries);
})


// functions

function selectRow(userId: number) {
  if(selectedUserId.id == userId) {
    selectedUserId.id = -1;
  } else {
    selectedUserId.id = userId;
  }
}

function nextPage() {
  if(page.curPage < maxPages.value) {
    page.curPage++;
    updateUserTable();
  }
}

function prevPage() {
  if(page.curPage > 1) {
    page.curPage--;
    updateUserTable();
  }
}

async function updateUserTable() {  
  try {
    fetch("/api/users?page=" + (page.curPage - 1) + "&size=" + showUserEntries)
    .then(res => res.json())
    .then(json => userData.users = json);
  } catch (error) {
    console.log(error);
  }
}
</script>



<style scoped>

</style>