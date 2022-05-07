<template>
    <div class="grid justify-center">
        <div class="flex text-base mt-24 mb-16">
            <router-link :to="{ name: 'Booking' }" class="l-color-blue underline">Booking</router-link>
            <p> > Enter you infomation</p>
        </div>
        <h1 class="text-7xl font-bold mb-24">Fill your information</h1>
        <div class="l-w-824 grid grid-cols-2 gap-6 space-y-4 mb-24">
            <div>
                <h2 class="l-color-gray-300 text-base">Clinic Selected</h2>
                <select class="l-w-400 h-12 l-bg-gray" v-model="eventCategoryId">
                    <option value="4">Front-end</option>
                    <option value="5">Back-end</option>
                    <option value="3">Database</option>
                    <option value="2">DevOps</option>
                    <option value="1">PM</option>
                </select>
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base">Duration</h2>
                <input type="text" class="l-w-400 h-12 bg-white pl-2 text-gray-500 font-semibold" :disabled="true"
                    :value='eventsCategoryData.eventCategoryDuration' />
            </div>
            <div class="flex space-x-6">
                <div>
                    <h2 class="l-color-gray-300 text-base">Fisrt name</h2>
                    <input type="text" class="l-w-294 h-12 l-bg-gray pl-2" v-model="firstName" />
                </div>
                <div>
                    <h2 class="l-color-gray-300 text-base">Last name</h2>
                    <input type="text" class="l-w-294 h-12 l-bg-gray pl-2" v-model="lastName" />
                </div>
                <div>
                    <h2 class="l-color-gray-300 text-base">Group</h2>
                    <input type="text" class="l-w-188 h-12 l-bg-gray pl-2" v-model="group" />
                </div>
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Email</h2>
                <input type="text" class="l-w-824 h-12 l-bg-gray pl-2" v-model="email" />
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Date</h2>
                <input type="datetime-local" class="l-w-824 h-12 l-bg-gray pl-2" v-model="dateTime" />
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Note (Optional)</h2>
                <input type="text" class="l-w-824 l-bg-gray pb-24 pl-2" v-model="note" />
            </div>
        </div>
        <button class="mb-24 l-bg-blue l-w-824 h-12 font-xl text-white"
            @click="submit(combineName, email, date, startTime, eventsCategoryData.eventCategoryDuration, eventCategoryId, note)">
            Submit
        </button>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUpdate } from "@vue/runtime-core";
import { getEventCategoryById, createEvent } from '../services/FetchServices.js'

onBeforeUpdate(async () => {
    eventsCategoryData.value = await categoryData.value
})

onBeforeMount(async () => {
    eventsCategoryData.value = await categoryData.value
})

const eventCategoryId = ref(1)
const eventsCategoryData = ref([])

const firstName = ref('')
const lastName = ref('')
const group = ref()
const email = ref('')
const dateTime = ref()

const date = computed(() => {
    return new Date(new Date(dateTime.value).toLocaleDateString()).toISOString()
})

const startTime = computed(() => {
    return new Date(dateTime.value).toLocaleTimeString('th-TH')
})

const note = ref('')

const combineName = computed(() => {
    return `${firstName.value} ${lastName.value} (${group.value})`
})

const categoryData = computed(async () => {
    const temp = await getEventCategoryById(eventCategoryId.value)
    return temp
})

const submit = (name, mail, dateTime, start, duration, categoryId, notes) => {
    createEvent(name, mail, dateTime, start, duration, categoryId, notes)
    firstName.value = ''
    lastName.value = ''
    email.value = ''
    date.value = ''
    startTime.value = ''
    note.value = ''
}


</script>
 
<style>
</style>