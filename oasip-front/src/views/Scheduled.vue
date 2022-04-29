<template>
    <div class="">
        <div class="py-24">
            <div class="">
                <h1 class="font-bold text-center text-4xl l-color-navi ">All Schedule</h1>
                <div class="flex justify-center text-xs">
                    <div class="flex justify-center w-8/12 h-6 m-24 space-x-3 ">
                        <div class="grid justify-center w-9 h-6 l-color-navi rounded-md hover:">
                            <button>All</button>
                        </div>
                        <div class="grid justify-center w-20 h-6 l-color-navi rounded-md">
                            <button>Upcoming</button>
                        </div>
                        <div class="grid justify-center w-20 h-6 l-color-navi rounded-md">
                            <button>Front-end</button>
                        </div>
                        <div class="grid justify-center w-20 h-6 l-color-navi rounded-md">
                            <button>Back-end</button>
                        </div>
                        <div class="grid justify-center w-20 h-6 l-color-navi rounded-md">
                            <button>DevOps</button>
                        </div>
                        <div class="grid justify-center w-20 h-6 l-color-navi rounded-md">
                            <button>Database</button>
                        </div>
                        <div class="grid justify-center w-36 h-6 l-color-navi rounded-md">
                            <button>Project Management</button>
                        </div>
                        <input class="w-48 h-6 rounded-md l-bg-gray px-4 border border-blue-400" type="text" placeholder="Search">
                    </div>
                </div>
            </div>
            <div class="mx-24">
                <BaseEvent :data="AllEventsData" />
            </div>
        </div>
    </div>


</template>
 
<script setup>
import { ref ,onBeforeMount} from 'vue'
import BaseEvent from '../components/BaseEvent.vue';
import { getAllEvents } from '../services/FetchServices.js'




const AllEventsData = ref([])

// const getMySchedule = async() => {
//     const res = await fetch('http://oasipdb/api/events')
//     if (res.status === 200) {
//         myEventData.value = await res.json();
//     } else {
//         console.log('err');
//     }
// }


onBeforeMount(async () => {
    const res =  await getAllEvents()
    console.log(res)
    AllEventsData.value = res
})
</script>
 
<style>
</style>