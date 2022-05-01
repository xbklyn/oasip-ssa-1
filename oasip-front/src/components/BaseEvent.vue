<template>
    <div class="space-y-12 mb-24" v-if="(data.length > 0)">
        <div class="l-w-10-12 mx-auto" v-for="(value, index) in data" :key="index">
            <router-link :to="{ name: 'event-info', params: { id: value.id } }">
                <div class="h-32 flex place-items-center space-x-6">
                    <div class="grid place-items-center w-48 h-16 l-bg-blue rounded-xl">
                        <p class="text-white">
                            {{ value.eventDuration }} Min.
                        </p>
                    </div>
                    <div
                        class="flex l-w-840 h-32 bg-white rounded-xl place-items-center ease-out transition hover:scale-110">
                        <div class="w-10 h-32 l-bg-navi rounded-tl-xl rounded-bl-xl"></div>
                        <div class="ml-12 l-w-720 h-20 place-items-center">
                            <div class="grid grid-cols-2 ">
                                <h3 class="grid place-items-start text-2xl font-bold">
                                    {{ value.eventCategory.eventCategoryName }}
                                </h3>
                                <p class="grid text-xs font-bold justify-items-end place-items-center">
                                    <!-- {{ value.eventStartTime }} - {{ value.eventStartTime + value.eventDuration }} -->
                                    On {{ new Date(value.eventStartTime).toLocaleString('th-TH') }}
                                </p>
                            </div>
                            <div>
                                <p class="text-xs font-regular mt-1">
                                    By {{ value.bookingName }}
                                </p>
                            </div>
                            <div>
                                <p class="text-xs font-regular mt-2 text-slate-400">
                                    {{ value.eventCategory.eventCategoryDescription }}
                                </p>
                            </div>

                        </div>
                    </div>
                </div>
            </router-link>
        </div>

    </div>
    <div v-else>
        <div class="l-w-10-12 mx-auto h-96 my-24">
            <h1 class="text-7xl l-color-gray text-center">No events scheduled</h1>
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