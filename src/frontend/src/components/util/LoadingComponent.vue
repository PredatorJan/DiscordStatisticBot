<template>
  <div class="div">
    <font-awesome-icon icon="rotate" size="1x" spin />
    <h3 class="message">{{ message }}</h3>
  </div>
</template>

<script setup lang="ts">
defineProps<{
    message: string
  }>()

</script>

<style scoped>
.title {
    margin: 0px;
}
.div {
  display: inline;

  background-color: var(--background-first-depth);
  border-radius: 10px;
  padding: 20px;
}
.message {
  display: inline;
  margin-left: 10px;

  white-space: nowrap;
}
</style>