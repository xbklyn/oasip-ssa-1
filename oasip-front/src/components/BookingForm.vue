<template>
    <div class="grid justify-center">
        <div class="l-w-824 grid grid-cols-2 gap-6 mb-24">
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
                <input type="email" class="l-w-824 h-12 l-bg-gray pl-2" v-model="email" />
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Note (Optional)</h2>
                <input type="text" class="l-w-824 l-bg-gray pb-24 pl-2" v-model="note" />
            </div>
        </div>
        <button class="mb-24 l-bg-blue l-w-824 h-12 font-xl text-white">
            Next
        </button>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUnmount, onBeforeUpdate, onMounted, onUpdated } from "@vue/runtime-core";
import { getEventCategoryById, createEvent } from '../services/FetchServices.js'
import { useRoute, useRouter } from 'vue-router'


const firstName = ref('')
const lastName = ref('')
const group = ref('')
const email = ref('')
const note = ref('')


const date = computed(() => {
    return new Date(new Date(dateTime.value).toLocaleDateString()).toISOString()
})

const startTime = computed(() => {
    return new Date(dateTime.value).toLocaleTimeString('th-TH')
})

const combineName = computed(() => {
    return `${firstName.value} ${lastName.value} (${group.value})`
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