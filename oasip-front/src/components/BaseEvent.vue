<template>
    <div class="grid justify-center" v-if="(data.length > 0)">
        <div class="l-h-1248 w-full pb-24 grid grid-cols-3 gap-6 p-12 mb-96">

            <!-- Main - Events List -->
            <div class="l-w-400 h-44 bg-white drop-shadow-sm hover:bg-slate-100 hover:text-black duration-150 grid place-items-center"
                v-for="value, index in data" :key="index">
                <router-link :to="{ name: 'scheduled-info', params: { id: value.id } }">
                    <div class="l-w-366 h-32">
                        <div class="grid grid-cols-2">
                            <div class="flex place-items-center gap-2 col-span-2 mb-3 l-text-xxs font-light">
                                <div v-if="value.statusName == 'UPCOMING'" class="w-2 h-2 bg-blue-700"></div>
                                <div v-else-if="value.statusName == 'ONGOING'" class="w-2 h-2 bg-red-600"></div>
                                <div v-else class="w-2 h-2 bg-green-400"></div>
                                <p v-if="value.statusName == 'UPCOMING'" class="text-blue-700">Upcoming
                                </p>
                                <p v-else-if="value.statusName == 'ONGOING'" class="text-red-600">On going
                                </p>
                                <p v-else class=" text-green-700">Complete</p>
                            </div>

                            <div class=" col-span-2 justify-self-end">
                                <p class="text-xs mt-1 grid justify-end">{{ value.eventDuration }} Min.</p>
                            </div>

                            <div class="col-span-4">
                                <p class="text-xs">{{ value.categoryName }}</p>
                            </div>

                            <div class="col-span-3 mb-1">
                                <h2 class="text-xl font-normal truncate">{{ value.bookingName }}</h2>
                            </div>

                            <div class="col-span-4 mb-3">
                                <p class="text-xs font-light">
                                    On {{ new Date(value.eventDate).toLocaleDateString('th-TH') }} at {{
                                            value.eventStartTime
                                    }}
                                </p>
                            </div>
                        </div>

                        <div class="col-span-4 text-xs font-light">
                            <p class="l-color-gray overflow-hidden l-w-366 h-8"><span class="font-semibold">Note:
                                </span>{{
                                        value.eventNotes ==
                                            '' ? 'None' : value.eventNotes
                                }}
                            </p>
                        </div>
                    </div>

                </router-link>
            </div>
        </div>

        <!-- Menu - Delete events -->
        <!-- <div v-show="eventId.length > 0"
            class="grid grid-cols-2 place-items-center w-screen h-16 bg-white drop-shadow-2xl overflow-y-auto overflow-x-hidden fixed bottom-0 right-0 left-0 z-50">
            <div class="justify-self-start ml-24">
                <button class="l-color-blue" @click="allEventId">Select All</button>
            </div>
            <div class="justify-self-end space-x-12 mr-24 ">
                <button class="text-red-700 " @click="show = true">Delete ({{ eventId.length
                }})</button>
                <button class="l-color-blue" @click="eventId = []">Cancel</button>
            </div>
        </div> -->

        <!-- Modal box - Confirmation -->
        <!-- <div v-show="show"
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
        </div> -->
    </div>

    <!-- If no events -->
    <div v-else class="w-full h-full grid place-items-center">
        <div v-if="prop.status == ''" class="grid justify-center place-items-center h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none">No scheduled event</h1>
        </div>
        <div v-else class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none leading-normal">No Ongoing / Upcoming event <br>at
                this moment</h1>
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