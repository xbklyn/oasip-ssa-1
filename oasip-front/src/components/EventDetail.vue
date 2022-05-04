<template>
    <div class="flex place-items-center mb-24 space-x-6 justify-center drop-shadow-md items-start">
        <div class="l-w-408 h-96 bg-white">
            <div class="w-full h-12 l-bg-navi mb-6"></div>
            <div class="w-72 h-64 mx-auto space-y-3">
                <h3 class="text-2xl font-bold">Clinic detail</h3>
                <div class="grid grid-cols-2 gap-6 w-72 h-48 ">
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Clinic name</h4>
                        <p class="text-sm font-light">{{ data.eventCategoryName }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Duration (Clinic)</h4>
                        <p class="text-sm font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="col-span-2 space-y-1">
                        <h4 class="text-base text-gray-300">Description</h4>
                        <p class="text-sm font-light h-20 w-full">{{ data.eventCategoryDescription }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="l-w-624 h-96 bg-white">
            <div class="l-bg-navi w-full h-12 grid place-items-center"></div>
            <div class="h-80 l-w-520 space-y-3 mx-auto mt-6">
                <h3 class="text-2xl font-bold">Personal detail</h3>
                <div class="grid grid-cols-2 gap-6">
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Name</h4>
                        <p class="text-sm font-light">{{ data.bookingName }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Email</h4>
                        <p class="text-sm font-light">{{ data.bookingEmail }}</p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Time</h4>
                        <p class="text-sm font-light">
                            Start: {{ data.eventStartTime }}
                        </p>
                        <p class="text-sm font-light">
                            End: {{ data.eventEndTime }}
                        </p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Duration</h4>
                        <p class="text-sm font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="grid col-span-2 space-y-1">
                        <h4 class="text-base text-gray-300">Note</h4>
                        <p class="text-sm h-20 font-light">{{ data.eventNotes == '' ? 'None' : data.eventNotes }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="grid gap-3">
            <button @click="$emit('exit')"><img src="../assets/component/quit.png" alt=""></button>
            <button @click="''"><img src="../assets/component/edit.png" alt=""></button>
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

defineEmits(['exit'])

</script>
 
<style>
</style>