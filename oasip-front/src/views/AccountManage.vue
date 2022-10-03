<template>
  <div class="l-w-824 mx-auto mt-12 scroll-smooth" id="top">
    <!-- Menu - Breadcrumbs -->
    <div class="l-w-824 mx-auto mb-4">
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
    <div class="l-w-400 grid gap-4">
      <h1 class="font-bold text-4xl l-color-navi">Account</h1>
    </div>
    <div class="grid grid-cols-1 mt-10 gap-16">
      <div class="flex justify-items-center items-center">
        <img
          class="h-36 w-36 rounded-full"
          src="../assets/profile.png"
          alt=""
        />
        <div class="ml-8">
          <h1 class="text-3xl font-semibold">OASIP ADMIN</h1>
          <h2 class="text-xl font-light">Admin</h2>
        </div>
      </div>
      <div class="grid grid-cols-1 gap-8">
        <div>
          <p class="text-base">Name</p>
          <input
            type="text"
            class="w-full h-12 pl-2 text-sm bg-transparent border-b-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
        </div>
        <div>
          <p class="text-base">Email</p>
          <input
            type="text"
            class="w-full h-12 pl-2 text-sm bg-transparent border-b-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
        </div>
        <!-- <div class="flex gap-6">
          <button
            class="my-12 bg-emerald-400 text-white l-w-188 h-12 duration-150 hover:bg-emerald-600"
          >
            Update
          </button>
        </div> -->
      </div>
    </div>
  </div>
</template>
<script setup></script>

<style></style>
