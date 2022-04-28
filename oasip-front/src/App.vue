<template>
  <div>
    <div>
      <Navbar />
    </div>
    <div>
      <router-view></router-view>
    </div>
    <div>
      <Footer />
    </div>
  </div>
</template>
 
<script setup>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
</script>
 
<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

body {
  font-family: 'Poppins', sans-serif;
  background-color: #F9FAFB;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #23374D;
  overscroll-behavior: auto;
}

.l-color-navi {
  color: #23374D;
}

.l-border-b-blue {
  border-bottom: 4px solid #1089FF;
}

.l-bg-blue {
  background-color: #1089FF;
}

.l-w-408 {
  width: 408px;
}

.l-bg-gray{
  background-color: #F9FAFB;
}
</style>