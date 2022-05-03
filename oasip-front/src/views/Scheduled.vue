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
                <div class="flex">
                    <div class="mt-1">
                        <svg class="absolute text-slate-400 h-4 w-4 ml-1" viewBox="0 0 20 20" fill="currentColor">
                            <path fill-rule="evenodd"
                                d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                                clip-rule="evenodd" />
                        </svg>
                    </div>
                    <input class="w-48 h-6 rounded-md l-bg-gray px-6 border border-blue-400" type="text"
                        placeholder="Search">
                </div>
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
    console.log(res)
    AllEventsData.value = res
    // sortTime()
    // sortDate()
})

const AllEventsData = ref([])

// const sortTime = () => {
//      return AllEventsData.value.sort(function (a, b) {
//         if (b.eventStartTime < a.eventStartTime) {
//             return -1
//         }
//         if (b.eventStartTime > a.eventStartTime) {
//             return 1
//         }
//         return 0
//     })
// }

// const sortDate = () => {
//     return AllEventsData.value.sort(function (a, b) {
//         return new Date(a.eventDate) - new Date(b.eventDate);
//     })
// }


</script>
 
<style>
</style>