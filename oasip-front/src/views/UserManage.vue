<template>
  <div>
    <div v-if="isUserLoaded">
      <BaseLoader />
    </div>

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
      <!-- <button @click="getRT()">ควย</button> -->
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
      <UserList :data="getUser" />
    </div>

    <!-- Dialog - Add new user -->
    <div
      v-if="addDialog"
      class="grid place-items-center fixed top-0 right-0 left-0 z-50 bg-black/80 w-screen h-screen"
    >
      <div class="bg-white w-[960px] h-[520px] p-12 overflow-y-auto">
        <div class="text-center text-2xl font-semibold">
          <h2 class="text-center">Add new user</h2>
        </div>

        <!-- User role -->
        <div class="mb-3 flex">
          <div class="">
            <input
              checked
              class="mr-1"
              type="radio"
              name="role"
              :value="'student'"
              v-model="addNewUser.role"
            />
            <label for="" class="mr-3">Student</label>
          </div>
          <div class="mx-2">
            <input
              class="mr-1"
              type="radio"
              name="role"
              :value="'lecturer'"
              v-model="addNewUser.role"
              />
              <label for="" class="mr-1">Lecturer</label>
            </div>
            <div class="mx-2">
              <input
              class="mr-1"
              type="radio"
              name="role"
              :value="'admin'"
              v-model="addNewUser.role"
            />
            <label for="" class="mr-1">Admin</label>
          </div>
        </div>
        <div class="grid gap-6">
          <!-- User email -->
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

          <!-- User password -->
          <div class="relative">
            <input
              v-model="addNewUser.password"
              type="password"
              :class="[
                'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
                responeError?.details?.password
                  ? 'border-red-500 focus:border-red-500'
                  : '',
              ]"
              placeholder=" "
            />
            <label
              class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
            >
              Password<span class="text-red-500">*</span>
            </label>
            <p
              v-if="responeError?.details?.password"
              class="absolute text-sm text-red-500 ml-2"
            >
              {{ responeError.details.password }}
            </p>
            <p v-if="isSamePassword" class="absolute text-sm text-red-500 ml-2">
              Password not match
            </p>
          </div>

          <!-- User confirm password -->
          <div class="relative">
            <input
              v-model="addNewUser.confirmPassword"
              type="password"
              :class="[
                'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
                responeError?.details?.confirmPassword
                  ? 'border-red-500 focus:border-red-500'
                  : '',
              ]"
              placeholder=" "
            />
            <label
              class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
            >
              Confirm password<span class="text-red-500">*</span>
            </label>
            <p
              v-if="responeError?.details?.confirmPassword"
              class="absolute text-sm text-red-500 ml-2"
            >
              {{ responeError.details.confirmPassword }}
            </p>
            <p v-if="isSamePassword" class="absolute text-sm text-red-500 ml-2">
              Password not match
            </p>
          </div>
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
          <div class="w-full h-12 mt-12 space-x-6">
            <button
              :class="[
                'w-24 h-full text-white duration-150 bg-emerald-500 disabled:bg-gray-200 disabled:text-gray-400',
              ]"
              @click="postNewUser"
              :disabled="isSamePassword"
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
                Your user is already created.
              </p>
            </div>
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
import { getRefreshToken } from '../services/FetchServices.js';
import { useStoreToken } from '../stores/token';
import { useRouter } from 'vue-router';
import axios from 'axios';
import Swal from 'sweetalert2';
import BaseLoader from '../components/bases/BaseLoader.vue';

onBeforeMount(async () => {
  isUserLoaded.value = true
  await getAllUsers();
  isUserLoaded.value = false
});

const myRouter = useRouter();
const useToken = useStoreToken();
const isUserLoaded = ref(false)
const users = ref([]);
const addNewUser = ref({
  role: 'student',
  userName: '',
  userEmail: '',
  password: '',
  confirmPassword: '',
});
const defaultUser = ref({
  role: 'student',
  userName: '',
  userEmail: '',
  password: '',
  confirmPassword: '',
});
const responeError = ref({});
const defaultResponeError = ref({});
const addDialog = ref(false);
const SUCCESFUL = ref(false);
const ERROR = ref(false);

const addDialogClosed = () => {
  addDialog.value = false;
  addNewUser.value = Object.assign({}, defaultUser.value);
  responeError.value = Object.assign({}, defaultResponeError.value);
};

// Computed
const getUser = computed(() => {
  try {
    return users.value;
  } catch (error) {
    return [];
  }
});

const isSamePassword = computed(() => {
  if (
    addNewUser.value.password !== addNewUser.value.confirmPassword &&
    addNewUser.value.password &&
    addNewUser.value.confirmPassword
  ) {
    return true;
  }
  return false;
});

// Fetch service
// GET METHOD - Get all users
const getAllUsers = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/users`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  });

  if (res.status === 200) {
    return users.value = await res.json();
  }

  if (res.status === 400) {
    await Swal.fire({
      icon: 'error',
      title: 'Sorry, something went wrong please try again.',
    });
    return;
  }

  const status = await getRefreshToken();
  if (status === 401) {
    setTimeout(async () => {
      await myRouter.push('/login');
    }, 500);
    return;
  }

  return getAllUsers();
};

// POST METHOD - Create new user
const postNewUser = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/users`, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
    body: JSON.stringify(addNewUser.value),
  });

  if (res.status === 201) {
    await Swal.fire({
      icon: 'success',
      title: 'User is successfully created ',
    });
    await getAllUsers();
    addDialogClosed();
    return;
  }

  if (res.status === 400) {
    // SUCCESFUL.value = false;
    // ERROR.value = true;
    // setTimeout(function () {
    //   ERROR.value = false;
    // }, 1500);
    return (responeError.value = await res.json());
  }

  const status = await getRefreshToken();
  if (status === 401) {
    setTimeout(async () => {
      await myRouter.push('/login');
    }, 500);
    return;
  }

  return postNewUser();
};
</script>

<style></style>
