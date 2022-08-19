<template>
  <div class="w-[824px] mx-auto mt-12 scroll-smooth" id="top">
    <!-- Menu - Breadcrumbs -->
    <div class="w-[824px] mx-auto mb-4">
      <div class="flex items-center">
        <router-link
          :to="{ name: 'users-manage' }"
          class="inline-flex items-center text-sm font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4"
        >
          <svg class="w-5 h-5 mr-1" viewBox="0 0 512 512">
            <path
              d="M427 234.625H167.296l119.702-119.702L256 85 85 256l171 171 29.922-29.924-118.626-119.701H427v-42.75z"
              fill="currentColor"
            ></path>
          </svg>
          Back to user manage
        </router-link>
      </div>
    </div>
    <div class="w-[824px] grid grid-cols-2 gap-6 mb-16">
      <div>
        <h1 class="font-bold text-4xl l-color-navi">User Detail</h1>
        <p class="font-light text-sm l-color-gray-300">
          Lasted upedate:
          {{ moment(user.updatedOn).format('DD-MM-YYYY HH:mm:ss') }}
        </p>
      </div>
      <div class="justify-self-end space-x-6 text-sm place-items-center">
        <button
          class="w-20 h-8 border border-blue-600 text-blue-600 hover:bg-blue-700 hover:text-white hover:border duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400"
        >
          Edit
        </button>
        <button
          class="w-20 h-8 bg-red-500 text-white font-medium hover:bg-red-700 hover:text-white duration-150"
        >
          Delete
        </button>
      </div>
    </div>

    <!-- Info - User -->
    <div class="w-[824px] grid grid-cols-2 gap-12">
      <Description :title="'Name'" :text="user.userName" />
      <Description :title="'Email'" :text="user.userEmail" />
      <Description :title="'Role'" :text="user.role" />
      <Description
        :title="'Create at'"
        :text="moment(user.createdOn).format('DD-MM-YYYY HH:mm:ss')"
      />
    </div>
  </div>
</template>

<script setup>
import { ref } from '@vue/reactivity';
import { onBeforeMount } from '@vue/runtime-core';
import { getUserById } from '../services/FetchServices.js';
import { useRoute, useRouter } from 'vue-router';
import moment from 'moment';
import Description from '../components/commons/lists/Description.vue';
const { params } = useRoute();

onBeforeMount(async () => {
  const res = await getUserById(params.id);
  user.value = res;
});

const user = ref({});
</script>

<style></style>
