<template>
    <div class="l-w-824 mx-auto mt-12" id="top">
         <!-- Menu - Breadcrumbs -->
        <div class="l-w-824 mx-auto mb-4">
            <div class="flex items-center">
                <router-link :to="{ name: 'Home' }"
                    class="inline-flex items-center text-sm font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4">
                    <svg class="w-5 h-5 mr-1" viewBox="0 0 512 512">
                        <path
                            d="M427 234.625H167.296l119.702-119.702L256 85 85 256l171 171 29.922-29.924-118.626-119.701H427v-42.75z"
                            fill="currentColor"></path>
                    </svg>
                    Back to home
                </router-link>
            </div>
        </div>
        <div class="l-w-400 grid gap-4">
            <h1 class="font-bold text-4xl l-color-navi ">Clinic manage</h1>
            <p class="font-light text-sm">A page for editing category information. Can change the name, duration, and description of a category.</p>

        </div>

        <div class="l-w-824 mx-auto mt-12 grid grid-cols-4 gap-6 mb-16">
            <a href="#edit" v-for="value, index in CLINICS" :key="index" @click="CURR_CLINIC = value.categoryId; reset()"
                :class="['l-w-188 l-h-188 bg-white grid place-items-center duration-200 drop-shadow-md border hover:bg-gradient-to-b to-indigo-700 from-sky-300 hover:text-white', CURR_CLINIC == value.categoryId ? 'bg-gradient-to-b to-indigo-700 from-sky-300 text-white': '']">
                <div class="w-32 h-32 grid place-items-center">
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                        aria-hidden="true" role="img" class="iconify iconify--dashicons" width="72" height="72"
                        preserveAspectRatio="xMidYMid meet" viewBox="0 0 20 20">
                        <path fill="currentColor" d="M5 7h13v10H2V4h7l2 2H4v9h1V7z"></path>
                    </svg>
                    <p class="text-sm truncate w-32">{{ value.eventCategoryName }}</p>
                </div>
            </a>
        </div>
        <div class="l-w-824 h-96 mx-auto" v-if="CURR_DATA" id="edit">
            <h2 class="text-xl font-medium mb-12 my">Category Setting<br><span
                    class="font-semibold text-3xl ">{{ CURR_DATA.eventCategoryName }}</span></h2>
            <div class="grid grid-cols-2 gap-6 gap-y-12">

                <!-- Input - Clinic name -->
                <div class="relative">
                    <input type="text" id="name" v-model="clinic_name.value"
                        @input="clinicNameValidate" maxlength="100"
                        :class="['l-w-400 h-12 pl-2 text-sm text-black border-2 bg-white peer focus:outline-none focus:ring-0 focus:border-blue-600', isClinicNamevalid || isClinicNameLengthValid ? 'border-2 border-red-600 focus:outline-none focus:ring-0 focus:border-red-600' : '']"
                        placeholder=" " />
                    <label for="name"
                        class="l-color-gray-300 absolute text-md duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                        Category name
                    </label>
                    <p v-show="isClinicNamevalid" class="text-sm text-red-600 absolute">Clinic name is already used.</p>
                    <p v-show="isClinicNameLengthValid" class="text-sm text-red-600 absolute">Clinic name must not empty.</p>
                </div>
                <!-- Input - Duration -->
                <div class="relative">
                    <div class="flex absolute inset-y-0 right-5 bottom-1 items-center pl-2 pointer-events-none">
                        <div class="w-5 h-5 text-gray-500 dark:text-gray-400">
                            Min
                        </div>
                    </div>
                    <input type="number" id="duration" v-model="clinic_dur.value" min="0"
                        max="480" @input="durationValidate"
                        :class="['l-w-400 h-12 pl-2 text-sm text-black border-2 bg-white peer focus:outline-none focus:ring-0 focus:border-blue-600', isDurationValid ? 'border-2 border-red-600 focus:outline-none focus:ring-0 focus:border-red-600' : '']"
                        placeholder="30" />
                    <label for="duration"
                        class="l-color-gray-300 absolute text-md duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                        Duration
                    </label>
                    <p v-show="isDurationValid" class="text-sm text-red-600 absolute">Time must between 1 and 480 mins.
                    </p>
                </div>

                <!-- Input - Description -->
                <div class="relative">
                    <textarea type="text" id="description" maxlength="500"
                        v-model="clinic_desc.value"
                        class="bg-white text-black resize-none l-w-824 h-28 pl-2 pt-2 text-sm border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder="Your note..." />
                    <label for="description"
                        class="l-color-gray-300 absolute text-md duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                        Description
                    </label>
                </div>
            </div>

            <div class="flex gap-6">
                <button @click="updateCategory(clinicData)"
                    class="my-12 bg-emerald-400 text-white l-w-188 h-12 duration-150 hover:bg-emerald-600">Update</button>
                <button @click="CURR_CLINIC = 0"
                    class="my-12 bg-white text-red-600 l-w-188 h-12 border border-red-600 duration-150 hover:bg-red-600 hover:text-white">Discard</button>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { computed, onBeforeMount, ref } from "@vue/runtime-core";
import { objectToString } from "@vue/shared";
import { getAllCategory, editCategoryById } from '../services/FetchServices.js'

onBeforeMount(async () => {
    const res = await getAllCategory()
    CLINICS.value = res
})
const isClinicNamevalid = ref(false)
const isClinicNameLengthValid = ref(false)
const isDurationValid = ref(false)
const CLINICS = ref([])
const CURR_CLINIC = ref(0)
const CURR_DATA = computed(() => {
    const data = ref([])
    data.value = CLINICS.value.find(c => {
        return c.categoryId == CURR_CLINIC.value
    })
    return data.value
})


const clinic_desc = computed(() => ref(CURR_DATA.value.eventCategoryDescription))
const clinic_dur = computed(() => ref(CURR_DATA.value.eventCategoryDuration))
const clinic_name = computed(() => ref(CURR_DATA.value.eventCategoryName))

const clinicData = computed(() => {
    return {
        categoryId: CURR_DATA.value.categoryId,
        eventCategoryDescription: clinic_desc.value.value,
        eventCategoryDuration: clinic_dur.value.value,
        eventCategoryName: clinic_name.value.value
    }
})


const durationValidate = () => {
    if (clinicData.value.eventCategoryDuration > 0 && clinicData.value.eventCategoryDuration < 480) {
        isDurationValid.value = false
        return
    } else {
        isDurationValid.value = true
        return
    }
}

const clinicWithoutCurrent = computed(() => {
    const CLINIC = ref([])
    CLINIC.value = CLINICS.value.filter(c => {
        return c.categoryId !== CURR_CLINIC.value
    })
    return CLINIC.value
})

const allEventCategoryName = computed(() => {
    const CLINIC_NAME = ref([])
    for (let i = 0; i < clinicWithoutCurrent.value.length; i++) {
        CLINIC_NAME.value.push(clinicWithoutCurrent.value[i].eventCategoryName)
    }
    return CLINIC_NAME.value
})


const clinicNameValidate = () => {
    if (allEventCategoryName.value.includes(clinicData.value.eventCategoryName.value)) isClinicNamevalid.value = true 
        else isClinicNamevalid.value = false
    if(clinicData.value.eventCategoryName.value.length == 0) isClinicNameLengthValid.value = true
        else isClinicNameLengthValid.value = false
}
const reset = () => {
    isDurationValid.value = false
    isClinicNamevalid.value = false
    isClinicNameLengthValid.value = false
}

const updateCategory = (category) => {
    editCategoryById(category)
    location.reload()

}
</script>
 
<style>
</style>