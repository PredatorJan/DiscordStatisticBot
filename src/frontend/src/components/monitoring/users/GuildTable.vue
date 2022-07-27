<template>
  <div class="guildTable">
    <table class="table" v>
      <thead>
          <tr>
            <th>Discord ID</th>
            <th>Name</th>
            <th>Current Members</th>
            <th>Active Members</th>
            <th>Inactive Members</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="guild in guildData.guilds" :key="guild.id" @click="selectRow(guild.id); $emit('guildSelectedEvent', selectedGuildId.id);" v-bind:class="{selectedRow: selectedGuildId.id == guild.id, row: selectedGuildId.id != guild.id}">
          <td>{{ guild.discordId }}</td>
          <td>{{ guild.name }}</td>
          <td>{{ guild.currentMembers }}</td>
          <td>{{ guild.activeMembers }}</td>
          <td>{{ guild.inactiveMembers }}</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <th>Max. Guilds: {{ maxGuilds.count }}</th>
          <th></th>
          <th></th>
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
import { Guild } from '@/components/util/types/Types';

const props = defineProps<{
  userId: string
}>()
const emit = defineEmits([
  'guildSelectedEvent'
])


const showGuildEntries = 10;

let selectedGuildId = reactive({ id: -1 });

let page = reactive({ curPage: 1 });
let maxGuilds = reactive({ count: 0 });
let guilds: Guild[] = [];
let guildData = reactive({ guilds });

// requeset guilds from api
try {
  fetch("/api/guilds/user/" + props.userId + "?page=0&size=" + showGuildEntries)
  .then(res => res.json())
  .then(json => guildData.guilds = json);
} catch (error) {
  console.log(error);
}
// requeset guilds count from api
try {
  fetch("/api/guilds/count/" + props.userId)
  .then(res => res.json())
  .then(json => maxGuilds.count = json);
} catch (error) {
  console.log(error);
}


// computed

const maxPages = computed(() => {
  return Math.ceil(maxGuilds.count / showGuildEntries);
})

emit('guildSelectedEvent', selectedGuildId.id);

// functions

function selectRow(guildId: number) {
  if(selectedGuildId.id == guildId) {
    selectedGuildId.id = -1;
  } else {
    selectedGuildId.id = guildId;
  }
}

function nextPage() {
  if(page.curPage < maxPages.value) {
    page.curPage++;
    updateGuildTable();
  }
}

function prevPage() {
  if(page.curPage > 1) {
    page.curPage--;
    updateGuildTable();
  }
}

function updateGuildTable() {  
  try {
    fetch("/api/guilds/user/" + props.userId + "?page=" + (page.curPage - 1) + "&size=" + showGuildEntries)
    .then(res => res.json())
    .then(json => guildData.guilds = json);
  } catch (error) {
    console.log(error);
  }
}
</script>




<style scoped>

</style>