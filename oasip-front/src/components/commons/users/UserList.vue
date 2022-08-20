<template>
  <div class="grid grid-cols-2 gap-6" v-if="data.length > 0">
    <router-link
      v-for="(value, i) in data"
      :key="i"
      :to="{ name: 'user-info', params: { id: value.userId } }"
    >
      <div
        class="w-full h-[188px] bg-white duration-150 px-12 py-10 drop-shadow-sm border hover:border-none hover:bg-gradient-to-br to-indigo-800 from-blue-500 hover:text-white"
      >
        <div class="w-full h-full">
          <h2 class="text-sm">{{ value.role }}</h2>
          <h1 class="text-2xl font-semibold">{{ value.userName }}</h1>
          <h3 class="text-xs">{{ value.userEmail }}</h3>
        </div>
      </div>
    </router-link>
  </div>
  <div v-else>
    <div class="grid justify-center place-items-center my-24">
      <h1 class="text-7xl l-color-gray text-center select-none">
        No any users at this moment
      </h1>
    </div>
  </div>
</template>

<script setup>
import { computed } from '@vue/runtime-core';

const prop = defineProps({
  data: {
    type: Array,
    require: false,
  },
});
</script>

<style></style>
