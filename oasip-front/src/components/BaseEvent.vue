<template>
    <div class="grid justify-center" v-if="(data.length > 0)">
        <div class="l-h-1248 w-full pb-24 grid grid-cols-3 gap-6 p-12 mb-96">

            <!-- Main - Events List -->
            <div v-for="value, index in data" :key="index"
                :class="['l-w-400 h-44 duration-150 grid place-items-center', value.statusName == 'COMPLETED' ? 'bg-gray-50 text-slate-300 border' : 'bg-white drop-shadow-sm hover:bg-slate-100 hover:text-black']">
                <router-link :to="{ name: 'scheduled-info', params: { id: value.bookingId } }"
                    class="sl-w-400 h-44 grid place-items-center">
                    <div class="l-w-366 h-32">
                        <div class="grid grid-cols-2">
                            <div class="flex place-items-center gap-2 col-span-2 mb-3 text-xs font-normal">
                                <div v-if="value.statusName == 'UPCOMING'"
                                    class="w-2 h-2 bg-gradient-to-b to-indigo-700 from-blue-600">
                                </div>
                                <div v-else-if="value.statusName == 'ONGOING'" class="w-2 h-2 bg-red-600">
                                    <div class="animate-ping w-2- h-2 bg-red-600"></div>
                                </div>
                                <div v-else class=" w-2 h-2 bg-emerald-500">
                                </div>
                                <p v-if="value.statusName == 'UPCOMING'" class=" text-blue-700">Upcoming
                                </p>
                                <p v-else-if="value.statusName == 'ONGOING'" class=" text-red-600">On going
                                </p>
                                <p v-else class=" text-emerald-500">Complete</p>
                            </div>

                            <div class=" col-span-2 justify-self-end">
                                <p class="text-xs mt-1 grid justify-end font-normal">{{ value.eventDuration }} Min.</p>
                            </div>

                            <div class="col-span-4">
                                <p class="text-xs truncate font-normal">{{ value.categoryName }}</p>
                            </div>

                            <div class="col-span-3 mb-1">
                                <h2 class="text-xl font-semibold truncate">{{ value.bookingName }}</h2>
                            </div>

                            <div class="col-span-4 mb-3">
                                <p class="text-xs font-light">
                                    <span class="font-medium">{{ new
                                            Date(value.eventStartTime).toLocaleDateString('th-TH')
                                    }}</span>
                                    at <span class="font-medium">{{ `${new
                                            Date(value.eventStartTime).toLocaleTimeString('th-TH').split(':')[0]}:${new
                                                Date(value.eventStartTime).toLocaleTimeString('th-TH').split(':')[1]} `
                                    }}</span>
                                </p>
                            </div>
                        </div>

                        <div class="col-span-4 text-xs font-light">
                            <p class="l-color-gray overflow-hidden l-w-366 h-8"><span class="font-medium">Note:
                                </span>{{
                                        value.eventNotes ==
                                            '' ? 'No description' : value.eventNotes
                                }}
                            </p>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>

    <!-- If no events -->
    <div v-else class="w-full h-full grid place-items-center">
        <div v-if="prop.status == ''" class="grid justify-center place-items-center h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none">No scheduled event</h1>
        </div>
        <div v-else-if="prop.status == 'upcoming'" class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none leading-normal">No Upcoming / Ongoing event <br>at
                this moment</h1>
        </div>
        <div v-else-if="prop.status == 'ongoing'" class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none leading-normal">No Ongoing event
                <br>at this moment
            </h1>
        </div>
        <div v-else class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none leading-normal">No Completed event
                <br>at this moment
            </h1>
        </div>
    </div>
</template>
 
<script setup>
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

</script>
 
<style>
</style>