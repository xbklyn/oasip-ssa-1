<template>
    <div class="mx-24">
        <div class="grid grid-cols-3 place-items-center">
            <button @click="goBack" class="">
                <div class="w-12 flex place-items-center">
                    <img src="../assets/back.png" alt="" width="36">
                    <span class="text-lg font-semibold">Back</span>
                </div>
            </button>
            <h1 class="text-4xl font-bold mt-24 mb-8 text-center inline grid justity-center">EventDetail</h1>
        </div>
        <EventDetail :data="eventInfoById" />
    </div>
</template>
 
<script setup>
import { ref } from '@vue/reactivity';
import { onBeforeMount } from '@vue/runtime-core';
import EventDetail from '../components/EventDetail.vue';
import { getEventById } from '../services/FetchServices.js';
import { useRoute, useRouter } from 'vue-router'

const { params } = useRoute()
const eventInfoById = ref([])

onBeforeMount(async () => {
    const res = await getEventById(params.id)
    console.log(res)
    eventInfoById.value = res
})


const myRouter = useRouter()
const goBack = () => myRouter.go(-1)
</script>
 
<style>
</style>