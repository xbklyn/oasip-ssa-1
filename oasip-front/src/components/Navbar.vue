<template>
    <nav class="flex justify-center space-x-12 h-24 place-items-center bg-white text-xs font-xs l-text-navi drop-shadow-sm">
        <div
            :class="['grid place-items-center l-color-blue h-24 w-24', 
            $route.name == 'Home' ? 'text-blue-400' : '']">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">Dashboard</router-link>
        </div>
        <div :class="['grid place-items-center l-color-blue h-24 w-24', 
        $route.name == 'Booking' ? 'text-blue-400' : '']">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Booking' }">Booking</router-link>
        </div>
        <div :class="['grid place-items-center l-color-blue h-24 w-24', 
        $route.name == 'Scheduled' ? 'text-blue-400' : '']">
            <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">Schduled</router-link>
        </div>
    </nav>
</template>
 
<script setup>

</script>
 
<style>
</style>