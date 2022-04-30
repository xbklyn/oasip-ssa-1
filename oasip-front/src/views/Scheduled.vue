<template>
    <div>
        <h1 class="font-bold text-center text-4xl l-color-navi mt-24">All Schedule</h1>
        <div class="flex justify-center text-xs">
            <div class="flex justify-center w-8/12 h-6 my-24 space-x-3">
                <div class="grid justify-center place-items-center w-9 h-6 l-color-navi rounded-md">
                    <button>All</button>
                </div>
                <div class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                    <button>Upcoming</button>
                </div>
                <div class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                    <button>Front-end</button>
                </div>
                <div class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                    <button>Back-end</button>
                </div>
                <div class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                    <button>Database</button>
                </div>
                <div class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                    <button>DevOps</button>
                </div>
                <div class="grid justify-center place-items-center w-36 h-6 l-color-navi rounded-md hover:">
                    <button>Product Management</button>
                </div> 
                <input class="w-48 h-6 rounded-md l-bg-gray px-4 border border-blue-400" type="text" placeholder="Search">
            </div>
        </div>

        <div class="mx-24">
            <BaseEvent :data="AllEventsData" />
        </div>
    </div>
</template>
 
<script setup>
import { ref, onBeforeMount, computed } from 'vue'
import BaseEvent from '../components/BaseEvent.vue';
import { getAllEvents } from '../services/FetchServices.js'

onBeforeMount(async () => {
    const res = await getAllEvents()
    console.log(JSON.stringify(res))
    // AllEventsData.value = res
})

const AllEventsData = ref([])

</script>
 
<style>
</style>