<template>
    <div class="grid justify-center" v-if="(data.length > 0)">
        <div class="l-w-1248 l-h-1176 pb-24 grid grid-cols-2 gap-6">

            <!-- Main - Events List -->
            <div class="l-w-612 h-44 flex" v-for="value, index in data" :key="index">
                <div class="l-w-106 h-44 grid place-items-center">
                    <input type="checkbox" width="36" class="ml-10" v-model="eventId" :value='value.id'>
                </div>
                <router-link :to="{ name: 'event-info', params: { id: value.id } }">
                    <div
                        class="l-w-506 h-44 l-bg-gray flex place-items-center hover:drop-shadow-xl hover:scale-105 duration-150">
                        <div class="w-12 h-44 l-bg-navi"></div>
                        <div class="l-w-408 h-28 ml-6">
                            <div class="grid grid-cols-3">
                                <div class="col-span-2">
                                    <p class="text-sm">{{ value.categoryName }}</p>
                                </div>
                                <div class="place-self-end">
                                    <p class="text-xs grid justify-end mt-1">{{ value.eventDuration }} Min.</p>
                                </div>
                                <div class="col-span-3 mt-1">
                                    <h2 class="text-xl font-bold">{{ value.bookingName }}</h2>
                                </div>
                            </div>
                            <div class="col-span-2 mt-1">
                                <p class="text-xs">
                                    On {{ new Date(value.eventDate).toLocaleDateString() }} at {{value.eventStartTime}}</p>
                            </div>
                            <div class="col-span-3 text-xs mt-4">
                                <p class="l-color-gray-300"><span class="font-bold">Note: </span>{{ value.eventNotes ==
                                        '' ? 'None' : value.eventNotes
                                }}
                                </p>
                            </div>
                            <div class="l-color-blue text-xs text-right">
                                <p>Click for more detail</p>
                            </div>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>

        <!-- Menu - Delete events -->
        <div v-show="eventId.length > 0"
            class="grid grid-cols-2 place-items-center w-screen h-16 bg-white drop-shadow-2xl overflow-y-auto overflow-x-hidden fixed bottom-0 right-0 left-0 z-50">
            <div class="justify-self-start ml-24">
                <button class="l-color-blue" @click="allEventId">Select All</button>
            </div>
            <div class="justify-self-end space-x-12 mr-24 ">
                <button class="text-red-700 " @click="show = true">Delete ({{ eventId.length
                }})</button>
                <button class="l-color-blue" @click="eventId = []">Cancel</button>
            </div>
        </div>

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
                        <h2 v-if="eventId.length === 1" class="font-bold text-2xl">
                            Are you sure<br>
                            delete this event?
                        </h2>
                        <h2 v-else class="font-bold text-2xl">
                            Are you sure<br>
                            delete these events?
                        </h2>
                        <p class="text-red-600">{{ eventId.length }} <span v-if="eventId.length === 1">Event</span><span
                                v-else>Events</span></p>
                    </div>
                    <div class="grid space-y-3">
                        <button class="w-80 h-12 l-bg-gray duration-150 border hover:bg-green-700 hover:text-white"
                            @click="$emit('delete', eventId, eventId = [], show = false)">Confirm</button>
                        <button class="w-80 h-12 bg-red-600 text-white hover:bg-red-800 duration-150"
                            @click="show = false">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- If no events -->
    <div v-else>
        <div v-if="prop.status == '' " class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none">No scheduled event</h1>
        </div>
        <div v-else class="grid justify-cente h-96 my-24">
            <h1 class="text-5xl l-color-gray text-center select-none">No Ongoing / Upcoming event at the moment</h1>
        </div>
    </div>
</template>
 
<script setup>
import { computed, ref } from "@vue/runtime-core"

const prop = defineProps({
    data: {
        type: Array,
        require: false
    },
    status: {
        type: String,
        default: ''
    }
})
defineEmits(['delete'])
const eventId = ref([])
const show = ref(false)
const allEventId = () => {
    for (let i = 0; i < prop.data.length; i++) {
        if (!(eventId.value.includes(prop.data[i].id))) {
            eventId.value.push(prop.data[i].id)
        }
    }
    return eventId.value
}

</script>
 
<style>
</style>