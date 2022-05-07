<template>
    <nav class="grid grid-cols-3 justify-center place-items-center bg-white w-full drop-shadow-sm">
        <div class="justify-self-start ml-24">
            <router-link :to="{ name: 'Home' }">
                <div class="flex space-x-3">
                    <img src="../assets/logo.png" alt="" width="" class="">
                    <p class="font-black text-2xl">OASIP</p>
                </div>
            </router-link>
        </div>
        <div class="flex justify-center space-x-12 h-24 place-items-center text-xs font-xs l-text-navi drop-shadow-sm">
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Home' ? 'text-blue-400' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">
                    Dashboard
                </router-link>
            </div>
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Booking' ? 'text-blue-400' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Booking' }">
                    Booking
                </router-link>
            </div>
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Scheduled' ? 'text-blue-400' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">
                    Scheduled
                </router-link>
            </div>
        </div>
    </nav>
</template>
 
<script setup>

</script>
 
<style>
</style>