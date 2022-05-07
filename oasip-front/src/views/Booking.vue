<template>
    <div class="l-w-full my-24 mx-auto justify-center">
        <h1 class="text-7xl font-bold l-color-navi text-center mb-16">What clinic do you<br>want to book?</h1>
        <div class="l-w-full flex flex-wrap gap-6 justify-center">
            <div class="l-w-400 l-h-624" v-for="(value, index) in clinics" :key="index">
                <div class="l-w-400 h-80">
                    <img :src='value.eventImagePath' alt="">
                </div>
                <div class="grid justify-center mt-6">
                    <h2 class="text-2xl font-bold">{{ value.eventCategoryName }}</h2>
                    <div class="l-w-344 h-40 my-3">
                        <p class="text-base text-gray-400">{{value.eventCategoryDescription}}</p>
                    </div>
                    <router-link :to="{ name: 'BookingForm', params: { id: value.categoryId } }" class="flex space-x-3">
                        <p class="l-color-blue">Go to booking</p>
                        <img src="../assets/component/next.png" alt="" width="24">
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { ref } from '@vue/reactivity';
import { onBeforeMount } from '@vue/runtime-core';
import { getAllCategory } from '../services/FetchServices.js';

onBeforeMount(async () => {
    const res = await getAllCategory()
    clinics.value = res
})

const clinics = ref([])

</script>
 
<style>
</style>