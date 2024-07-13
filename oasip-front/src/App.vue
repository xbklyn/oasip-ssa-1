<template>
  <div class="scroll-smooth">
    <div>
      <Navbar
        v-show="
          $route.name == 'NotFound' || $route.name == 'Login' ? false : true
        "
      />
    </div>
    <div class="mx-auto">
      <router-view></router-view>
    </div>
    <div>
      <Footer
        v-show="
          $route.name == 'NotFound' || $route.name == 'Login' ? false : true
        "
      />
    </div>
  </div>
</template>

<script setup>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

import { useRoute, useRouter } from 'vue-router';
import { onMounted } from '@vue/runtime-core';
import { useStoreToken } from './stores/token';

const myRouter = useRouter();
const tokenStore = useStoreToken()
const { setAccessToken, setUserRole, setUserEmail } = tokenStore


onMounted(()=>{
  setAccessToken(localStorage.getItem('access_token'))
  setUserRole(localStorage.getItem('userRole'))
  setUserEmail(localStorage.getItem('userEmail'))
})

myRouter.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  next();
});
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Prompt:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

body {
  font-family: 'Poppins', 'Prompt';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #23374d;
  overscroll-behavior: auto;
}

nav {
  font-family: 'Poppins', 'Prompt';
}

.l-color-navi {
  color: #23374d;
}

.l-color-gray {
  color: #d4daef;
}

.l-color-gray-300 {
  color: #8d8d8d;
}

.l-color-blue {
  color: #1089ff;
}

.l-border-b-blue {
  border-bottom: 4px solid #1089ff;
}

.l-bg-blue {
  background-color: #1089ff;
}

.l-bg-gray-100 {
  background-color: #f5f6f7;
}

.l-bg-navi {
  background-color: #23374d;
}

.l-w-188 {
  width: 188px;
}

.l-w-294 {
  width: 294px;
}

.l-w-366 {
  width: 366px;
}

.l-w-400 {
  width: 400px;
}

.l-w-520 {
  width: 520px;
}

.l-w-612 {
  width: 612px;
}

.l-w-624 {
  width: 624px;
}

.l-w-720 {
  width: 720px;
}

.l-w-824 {
  width: 824px;
}

.l-w-840 {
  width: 840px;
}

.l-w-960 {
  width: 960px;
}

.l-bg-gray {
  background-color: #f9fafb;
}

.l-w-10-12 {
  width: 1036px;
}

.l-w-1060 {
  width: 1060px;
}

.l-w-full {
  width: 1248px;
}

.l-h-188 {
  height: 188px;
}

.l-h-294 {
  height: 294px;
}

.l-h-520 {
  height: 520px;
}

.l-h-624 {
  height: 624px;
}

.l-h-824 {
  height: 824px;
}

.l-h-1248 {
  width: 1248px;
}

.l-text-xxs {
  font-size: 0.5rem;
}

.alert {
  opacity: 0;
  animation: fade 0.5s forwards ease-in-out;
}

@keyframes fade {
  from {
    opacity: 0;
  }

  to {
    opacity: 100;
  }
}
</style>
