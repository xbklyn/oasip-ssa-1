<template>
    <div class="grid justify-center gap-6">

        <!-- Detail - Personal -->
        <div>
            <div class="l-w-10-12 h-80 space-y-3 mt-6">
                <h3 class="text-2xl font-semibold">Personal detail</h3>
                <div class="grid grid-cols-4 gap-6">
                    <div class="space-y-1 col-span-2">
                        <h4 class="text-base text-gray-300">Name</h4>
                        <p class="text-sm font-light">{{ data.bookingName }}</p>
                    </div>
                    <div class="space-y-1 col-span-2 w-96">
                        <h4 class="text-base text-gray-300">Email</h4>
                        <p class="text-sm font-light">{{ data.bookingEmail }}</p>
                    </div>
                    <div class="space-y-1 col-span-2">
                        <h4 class="text-base text-gray-300">Time</h4>
                        <p class="text-sm font-light">
                           On {{new Date(data.eventStartTime).toLocaleString('th-TH')}} - {{new Date(data.eventEndTime).toLocaleTimeString('th-TH')}}
                        </p>
                    </div>
                    <div class="space-y-1">
                        <h4 class="text-base text-gray-300">Duration</h4>
                        <p class="text-sm font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="grid col-span-4 space-y-1">
                        <h4 class="text-base text-gray-300">Note</h4>
                        <p class="text-sm h-20 font-light">{{ data.eventNotes == '' ? 'None' : data.eventNotes }}</p>
                    </div>
                </div>
            </div>
        </div> 

        <div class="w-full h-px bg-black"></div>

        <!-- Detail - Clinic -->
        <div>
            <div class="l-w-10-12 h-80 space-y-3 mt-6">
                <h3 class="text-2xl font-semibold">Clinic detail</h3>
                <div class="grid grid-cols-4 gap-6">
                    <div class="space-y-1 col-span-2">
                        <h4 class="text-base text-gray-300">Clinic name</h4>
                        <p class="text-sm font-light">{{ data.eventCategoryName }}</p>
                    </div>
                    <div class="space-y-1 col-span-2">
                        <h4 class="text-base text-gray-300">Duration (Clinic)</h4>
                        <p class="text-sm font-light">{{ data.eventDuration }} Min.</p>
                    </div>
                    <div class="col-span-4 space-y-1">
                        <h4 class="text-base text-gray-300">Description</h4>
                        <p class="text-sm font-light h-20 w-full">{{ data.eventCategoryDescription }}</p>
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