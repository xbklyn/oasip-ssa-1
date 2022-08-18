<template>
  <div class="l-w-full l-h-824 my-6 mx-auto flex border">
    <div
      class="l-w-188 l-h-824 justify-self-start l-bg-gray overflow-y-auto border-r"
    >
      <!-- Navigation bar - Left side -->
      <div class="w-40 grid my-12 justify-center gap-3 duration-150">
        <h2 class="mb-3 text-slate-200 font-medium">Manage</h2>
        <router-link
          :to="{ name: 'clinic-manage' }"
          class="font-normal c flex gap-2 hover:text-blue-300 duration-150"
        >
          <div
            v-show="$route.name == 'clinic-manage'"
            class="w-1 h-full bg-blue-500"
          ></div>
          <p :class="[$route.name == 'clinic-manage' ? 'font-semibold' : '']">
            Clnics
          </p>
        </router-link>
        <router-link
          :to="{ name: 'users-manage' }"
          class="font-normal c flex gap-2 hover:text-blue-300 duration-150"
        >
          <div
            v-show="$route.name == 'users-manage'"
            class="w-1 h-full bg-blue-500"
          ></div>
          <p :class="[$route.name == 'users-manage' ? 'font-semibold' : '']">
            Users
          </p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>In-schedule</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Navigation</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Theme</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Help</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Enviroment</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Brand</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>Official</p>
        </router-link>
        <router-link
          :to="{ name: '' }"
          class="l-color-navi flex gap-2 hover:text-blue-300 duration-150"
        >
          <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
          <p>XOXO</p>
        </router-link>
      </div>
    </div>

    <!-- Page - Editing -->
    <div class="l-w-1060 l-h-824 pt-12 overflow-auto scroll-smooth">
      <div class="grid justify-center">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style></style>
