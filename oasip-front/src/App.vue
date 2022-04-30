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
import CatagoryFilter from './components/CatagoryFilter.vue';
</script>
 
<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nunito+Sans:ital,wght@0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

body {
  font-family: 'Poppins', 'Kanit';
  /* font-family: 'Nunito Sans', sans-serif; */
  /* font-family: 'Nunito', sans-serif; */
  /* font-family: 'Kanit', sans-serif; */
  background-color: #F9FAFB;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #23374D;
  overscroll-behavior: auto;
}

nav {
  font-family: 'Nunito', 'Kanit';
  /* font-family: 'Nunito Sans', sans-serif; */
}

.l-color-navi {
  color: #23374D;
}

.l-color-gray {
  color: #D4DAEF;
}

.l-border-b-blue {
  border-bottom: 4px solid #1089FF;
}

.l-bg-blue {
  background-color: #1089FF;
}

.l-bg-navi {
  background-color: #23374D;
}

.l-w-408 {
  width: 408px;
}

.l-w-520 {
  width: 520px;
}

.l-w-624 {
  width: 624px;
}

.l-w-720 {
  width: 720px;
}

.l-w-840 {
  width: 840px;
}

.l-bg-gray {
  background-color: #F9FAFB;
}

.l-w-10-12 {
  width: 1056px
}

.l-w-full {
  width: 1272px;
}
</style>