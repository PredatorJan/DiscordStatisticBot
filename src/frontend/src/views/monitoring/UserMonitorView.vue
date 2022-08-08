<template>
  <div class="contentView div">
    <h2 class="title">Monitoring > Users</h2>
    
    <Suspense>
      <UserTable class="userTable" :user-id="tableData.selectedUserId.toString()" @userSelectedEvent="userSelected"/>
      <template #fallback>
        <LoadingComponent class="loading userTable" :status="userTableStatus.status" :message="userTableStatus.message" />
      </template>
    </Suspense>

    <Suspense v-if="tableData.selectedUserId !== -1">
      <GuildTable class="guildTable" :user-id="tableData.selectedUserId.toString()" :key="tableData.selectedUserId.toString()" @guildSelectedEvent="guildSelected"/>
      <template #fallback>
        <LoadingComponent class="loading guildTable" :status="guildTableStatus.status" :message="guildTableStatus.message" />
      </template>
    </Suspense>

    <Suspense v-if="tableData.selectedUserId !== -1">
      <UserCharts class="userCharts" :user-id="tableData.selectedUserId.toString()" :guild-id="tableData.selectedGuildId.toString()" :key="tableData.selectedUserId.toString() + '-' + tableData.selectedGuildId.toString()"/>
      <template #fallback>
        <LoadingComponent class="loading userCharts" :status="userChartsStatus.status" :message="userChartsStatus.message" />
      </template>
    </Suspense>
  </div>
</template>

<script setup lang="ts">
import LoadingComponent from '@/components/util/LoadingComponent.vue';
import UserCharts from '../../components/monitoring/users/UserCharts.vue';
import GuildTable from '../../components/monitoring/users/GuildTable.vue';
import UserTable from '../../components/monitoring/users/UserTable.vue';
import { onErrorCaptured, reactive } from 'vue';


let userTableStatus = reactive({
  status: 0,
  message: "Loading User Table..."
});
let guildTableStatus = reactive({
  status: 0,
  message: "Loading Guild Table..."
});
let userChartsStatus = reactive({
  status: 0,
  message: "Loading User Charts..."
});

onErrorCaptured(e => {
  console.log(e.name);


  if(e.name == 'userTable') {
    userTableStatus.status = 1;
    userTableStatus.message = e.message;
  } else if(e.name == 'guildTable') {
    guildTableStatus.status = 1;
    guildTableStatus.message = e.message;
  } else if(e.name == 'userCharts') {
    userChartsStatus.status = 1;
    userChartsStatus.message = e.message;
  } else {
    console.log("UnexpectedError from '" + e.name + "' with message '" + e.message + "'");
  }
  return true;
})


let tableData = reactive({
  selectedUserId: -1,
  selectedGuildId: -1
});


function userSelected(userId: number) {
  tableData.selectedUserId = userId;
}
function guildSelected(guildId: number) {
  tableData.selectedGuildId = guildId;
}
</script>


<style scoped>
.div {
  display: grid;
  gap: 20px;
}
.userTable {
  grid-column: 1;
}
.guildTable {
  grid-column: 2;
}
.charts {
  grid-column: 1;

  margin-top: 0px;
}
.charts > h3 {
  white-space: nowrap;
}


.loading {
  display: flex;
}
</style>