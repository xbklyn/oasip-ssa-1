<template>
    <div class="flex place-items-center mb-24 space-x-6 justify-center drop-shadow-md">
        <div class="l-w-624 h-96 bg-white rounded-md">
            <div class="l-bg-blue w-full h-12 rounded-tl-md rounded-tr-md grid place-items-center"></div>
            <div class="h-80 l-w-520 space-y-3 mx-auto mt-6">
                <h3 class="text-2xl font-semibold">Personal detail</h3>
                <div class="grid grid-cols-2 gap-6">
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Name</h4>
                        <p class="text-xs font-light">{{ data.bookingName }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Email</h4>
                        <p class="text-xs font-light">{{ data.bookingEmail }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Time</h4>
                        <p class="text-xs font-light">
                            Start: {{ new Date(data.eventStartTime).toLocaleString() }}
                        </p>
                        <p class="text-xs font-light">
                            End: {{ new Date(new Date(data.eventStartTime).getTime() + (data.eventDuration * 60000)).toLocaleString() }}
                        </p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Duration</h4>
                        <p class="text-xs font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="grid col-span-2 space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Note</h4>
                        <p v-if="data.eventNotes.length > 0" class="text-xs font-light h-20">{{ data.eventNotes }}</p>
                        <p v-else class="text-xs font-light h-20">None</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="l-w-408 h-96 bg-white rounded-md">
            <div class="w-full h-12 l-bg-blue rounded-tl-md rounded-tr-md mb-6"></div>
            <div class="w-72 h-64 mx-auto space-y-3">

                <h3 class="text-2xl font-semibold">Clinic detail</h3>
                <div class="grid grid-cols-2 gap-6 w-72 h-48 ">
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Clinic name</h4>
                        <p class="text-xs font-light">{{ data.eventCategory.eventCategoryName }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Duration</h4>
                        <p class="text-xs font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="col-span-2 space-y-1">
                        <h4 class="text-base font-semibold text-gray-300">Description</h4>
                        <p class="text-xs font-light h-20 w-full">{{ data.eventCategory.eventCategoryDescription }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
const prop = defineProps({
    data: {
        type: Object,
        require: false
    }
})


</script>
 
<style>
</style>