<template>
    <div>
        <div class="l-w-400 mx-auto mt-24 grid gap-4 justify-center">
            <h1 class="font-medium text-center text-4xl l-color-navi ">All Schedule Event</h1>
            <p class="font-light text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lectus amet a lectus aliquam semper mi sem. Dui
                urna
                dolor ultrices venenatis commodo. Orci pellentesque rhoncus sagittis, odio pretium vitae tristique.
                Ultrices
                non pellentesque ac velit aliquam sed non nisl eget.</p>
        </div>
        <!-- Menu - Filter category -->
        <div class="flex justify-center text-xs">
            <div class="flex justify-center w-8/12 h-6 mt-24 mb-8 space-x-3">
                <button @click="status = ''">
                    <div :class="['grid justify-center place-items-center w-9 h-6 l-color-navi',
                        status == '' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        All
                    </div>
                </button>
                <button @click="status = 'upcoming'">
                    <div :class="['grid justify-center place-items-center w-20 h-6 l-color-navi',
                        status == 'upcoming' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        Upcoming
                    </div>
                </button>

                <div class="h-6 w-0.5 bg-gray-100"> </div>

                <!-- Category -->
                <button @click="addToFilter(4)">
                    <div :class="['grid justify-center place-items-center w-20 h-6 l-color-navi',
                        filterCat.includes(4) ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        Client-Sided
                    </div>
                </button>
                <button @click="addToFilter(5)">
                    <div :class="['grid justify-center place-items-center w-20 h-6 l-color-navi',
                        filterCat.includes(5) ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        Server-Sided
                    </div>
                </button>
                <button @click="addToFilter(3)">
                    <div :class="['grid justify-center place-items-center w-20 h-6 l-color-navi',
                        filterCat.includes(3) ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        Database
                    </div>
                </button>
                <button @click="addToFilter(2)">
                    <div :class="['grid justify-center place-items-center w-20 h-6 l-color-navi',
                        filterCat.includes(2) ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        DevOps
                    </div>
                </button>
                <button @click="addToFilter(1)">
                    <div :class="['grid justify-center place-items-center w-40 h-6 l-color-navi',
                        filterCat.includes(1) ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300'
                    ]">
                        Project Management
                    </div>
                </button>
                <div class="flex">
                    <div class="mt-1">
                        <svg class="absolute text-slate-400 h-4 w-6 ml-1" viewBox="0 0 20 20" fill="currentColor">
                            <path fill-rule="evenodd"
                                d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                                clip-rule="evenodd" />
                        </svg>
                    </div>
                    <input class="w-56 h-6 l-bg-gray px-6 focus:border-blue-400" type="text" placeholder="Search">
                </div>
            </div>
        </div>

        <!-- List - All events -->
        <div class="l-bg-gray">
            <BaseEvent :data="sortByStatus" :status="status" @delete="events($event)" />
        </div>
    </div>
</template>
 
<script setup>
import { ref, onBeforeMount, computed, onUpdated } from 'vue'
import { getAllEvents, deleteEventById } from '../services/FetchServices.js'
import BaseEvent from '../components/BaseEvent.vue';

onBeforeMount(async () => {
    const res = await getAllEvents()
    console.log(res)
    AllEventsData.value = res
})

const AllEventsData = ref([])

// Filter - event category
const filterCat = ref([])

function addToFilter(catId) {
    if (filterCat.value.includes(catId)) {
        filterCat.value.splice(filterCat.value.indexOf(catId), 1)
        console.log(filterCat.value);
    }
    else {
        filterCat.value.push(catId)
        console.log(filterCat.value);
    }
}

const sortByCategory = computed(() => {
    if (filterCat.value.length == 0) {
        return AllEventsData.value
    } else {
        return AllEventsData.value.filter(event => filterCat.value.includes(event.categoryId))
    }
})

const status = ref('')
const sortByStatus = computed(() => {
    if (status.value == '') return sortByCategory.value;
    else return sortByCategory.value.filter((event) => {
        return event.statusName.toLowerCase() == status.value.toLowerCase() || event.statusName.toLowerCase() == 'ongoing'
    })
})


// // Delete - event
// const events = (eventId) => {
//     console.log(eventId);
//     if (eventId.length <= 0) {
//         deleteEventById(eventId)
//     } else {
//         for (let i = 0; i < eventId.length; i++) {
//             AllEventsData.value = AllEventsData.value.filter(e => {
//                 return e.id != eventId[i]
//             })
//             deleteEventById(eventId[i])
//         }
//     }
// }

</script>
 
<style>
</style>