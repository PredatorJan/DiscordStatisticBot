<template>
  <div class="contentView">
    <h2 class="title">Monitoring > Guilds</h2>
    <div class="div">
      <Suspense>
        <UserTable class="userTable" :user-id="tableData.selectedUserId.toString()" @userSelectedEvent="userSelected"/>
        <template #fallback>
          <LoadingComponent class="loading" message="Loading User Table!" />
        </template>
      </Suspense>

      <Suspense v-if="tableData.selectedUserId !== -1">
        <GuildTable class="guildTable" :user-id="tableData.selectedUserId.toString()" :key="tableData.selectedUserId.toString()" @guildSelectedEvent="guildSelected"/>
        <template #fallback>
          <LoadingComponent class="loading" message="Loading Guild Table!" />
        </template>
      </Suspense>
    </div>
    <div class="div">
      <Suspense v-if="tableData.selectedUserId !== -1">
        <UserCharts class="userCharts" :user-id="tableData.selectedUserId.toString()" :guild-id="tableData.selectedGuildId.toString()" :key="tableData.selectedUserId.toString() + '-' + tableData.selectedGuildId.toString()"/>
        <template #fallback>
          <LoadingComponent class="loading" message="Loading User Charts!" />
        </template>
      </Suspense>
    </div>
  </div>
</template>

<script setup lang="ts">
import LoadingComponent from '@/components/util/LoadingComponent.vue';
import UserCharts from '../../components/monitoring/users/UserCharts.vue';
import GuildTable from '../../components/monitoring/users/GuildTable.vue';
import UserTable from '../../components/monitoring/users/UserTable.vue';
import { reactive } from 'vue';

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
.title {
    margin: 0px;
}
.div {
  display: flex;
  flex: 2500px;
  flex-wrap: wrap;

  gap: 20px;
  margin-top: 20px;
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
  white-space: nowrap;
}
</style>