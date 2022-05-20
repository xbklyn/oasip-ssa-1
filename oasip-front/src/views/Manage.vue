<template>
        <div class="l-w-full l-h-824 my-6 mx-auto flex border">
            <div class="l-w-188 l-h-824 justify-self-start bg-gradient-to-b to-rose-700 from-sky-700 overflow-y-auto border-r">
                <div class="w-32 grid my-12 justify-center gap-3">
                    <h2 class="mb-3 text-slate-200 font-medium">Manage</h2>
                    <router-link :to="{ name: 'clinic-manage' }" class="font-normal text-white flex gap-2 hover:text-blue-300 duration-150">
                        <div v-show="$route.name == 'clinic-manage'" class="w-1 h-full bg-white"></div>
                        <p :class="[$route.name == 'clinic-manage' ? 'font-semibold':'']">Clnics</p>
                    </router-link>
                    <router-link :to="{ name: '' }" class="text-white flex gap-2 hover:text-blue-300 duration-150">
                        <div v-show="$route.name == ''" class="w-px h-full bg-white"></div>
                        <p>Theme</p>
                    </router-link>
                </div>
            </div>
            <div class="l-w-1060 l-h-824 pt-12 overflow-auto scroll-smooth">
                <div class="grid justify-center">
                    <router-view></router-view>
                </div>
            </div>
        </div>

</template>
 
<script setup>
import { ref } from "@vue/reactivity";



</script>
 
<style>
</style>