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
                <button :disabled="eventInfoById.statusId == 1 || eventInfoById.statusId == 2"
                    @click="modifyMode = true"
                    class="w-20 h-8 border border-blue-600 text-blue-600 hover:bg-blue-700 hover:text-white hover:border duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400">Edit</button>
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
        <EventDetailEdit :data="eventInfoById" @edit="editEvent($event)" :duration="eventInfoById.eventDuration"
            :MAX="480" :BREAK="5" :SUCCESFUL="SUCCESFUL" :ERROR="ERROR" v-else />


        <!-- Modal box - Confirmation -->
        <div v-show="show"
            class="grid place-items-center fixed top-0 right-0 left-0 z-50 bg-black/80 w-screen h-screen">
            <div class="bg-white l-w-960 l-h-520 flex">
                <div>
                    <img src="../assets/component/confirmation-2.png" alt="">
                </div>
                <div class="l-w-520 l-h-520 grid place-items-center">
                    <div class="grid justify-center">
                        <img src="../assets/component/warning.png" alt="">
                    </div>
                    <div class="text-center space-y-3">
                        <h2 class="font-medium text-2xl">
                            Are you sure delete this event?
                        </h2>
                        <p class="l-w-400 font-light text-xs l-color-gray-300">If you delete this event, it will be gone
                            forever.</p>
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
            <div v-if="SUCCESFUL"
                class="alert bg-black/40 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 w-full h-full">
                <div class="relative p-4 w-full h-full grid place-items-center justify-center">
                    <div class="relative bg-white shadow l-w-520 h-72 grid place-items-center">
                        <div class="grid place-items-center gap-6">
                            <div class="grid justify-center">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                    aria-hidden="true" role="img" class="iconify iconify--clarity text-emerald-500"
                                    width="96" height="96" preserveAspectRatio="xMidYMid meet" viewBox="0 0 36 36">
                                    <path fill="currentColor"
                                        d="M18 2a16 16 0 1 0 16 16A16 16 0 0 0 18 2Zm10.45 10.63L15.31 25.76L7.55 18a1.4 1.4 0 0 1 2-2l5.78 5.78l11.14-11.13a1.4 1.4 0 1 1 2 2Z"
                                        class="clr-i-solid clr-i-solid-path-1"></path>
                                    <path fill="none" d="M0 0h36v36H0z"></path>
                                </svg>
                            </div>
                            <div class="text-center">
                                <h2 class="text-xl font-semibold text-emerald-700 mb-2">DONE!</h2>
                                <p class="text-md l-color-gray-300">your scheduled is already deleted.</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div v-if="ERROR"
                class="alert bg-black/40 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 w-full h-full">
                <div class="relative p-4 w-full h-full grid place-items-center justify-center">
                    <div class="relative bg-white shadow l-w-520 h-72 grid place-items-center">
                        <div class="grid place-items-center gap-6">
                            <div class="grid justify-center">
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                    aria-hidden="true" role="img" class="iconify iconify--material-symbols text-orange-400" width="96"
                                    height="96" preserveAspectRatio="xMidYMid meet" viewBox="0 0 24 24">
                                    <path fill="currentColor"
                                        d="M12 22q-2.075 0-3.9-.788q-1.825-.787-3.175-2.137q-1.35-1.35-2.137-3.175Q2 14.075 2 12t.788-3.9q.787-1.825 2.137-3.175q1.35-1.35 3.175-2.138Q9.925 2 12 2t3.9.787q1.825.788 3.175 2.138q1.35 1.35 2.137 3.175Q22 9.925 22 12t-.788 3.9q-.787 1.825-2.137 3.175q-1.35 1.35-3.175 2.137Q14.075 22 12 22Zm-1-9h2V7h-2Zm1 4q.425 0 .713-.288Q13 16.425 13 16t-.287-.713Q12.425 15 12 15t-.712.287Q11 15.575 11 16t.288.712Q11.575 17 12 17Z">
                                    </path>
                                </svg>
                            </div>
                            <div class="text-center">
                                <h2 class="text-xl font-semibold text-orange-400 mb-2">WARNING!</h2>
                                <p class="text-md l-color-gray-300">Somthing want wrong, Please try again.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { computed, ref } from '@vue/reactivity';
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
const deleteEvent = async (eventId) => {
    let status = await deleteEventById(eventId)

    if (status == 500 || status == 400) {
        ERROR.value = true
        setTimeout(function () {
ERROR.value = false
        }, 2000);
    }
    // alert("Something is wrong, please try again.")
    else {
        SUCCESFUL.value = true
        ERROR.value = false
        setTimeout(function () {
            location.reload(1)
            myRouter.go(-1)
        }, 2000);
    }
    // alert("Delete succesfully.")
    // show.value = false
    // location.reload()
    // myRouter.go(-1)
}

// Edit - Event
const SUCCESFUL = ref(false)
const ERROR = ref(false)
const editEvent = async (event) => {
    let status = await editEventById(event)
    if (status == 500 || status == 400) {
        ERROR.value = true
        SUCCESFUL.value = false
    }
    // alert("Something is wrong, please try again.")
    else {
        SUCCESFUL.value = true
        ERROR.value = false
        setTimeout(function () {
            myRouter.go({
                path: `scheduled/scheduled-info/${params.id}`
            })
        }, 2000);
        // modifyMode.value = false
    }
    // alert("Edit succesfully.")
    // location.reload()
    // myRouter.go({
    //     path: `scheduled/scheduled-info/${params.id}`
    // })

}



</script>
 
<style>
</style>