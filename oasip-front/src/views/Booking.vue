<template>
    <div class="l-w-full mt-12 mx-auto justify-center">
        <button class="float-right" @click="quit"><img src="../assets/component/quit.png" alt=""></button>
        <!-- Booking - Step 1 -->
        <div v-show="stepOne">
            <div class="w-20 h-4 flex gap-4 mx-auto">
                <div class="w-4 h-4 l-bg-blue"></div>
                <div class="w-4 h-4 l-bg-gray"></div>
                <div class="w-4 h-4 l-bg-gray"></div>
            </div>
            <div>
                <p class="text-base font-semibold mb-4">Step 1: What clinic do you want to book?</p>
                <h1 class="text-4xl font-bold mb-12">Step 1: What clinic do you want to book?</h1>
            </div>
            <div class="grid justify-center gap-2 cursor-pointer l-w-824 mx-auto">
                <div class="l-w-824 h-24 l-bg-gray flex" v-for="value, index in clinics" :key="index"
                    @click="clinicId = value.categoryId, clinicIndex = index">
                    <div class="w-24 h-24 grid place-items-center">
                        <div v-if="clinicId == value.categoryId" class="w-4 h-4 l-bg-blue"></div>
                        <div v-else class="w-4 h-4 bg-white border border-blue-300"></div>
                    </div>
                    <div class="l-w-632 h-24 grid content-center">
                        <div class="">
                            <div class="flex place-items-center space-x-6">
                            <h2 class="text-xl font-bold">{{ value.eventCategoryName }}</h2>
                            <p class="text-sm font-semibold">{{value.eventCategoryDuration}} Min.</p>
                            </div>
                            <p class="text-xs text-gray-400">{{ value.eventCategoryDescription }}</p>
                        </div>
                    </div>
                    <div>
                        <img :src='value.imagePath' alt="">
                    </div>
                </div>
            </div>

            <button
                :class="['grid place-items-center mx-auto l-w-824 h-12 l-bg-blue mt-6 text-white', clinicId == 0 ? 'bg-gray-300' : '']"
                :disabled="clinicId == 0" @click="stepOne = false, stepTwo = true">Next</button>
        </div>

        <!-- Booking - Step 2 -->
        <div v-show="stepTwo">
            <div class="w-20 h-4 flex gap-4 mx-auto">
                <div class="w-4 h-4 l-bg-blue"></div>
                <div class="w-4 h-4 l-bg-blue"></div>
                <div class="w-4 h-4 l-bg-gray"></div>
            </div>
            <div>
                <p class="text-base font-semibold mb-4"><span
                        class="l-color-blue underline underline-offset-auto cursor-pointer font-normal"
                        @click="stepOne = true, stepTwo = false">Step 1</span> / Step 2: Enter your information</p>
                <h1 class="text-4xl font-bold mb-16">Step 2: Enter your information.</h1>
            </div>
            <div class="l-w-824 mx-auto">
                <div class="grid justify-center">
                    <div class="l-w-824 grid grid-cols-2 gap-6 mb-24">
                        <div class="flex space-x-6">
                            <div>
                                <h2 class="l-color-gray-300 text-base">Fisrt name</h2>
                                <input type="text" class="l-w-294 h-12 l-bg-gray pl-6" v-model="firstName" />
                            </div>
                            <div>
                                <h2 class="l-color-gray-300 text-base">Last name</h2>
                                <input type="text" class="l-w-294 h-12 l-bg-gray pl-6" v-model="lastName" />
                            </div>
                            <div>
                                <h2 class="l-color-gray-300 text-base">Group</h2>
                                <input type="text" class="l-w-188 h-12 l-bg-gray pl-6" v-model="group" />
                            </div>
                        </div>
                        <div class="col-span-2">
                            <h2 class="l-color-gray-300 text-base">Email</h2>
                            <input type="email" class="l-w-824 h-12 l-bg-gray pl-6" v-model="email" />
                        </div>
                        <div class="col-span-2">
                            <h2 class="l-color-gray-300 text-base">Note (Optional) <span class="text-xs">{{note.length}}/500</span></h2>
                            <textarea maxlength="500" type="text" class="l-w-824 l-bg-gray pb-24 pl-2 resize-none focus:outline-none"
                                v-model="note"></textarea>
                        </div>
                    </div>
                    <button class="l-bg-blue l-w-824 h-12 font-xl text-white"
                        @click="stepOne = false, stepTwo = false, stepThree = true">
                        Next
                    </button>
                </div>
            </div>
        </div>

        <!-- Booking - Step 3 -->
        <div v-show="stepThree">
            <div class="w-20 h-4 flex gap-4 mx-auto">
                <div class="w-4 h-4 l-bg-blue"></div>
                <div class="w-4 h-4 l-bg-blue"></div>
                <div class="w-4 h-4 l-bg-blue"></div>
            </div>
            <div>
                <p class="text-base font-semibold mb-4"><span
                        class="l-color-blue underline underline-offset-auto cursor-pointer font-normal"
                        @click="stepOne = true, stepTwo = false, stepThree = false">Step 1</span> / <span
                        class="l-color-blue underline underline-offset-auto cursor-pointer font-normal"
                        @click="stepOne = false, stepTwo = true, stepThree = false">Step 2</span> / Step 3 : Select time
                    period</p>
                <h1 class="text-4xl font-bold mb-16">Step 3 : Select time period</h1>
            </div>
            <div class="l-w-824 mx-auto">
                <div class="grid justify-center">
                    <div class="l-w-824 grid grid-cols-2 gap-6 mb-24">
                        <div class="flex space-x-6">
                            <div>
                                <h2 class="l-color-gray-300 text-base">Date</h2>
                                <input type="date" class="l-w-400 h-12 l-bg-gray pl-2" v-model="dateTime" />
                            </div>
                            <div>
                                <h2 class="l-color-gray-300 text-base">StartTime</h2>
                                <!-- <select type="time" class="l-w-400 h-12 l-bg-gray pl-2" v-model="startTime">
                                    <option v-for="" value=""></option>
                                </select> -->
                                <input type="time" class="l-w-400 h-12 l-bg-gray pl-2" v-model="startTime" />
                            </div>
                        </div>
                    </div><button class="mb-24 l-bg-blue l-w-824 h-12 font-xl text-white"
                        @click="submit(combineName, email, date, time, clinics[clinicIndex].eventCategoryDuration, clinicId, note)">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUpdate } from "@vue/runtime-core";
