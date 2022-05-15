<template>
    <nav class="grid grid-cols-3 justify-center place-items-center bg-white w-full drop-shadow-sm">

        <!-- Icon -->
        <div class="justify-self-start ml-24">
            <router-link :to="{ name: 'Home' }">
                <div class="flex space-x-3">
                    <img src="../assets/logo.png" alt="" width="" class="">
                    <p class="font-light text-2xl">OASIP</p>
                </div>
            </router-link>
        </div>

        <!-- Menu - main nav -->
        <div class="flex justify-center space-x-12 h-24 place-items-center text-xs font-xs l-text-navi drop-shadow-sm">

            <!-- Dashboard -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Home' ? 'text-blue-400 border-b-2 border-blue-600' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">
                    Dashboard
                </router-link>
            </div>

            <!-- Booking -->


            <!-- Scheduled -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Scheduled' ? 'text-blue-400 border-b-2 border-blue-600' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">
                    Scheduled
                </router-link>
            </div>
        </div>
        <router-link :to="{ name: 'Booking' }" class="flex place-items-center space-x-2 justify-self-end">
            <button
                class="mr-24 w-28 h-10 bg-white font-light grid place-items-center border border-green-600  text-green-600 hover:bg-green-800 hover:border-green-800 hover:text-white hover:border-2 duration-150">
                <p>Book now</p>
            </button>
        </router-link>
    </nav>
</template>
 
<script setup>

</script>
 
<style>
</style>