<template>
    <div>

        <!-- Menu - Breadcrumbs -->
        <div class="l-w-10-12 mx-auto mt-12 mb-4">
            <ol class="inline-flex items-center space-x-1">
                <li class="flex items-center">
                    <router-link :to="{ name: 'Scheduled' }"
                        class="inline-flex items-center text-sm font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4">
                        <svg class="w-5 h-5 mr-1" viewBox="0 0 512 512">
                            <path
                                d="M427 234.625H167.296l119.702-119.702L256 85 85 256l171 171 29.922-29.924-118.626-119.701H427v-42.75z"
                                fill="currentColor"></path>
                        </svg>
                        Scheduled
                    </router-link>
                </li>
                <li class="flex items-center">
                    <div class="flex items-center">
                        <svg class="w-6 h-6 text-gray-400" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                clip-rule="evenodd"></path>
                        </svg>
                        <span @click="modifyMode = !modifyMode"
                            :class="[modifyMode ? ' text-sm ml-1 font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4 cursor-pointer' : 'ml-1 text-sm font-medium text-gray-400']">
                            Scheduled
                            Infomation</span>
                    </div>
                </li>
                <li class="flex items-center" v-show="modifyMode">
                    <div class="flex items-center">
                        <svg class="w-6 h-6 text-gray-400" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                clip-rule="evenodd"></path>
                        </svg>
                        <span class="ml-1 text-sm font-medium text-gray-400">Modify Scheduled Information</span>
                    </div>
                </li>
            </ol>
        </div>

        <!-- Header -->
        <div class="l-w-10-12 mx-auto mb-8 grid grid-cols-2" v-if="(!modifyMode)">
            <div>
                <h1 class="font-semibold text-4xl l-color-navi ">Scheduled Infomation</h1>
            </div>
            <div class="justify-self-end space-x-6 text-sm place-items-center">
                <button :disabled="eventInfoById.statusId == 1" @click="modifyMode = true"
                    class="w-20 h-8 border border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400">Edit</button>
                <button @click="show = true"
                    class="w-20 h-8 bg-red-500 text-white font-medium hover:bg-red-700 hover:text-white duration-150">Delete</button>
            </div>
        </div>

        <!-- Header - Modify mode -->
        <div class="l-w-10-12 mx-auto mb-8 grid grid-cols-2" v-else>
            <div>
                <h1 class="font-semibold text-4xl l-color-navi ">Modify Scheduled Information</h1>
            </div>
            <div class="justify-self-end space-x-6 text-sm place-items-center">
                <button @click="modifyMode = false"
                    class="w-20 h-8 border border-red-500 text-red-500 hover:bg-red-500 hover:text-white duration-150">Close</button>
            </div>
        </div>

        <!-- Data - Scheduled Detail -->
        <EventDetail :data="eventInfoById" v-if="(!modifyMode)" />

        <!-- Data - Scheduled Detail Editing mode -->
        <EventDetailEdit :data="eventInfoById" @edit="editEvent($event)" :duration="eventInfoById.eventCategoryDuration" :MAX="480" :BREAK="5" v-else />


        <!-- Modal box - Confirmation -->
        <div v-show="show"
            class="grid place-items-center fixed top-0 right-0 left-0 z-50 bg-black/80 w-screen h-screen">
            <div class="bg-white l-w-960 l-h-520 flex">
                <div>
                    <img src="../assets/component/confirmation.png" alt="">
                </div>
                <div class="l-w-520 l-h-520 grid place-items-center">
                    <div class="grid justify-center">
                        <img src="../assets/component/warning.png" alt="">
                    </div>
                    <div class="text-center space-y-3">
                        <h2 class="font-medium text-2xl">
                            Are you sure delete this event?
                        </h2>
                        <p class="l-w-400 font-light text-xs l-color-gray-300">Lorem ipsum dolor sit amet, consectetur
                            adipiscing elit. Lectus amet a lectus aliquam semper mi sem.</p>
                    </div>
                    <div class="grid space-y-3">
                        <button
                            class="w-80 h-12 bg-white text-green-700 duration-150 border border-green-700 hover:bg-green-700 hover:text-white"
                            @click="deleteEvent(params.id)">Confirm</button>
                        <button class="w-80 h-12 bg-red-600 text-white hover:bg-red-800 duration-150"
                            @click="show = false">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { computed ,ref } from '@vue/reactivity';
import { onBeforeMount, onBeforeUpdate } from '@vue/runtime-core';
import EventDetail from '../components/EventDetail.vue';
import EventDetailEdit from '../components/EventDetailEdit.vue'
import { getEventById, deleteEventById, editEventById, getEventByCatAndDate } from '../services/FetchServices.js';
import { useRoute, useRouter } from 'vue-router'
const { params } = useRoute()



// @@@@@@ HOOK @@@@@@
onBeforeMount(async () => {
    const res = await getEventById(params.id)
    eventInfoById.value = res
    // const temp = await getEventCategoryById(eventInfoById.value.categoryId)
    // CATEGORY.value = temp
})

// @@@@@@ ATTIBUTE @@@@@@
const eventInfoById = ref([])
const CATEGORY = ref({})
const show = ref(false)
const modifyMode = ref(false)
const myRouter = useRouter()





// @@@@@@ FUNCTION @@@@@@
// Delete - Event
const deleteEvent = (eventId) => {
    deleteEventById(eventId)
    show.value = false
    location.reload()
    myRouter.go(-1)
    
}

// Edit - Event
const editEvent = (obj) => {
    editEventById(obj)
    location.reload()
    myRouter.go({
        path: `scheduled/scheduled-info/${params.id}`
    })
    modifyMode.value = false
}



</script>
 
<style>
</style>