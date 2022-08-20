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
        @click="addDialog = true"
        class="w-24 h-8 mb-6 border border-emerald-400 text-emerald-400 hover:bg-emerald-500 hover:text-white hover:border-none duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400"
      >
        Add user
      </button>
    </div>
    <div class="w-[824px] mx-auto mb-16">
      <UserList :data="users" />
    </div>

    <!-- Dialog - Add new user -->
    <div
      v-if="addDialog"
      class="grid place-items-center fixed top-0 right-0 left-0 z-50 bg-black/80 w-screen h-screen"
    >
      <div class="bg-white w-[960px] h-[520px] p-12">
        <div class="text-center text-2xl font-semibold">
          <h2 class="text-center">Add new user</h2>
        </div>
        <div>
          <input
            checked
            class=""
            type="radio"
            name="role"
            :value="'3'"
            v-model="addNewUser.role"
          />
          <label for=""> Student </label>
          <input
            type="radio"
            name="role"
            :value="'2'"
            v-model="addNewUser.role"
          />
          <label for="">Lecturer</label>
          <input
            type="radio"
            name="role"
            :value="'1'"
            v-model="addNewUser.role"
          />
          <label for="">Admin</label>
        </div>
        <div class="grid gap-12">
          <div class="relative">
            <input
              v-model="addNewUser.userName"
              type="text"
              :class="[
                'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
                responeError?.details?.userName
                  ? 'border-red-500 focus:border-red-500'
                  : '',
              ]"
              placeholder=" "
            />
            <label
              class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
            >
              Name<span class="text-red-500">*</span>
            </label>
            <p
              v-if="responeError?.details?.userName"
              class="absolute text-sm text-red-500 ml-2"
            >
              {{ responeError?.details?.userName }}
            </p>
          </div>
          <div class="relative">
            <input
              v-model="addNewUser.userEmail"
              type="text"
              :class="[
                'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
                responeError?.details?.userEmail
                  ? 'border-red-500 focus:border-red-500'
                  : '',
              ]"
              placeholder=" "
            />
            <label
              class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
            >
              Email<span class="text-red-500">*</span>
            </label>
            <p
              v-if="responeError?.details?.userEmail"
              class="absolute text-sm text-red-500 ml-2"
            >
              {{ responeError.details.userEmail }}
            </p>
          </div>
          <div class="w-full h-12 mt-12 space-x-6">
            <button
              :class="['w-24 h-full text-white duration-150 bg-emerald-500']"
              @click="postNewUser"
            >
              Add
            </button>
            <button
              :class="[
                'text-slate-500 h-full hover:text-slate-300 duration-150',
              ]"
              @click="addDialogClosed"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- ALERT - Succesfully -->
    <div
      v-if="SUCCESFUL"
      class="alert bg-black/40 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 w-full h-full"
    >
      <div
        class="relative p-4 w-full h-full grid place-items-center justify-center"
      >
        <div
          class="relative bg-white shadow l-w-520 h-72 grid place-items-center"
        >
          <div class="grid place-items-center gap-6">
            <div class="grid justify-center">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                aria-hidden="true"
                role="img"
                class="iconify iconify--clarity text-emerald-500"
                width="96"
                height="96"
                preserveAspectRatio="xMidYMid meet"
                viewBox="0 0 36 36"
              >
                <path
                  fill="currentColor"
                  d="M18 2a16 16 0 1 0 16 16A16 16 0 0 0 18 2Zm10.45 10.63L15.31 25.76L7.55 18a1.4 1.4 0 0 1 2-2l5.78 5.78l11.14-11.13a1.4 1.4 0 1 1 2 2Z"
                  class="clr-i-solid clr-i-solid-path-1"
                ></path>
                <path fill="none" d="M0 0h36v36H0z"></path>
              </svg>
            </div>
            <div class="text-center">
              <h2 class="text-xl font-semibold text-emerald-700 mb-2">DONE!</h2>
              <p class="text-md l-color-gray-300">
                Your scheduled is already deleted.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  computed,
  nextTick,
  onBeforeMount,
  onMounted,
  ref,
  watch,
} from '@vue/runtime-core';
import UserList from '../components/commons/users/UserList.vue';

onBeforeMount(async () => {
  await getAllUsers();
});

const users = ref({});
const addNewUser = ref({
  role: '',
  userName: '',
  userEmail: '',
});
const defaultUser = ref({
  role: '',
  userName: '',
  userEmail: '',
});
const responeError = ref({});
const defaultResponeError = ref({});
const addDialog = ref(false);
const SUCCESFUL = ref(false);
const ERROR = ref(false);

const addDialogClosed = () => {
  nextTick(() => {
    addDialog.value = false;
    addNewUser.value = Object.assign({}, defaultUser.value);
    responeError.value = Object.assign({}, defaultResponeError.value);
  });
};

// Fetch service
// GET METHOD - Get all users
const getAllUsers = async () => {
  await fetch(`${import.meta.env.VITE_BASE_URL}/users`)
    .then(async (res) => {
      if (res.status === 200) return (users.value = await res.json());
      throw new Error();
    })
    .catch((e) => {
      return e.message;
    });
};

// POST METHOD - Create new user
const postNewUser = async () => {
  await fetch(`${import.meta.env.VITE_BASE_URL}/users`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(addNewUser.value),
  }).then(async (res) => {
    if (res.ok) {
      SUCCESFUL.value = true;
      ERROR.value = false;
      setTimeout(function () {
        SUCCESFUL.value = false;
      }, 1500);
      await getAllUsers();
      addDialogClosed();
      return;
    } else {
      SUCCESFUL.value = false;
      ERROR.value = true;
      setTimeout(function () {
        ERROR.value = false;
      }, 1500);
      return (responeError.value = await res.json());
    }
  });
};
</script>

<style></style>
