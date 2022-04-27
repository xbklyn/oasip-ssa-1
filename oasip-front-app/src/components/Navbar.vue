<template>
    <nav class="flex justify-center space-x-12 h-24 place-items-center bg-white text-xs font-xs l-text-navi">
        <div
            :class="['grid place-items-center l-color-blue h-24 w-24', this.$route.name == 'Home' ? 'text-blue-400' : '']">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">Dashboard
            </router-link>
            <!-- <span v-show="(this.$route.name == 'Home')" class="absolute top-20 bg-blue-500 w-24 h-4 rounded-tl-md rounded-tr-md"></span> -->
        </div>
        <div class="grid place-items-center l-color-blue h-24 w-24">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Booking' }">Booking</router-link>
        </div>
        <div class="grid place-items-center l-color-blue h-24 w-24">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">Schduled</router-link>
        </div>
    </nav>
</template>
 
<script setup>

</script>
 
<style>
</style>