<template>
    <div>
        <div class="l-w-400 mx-auto mt-24 grid gap-4 justify-center">
            <h1 class="font-medium text-center text-4xl l-color-navi ">All Schedule Event</h1>
            <p class="font-light text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lectus amet a lectus
                aliquam semper mi sem. Dui
                urna
                dolor ultrices venenatis commodo. Orci pellentesque rhoncus sagittis, odio pretium vitae tristique.
                Ultrices
                non pellentesque ac velit aliquam sed non nisl eget.</p>
        </div>

        <!-- Menu - Filter category -->
        <!-- <div class="flex justify-center">
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

                <div class="h-6 w-0.5 bg-gray-100"> </div> -->

        <!-- Category -->
        <!-- <button @click="addToFilter(4)">
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
        </div> -->


        <!-- Menu - Filter category -->
        <div class="h-20 bg-white mt-24 mx-auto sticky top-0 z-50 grid place-items-center">
            <div class="l-w-full h-6 grid grid-cols-3 gap-6 mx-auto place-items-center">

                <div class="l-w-188 h-6">
                    <select
                        class="l-w-188 border border-gray-300 text-sm l-color-gray-300 pl-2 py-1" v-model="filter">
                        <option disabled selected>Category</option>
                        <option value="0">None</option>
                        <option v-for="value, index in allEventCategory" :key="index" :value="value.categoryId">{{value.eventCategoryName}}</option>
                    </select>
                </div>

                <div class="flex gap-6">
                    <button @click="status = ''">
                        <div :class="['grid justify-center place-items-center w-24 h-6 text-sm font-light l-color-navi',
                        status == '' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300']">
                            All</div>
                    </button>
                    <button @click="status = 'upcoming'">
                        <div :class="['grid justify-center place-items-center w-24 h-6 text-sm font-light l-color-navi', 
                        status == 'upcoming' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300']">
                            Upcoming</div>
                    </button>
                    <button @click="status= 'ongoing'">
                        <div :class="['grid justify-center place-items-center w-24 h-6 text-sm font-light l-color-navi',
                        status == 'ongoing' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300']">
                            On going</div>
                    </button>
                    <button @click="status = 'completed'">
                        <div :class="['grid justify-center place-items-center w-24 h-6 text-sm font-light l-color-navi',
                        status == 'completed' ? 'l-bg-blue text-white font-semibold' : 'hover:bg-gray-300']">
                            Completed</div>
                    </button>
                </div>

                <div class="l-w-188 h-6">
                    <input type="date" class="pl-2 py-1 l-w-188 border l-color-gray-300 text-sm l-color-gray-300" />
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
import { getAllEvents, deleteEventById, getAllCategory } from '../services/FetchServices.js'
import BaseEvent from '../components/BaseEvent.vue';

onBeforeMount(async () => {
    const res = await getAllEvents()
    const temp = await getAllCategory()
    console.log(res)
    AllEventsData.value = res
    allEventCategory.value = temp
})

const AllEventsData = ref([])
const allEventCategory = ref([])

// Filter - event category

const filter = ref('Category')

const sortByCategory = computed(() => {
    if (filter.value == 0 || filter.value == 'Category') {
        return AllEventsData.value
    } else {
        return AllEventsData.value.filter(event => event.categoryId == filter.value)
    }
})

const status = ref('')
const sortByStatus = computed(() => {
    if (status.value == '') return sortByCategory.value;
    else if (status.value == 'upcoming') {
        return sortByCategory.value.filter(event => {
            return event.statusName.toLowerCase() == 'upcoming' || event.statusName.toLowerCase() == 'ongoing'
        })
    }
    else if (status.value == 'ongoing') {
        return sortByCategory.value.filter(event => {
            return event.statusName.toLowerCase() == 'ongoing'
        })
    }
    else {
        return sortByCategory.value.filter(event => {
            return event.statusName.toLowerCase() == 'completed'
        })
    }
    
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