<template>
  <div>
    <h4 class="text-base l-color-gray-300">{{ title }}</h4>
    <p class="text-sm font-medium">{{ text }}</p>
  </div>
</template>

<script setup>
const prop = defineProps({
  title: {
    type: String,
    require: false,
  },
  text: {
    type: String,
    require: false,
  },
});
</script>

<style></style>
