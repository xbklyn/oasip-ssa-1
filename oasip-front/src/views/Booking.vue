<template>
    <div class="l-w-full my-24 mx-auto justify-center">
        <h1 class="text-7xl font-bold l-color-navi text-center mb-16">What clinic do you<br>want to book?</h1>
        <div class="l-w-full flex flex-wrap gap-6 justify-center">
            <div class="l-w-400 l-h-624" v-for="(value, index) in clinics" :key="index">
                <div class="l-w-400 h-80">
                    <img :src='value.eventImagePath' alt="">
                </div>
                <div class="grid justify-center mt-6">
                    <h2 class="text-2xl font-bold">{{ value.eventCategoryName }}</h2>
                    <div class="l-w-344 h-40 my-3">
                        <p class="text-base text-gray-400">{{value.eventCategoryDescription}}</p>
                    </div>
                    <router-link :to="{ name: 'BookingForm', params: { id: value.categoryId } }" class="flex space-x-3">
                        <p class="l-color-blue">Go to booking</p>
                        <img src="../assets/component/next.png" alt="" width="24">
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUpdate } from "@vue/runtime-core";
import { getAllCategory, getEventCategoryById, createEvent } from '../services/FetchServices.js'

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

onBeforeMount(async () => {
    const res = await getAllCategory()
    clinics.value = res
})

const clinics = ref([])

</script>
 
<style>
</style>