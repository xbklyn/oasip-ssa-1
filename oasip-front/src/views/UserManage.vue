<template>
  <div class="w-[824px] mx-auto mt-12 scroll-smooth" id="top">
    <!-- Menu - Breadcrumbs -->
    <div class="w-[824px] mx-auto mb-4">
      <div class="flex items-center">
        <router-link
          :to="{ name: 'Home' }"
          class="inline-flex items-center text-sm font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4"
        >
          <svg class="w-5 h-5 mr-1" viewBox="0 0 512 512">
            <path
              d="M427 234.625H167.296l119.702-119.702L256 85 85 256l171 171 29.922-29.924-118.626-119.701H427v-42.75z"
              fill="currentColor"
            ></path>
          </svg>
          Back to home
        </router-link>
      </div>
    </div>
    <div class="w-[400px] grid gap-4">
      <h1 class="font-bold text-4xl l-color-navi">Users manage</h1>
      <p class="font-light text-sm l-color-gray-300">
        This page for listing and editing users. You can change the user
        information.
      </p>
    </div>

    <!-- Lists - Users -->
    <div class="grid justify-end text-sm">
      <button
        class="w-24 h-8 mb-6 border border-emerald-400 text-emerald-400 hover:bg-emerald-500 hover:text-white hover:border-none duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400"
      >
        Add user
      </button>
    </div>
    <div class="w-[824px] mx-auto mb-16">
      <UserList :data="getUsers" />
      <!-- <router-link :to="{ name: 'users-info', params: { id: '1' } }">
        <div
          class="w-full h-40 bg-white duration-150 px-12 py-10 drop-shadow-sm border hover:border-none hover:bg-gradient-to-br to-indigo-800 from-blue-500 hover:text-white"
        >
          <div class="w-full h-full">
            <h2 class="text-sm">Student</h2>
            <h1 class="text-2xl font-semibold">Fuck Design</h1>
            <h3 class="text-xs">fukdesign@worstdesign.co.th</h3>
          </div>
        </div>
      </router-link> -->
    </div>
  </div>
</template>

<script setup>
import { computed, onBeforeMount, ref } from '@vue/runtime-core';
import UserList from '../components/commons/users/UserList.vue';
import { getAllUsers } from '../services/FetchServices.js';

onBeforeMount(async () => {
  const res = await getAllUsers();
  users.value = res;
});

const users = ref({});

const getUsers = computed(() => {
  try {
    return users.value;
  } catch (error) {
    return [];
  }
});
</script>

<style></style>
