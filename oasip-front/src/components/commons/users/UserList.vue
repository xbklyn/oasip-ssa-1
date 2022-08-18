<template>
  <div>
    <router-link v-for="(value, i) in data" :key="i" :to="''">
      <div
        class="w-full h-[188px] bg-white duration-150 px-12 py-10 drop-shadow-sm border hover:border-none hover:bg-gradient-to-br to-indigo-800 from-blue-500 hover:text-white"
      >
        <div class="w-full h-full">
          <h2 class="text-sm">{{ value.role }}</h2>
          <h1 class="text-2xl font-semibold">{{ value.name }}</h1>
          <h3 class="text-xs">{{ value.email }}</h3>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
const prop = defineProps({
  data: {
    type: Array,
    require: false,
  },
});
</script>

<style></style>
