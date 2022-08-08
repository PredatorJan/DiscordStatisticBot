<template>
  <div class="div">
    <font-awesome-icon v-if="props.status == 0" class="icon" icon="rotate" size="1x" spin />
    <h3 class="message">{{ message }}</h3>
  </div>
</template>

<script setup lang="ts">
let props = defineProps<{
    status: number,
    message: string
  }>()

</script>

<style scoped>
.title {
    margin: 0px;
}
.div {
  background-color: var(--background-first-depth);
  border-radius: 10px;
  padding: 20px;
}
.message {
  white-space: nowrap;

  margin: 0px;
}
</style>