import { getAllCategory, getEventCategoryById, createEvent } from '../services/FetchServices.js'
import { useRoute, useRouter } from "vue-router";
const myRouter = useRouter()

onBeforeMount(async () => {
    const res = await getAllCategory()
    clinics.value = res
})

const stepOne = ref(true)
const stepTwo = ref(false)
const stepThree = ref(false)

const clinics = ref([])
const clinicId = ref(1)
const clinicIndex = ref(1)

const firstName = ref('')
const lastName = ref('')
const group = ref('')
const combineName = computed(() => {
    return `${firstName.value} ${lastName.value} ${group.value.length != 0 ? '(' + group.value + ')' : ''}`
})

const email = ref('')

const dateTime = ref()
const date = computed(() => {
    return new Date(new Date(dateTime.value)).toISOString()
})

const startTime = ref()
const time = computed(() => {
    return `${startTime.value}:00`
})

const note = ref('')

const submit = (name, mail, date, start, duration, categoryId, notes) => {
    createEvent(name, mail, date, start, duration, categoryId, notes)
    firstName.value = ''
    lastName.value = ''
    email.value = ''
    dateTime.value = ''
    startTime.value = ''
    note.value = ''

    myRouter.push({
        name: 'Home'
    })
}

const quit = () => {
    firstName.value = ''
    lastName.value = ''
    email.value = ''
    dateTime.value = ''
    startTime.value = ''
    note.value = ''
    clinicId.value = 1
    clinicIndex.value = 1

    myRouter.push({
        name: 'Home'
    })
}

</script>
 
<style>
</style>