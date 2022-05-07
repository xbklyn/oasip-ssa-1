<template>
    <div>
        <div class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-49 w-screen h-screen"
            @click="goBack">
        </div>
        <div class="bg-black/80 fixed top-72 right-0 left-0 py-16 z-50">
            <EventDetail :data="eventInfoById" @exit="goBack" />
        </div>
    </div>
</template>
 
<script setup>
import { ref } from '@vue/reactivity';
import { onBeforeMount } from '@vue/runtime-core';
import EventDetail from '../components/EventDetail.vue';
import { getEventById } from '../services/FetchServices.js';
import { useRoute, useRouter } from 'vue-router'

const { params } = useRoute()

onBeforeMount(async () => {
    console.log(params.id);
    const res = await getEventById(params.id)
    console.log(res)
    eventInfoById.value = res
})

const eventInfoById = ref([])

const myRouter = useRouter()
const goBack = () => myRouter.forward({
    name: 'Scheduled'
})  
</script>
 
<style>
</style>