<template>
    <div class="grid justify-center" v-if="(data.length > 0)">
        <div class="l-w-1248 l-h-1176 pb-24 grid grid-cols-2 gap-6">
            <div class="l-w-612 h-44 flex" v-for="value, index in data" :key="index">
                <div class="l-w-106 h-44 grid place-items-center"><input type="checkbox" width="24" class="ml-10"></div>
                <router-link :to="{ name: 'event-info', params: { id: value.id } }">
                    <div class="l-w-506 h-44 l-bg-gray flex place-items-center">
                        <div class="w-12 h-44 l-bg-navi"></div>
                        <div class="l-w-408 h-28 ml-6">
                            <div class="grid grid-cols-3">
                                <div class="col-span-2">
                                    <p class="text-md font-semibold">{{ value.eventCategoryName }}</p>
                                </div>
                                <div class="place-self-end">
                                    <p class="text-xs font-semibold">On {{ new
                                            Date(value.eventDate).toLocaleDateString()
                                    }}</p>
                                    <p class="text-xs grid justify-end mt-1">{{ value.eventDuration }} Min.</p>
                                </div>
                                <div class="col-span-3">
                                    <h2 class="text-xl font-bold">{{ value.bookingName }}</h2>
                                </div>
                            </div>
                            <div class="col-span-2 text-xs mt-6">
                                <p class="l-color-gray-300"><span class="font-bold">Note: </span>{{ value.eventNotes == ''?'None':value.eventNotes }}
                                </p>
                            </div>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
        <div class="font-bold text-4xl l-color-gray text-center pb-24">
            <p>Page Number</p>
        </div>
    </div>
    <div v-else>
        <div class="grid justify-cente h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center select-none">No scheduled event</h1>
        </div>
    </div>
</template>
 
<script setup>

const date = new Date()
const prop = defineProps({
    data: {
        type: Array,
        require: false
    }
})
</script>
 
<style>
</style>