<template>
    <nav class="grid grid-cols-3 justify-center place-items-center bg-white w-full drop-shadow-sm">

        <!-- Icon -->
        <div class="justify-self-start ml-24">
            <router-link :to="{ name: 'Home' }">
                <div class="flex space-x-3">
                    <img src="../assets/logo.png" alt="" width="" class="">
                    <p class="font-black text-2xl">OASIP</p>
                </div>
            </router-link>
        </div>

        <!-- Menu - main nav -->
        <div class="flex justify-center space-x-12 h-24 place-items-center text-xs font-xs l-text-navi drop-shadow-sm">

            <!-- Dashboard -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Home' ? 'text-blue-400' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">
                    Dashboard
                </router-link>
            </div>

            <!-- Booking -->
            

            <!-- Scheduled -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Scheduled' ? 'text-blue-400' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">
                    Scheduled
                </router-link>
            </div>
        </div>
        
        <button class="justify-self-end mr-24 w-32 h-10 text-white bg-green-400 font-bold grid place-items-center l-color-navi hover:bg-green-600 duration-150">
                <router-link :to="{ name: 'Booking' }" class="flex place-items-center space-x-2">
                  <span class="text-xl font-black">+</span><p>book now</p>
                </router-link>
            </button>
    </nav>
</template>
 
<script setup>

</script>
 
<style>
</style>