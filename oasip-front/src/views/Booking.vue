<template>
    <div class="l-w-full mb-12 mx-auto justify-center">

        <!-- Menu - Breadcrumbs -->
        <div class="l-w-824 mx-auto mt-12 mb-4">
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

        <div class="l-w-824 mx-auto my-24">
            <div class="l-w-400 mt-24 grid gap-4">
                <h1 class="font-bold text-4xl l-color-navi ">Booking</h1>
                <p class="font-light text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lectus amet a
                    lectus aliquam semper mi sem. Dui
                    urna
                    dolor ultrices venenatis commodo. Orci pellentesque rhoncus sagittis, odio pretium vitae tristique.
                    Ultrices
                    non pellentesque ac velit aliquam sed non nisl eget.</p>
            </div>
        </div>

        <!-- Step 1 - Select clinic -->
        <div class="l-w-824 h-40 mx-auto flex">
            <div class="w-52 h-40 place-items-center">
                <h2>Step 1 <span class="text-red-500">*</span></h2>
                <p class="l-text-xxs">What clinic do you want to book?</p>
            </div>
            <div>
                <div class="flex place-items-center" v-for="value, index in clinics" :key="index">
                    <div class="w-56">
                        <input :id="value.eventCategoryName" type="radio" :value='value.categoryId' name="eventCategory"
                            v-model="clinicId" @click="clinicIndex = index" @input="computeTimePeriod"
                            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label :for="value.eventCategoryName"
                            class="ml-2 text-sm font-light hover:text-blue-400 cursor-pointer">
                            {{ value.eventCategoryName }}
                        </label>
                    </div>
                    <p class="l-text-xxs font-semibold ml-3">{{ value.eventCategoryDuration }} Mins</p>
                </div>
            </div>
        </div>

        <div class="l-w-824 h-px bg-black mx-auto" v-show="clinicId"></div>

        <!-- Step 2 - Fill infomation -->
        <div class="l-w-824 h-64 mx-auto flex m-12 duration-150 ease-in-out transition" v-show="clinicId">
            <div class="w-52 h-64 place-items-center">
                <h2>Step 2</h2>
                <p class="l-text-xxs">Fill your infomation.</p>
            </div>
            <div class="space-y-6">

                <!-- Input - Email -->
                <div class="relative">
                    
                    <input type="text" id="email" v-model="email" @change="isEmailValid"
                        :class="['l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', wrongEmail || email.length > 100 ? 'border-red-500 focus:border-red-500' : '']"
                        placeholder=" " />
                    <label for="email"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Email {{email.length}}/100<span class="text-red-500"> * </span><span v-show="(email.length > 100)" class="text-sm text-red-500 ml-2">Email size more than 100
                    </span>
                    </label>
                    <p v-show="wrongEmail" class="absolute text-sm text-red-500 ml-2">Incorrect email address</p>

                </div>
                <div class="grid gap-6">

                    <!-- Input - First name -->
                    <div class="relative">
                        <input type="text" id="firstName" v-model="firstName" @change="isFirstNameValid"
                            :class="['l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', firstNameNotValid ? 'border-red-500 focus:border-red-500' : '']"
                            placeholder=" " />
                        <label for="firstName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Name<span class="text-red-500">*</span>
                        </label>
                        <p v-show="firstNameNotValid" class="absolute text-sm text-red-500 ml-2">Name size must be
                            beween 1 and 100</p>
                    </div>

                    <!-- Input - Last name -->
                    <!-- <div class="relative">
                        <input type="text" id="lastName" v-model="lastName" @input="isFirstNameValid"
                            :class="['l-w-188 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', firstNameNotValid ? 'border-red-500 focus:border-red-500' : '']"
                            placeholder=" " />
                        <label for="lastName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Last Name
                        </label>
                    </div> -->

                    <!-- Input - Group -->
                    <!-- <div class="relative">
                        <input type="text" id="group" v-model="group" @input="isFirstNameValid"
                            :class="['l-w-188 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', firstNameNotValid ? 'border-red-500 focus:border-red-500' : '']"
                            placeholder=" " />
                        <label for="group"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Group
                        </label>
                    </div> -->

                </div>

                <!-- Input - Note -->
                <div class="relative">
                    <textarea type="text" id="note" maxlength="500" v-model="note"
                        class="resize-none l-w-612 h-28 pl-2 pt-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder="Your note..." />
                    <label for="note"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10  bg-white px-2 left-1">
                        Note {{ note.length }}/500
                    </label>
                </div>
            </div>
        </div>

        <div class="l-w-824 h-px bg-black mx-auto" v-show="firstName !== '' && email !== ''"></div>

        <!-- Step 3 - Select time period -->
        <div class="l-w-824 h-96 mx-auto flex m-12 mb-12" v-show="firstName !== '' && email !== ''">
            <div class="w-52 h-24 place-items-center">
                <h2>Step 3<span class="text-red-500">*</span></h2>
                <p class="l-text-xxs">Select time period.</p>

            </div>
            <div class="l-w-612 h-12 grid gap-6">

                <!-- Input - Date -->
                <div class="relative">
                    <input type="date" id="dateTime" v-model="selectDate" :min="currentDate" @input="computeTimePeriod"
                        @change="startTime = -1"
                        class="block l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" " />
                    <label for="dateTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Date
                    </label>
                </div>

                <!-- Button - Time selector -->
                <div class="l-w-612 grid grid-cols-6 gap-6 mt-6">
                    <button v-for="(time, index) in TimePeriod" :key="index" @click="startTime = index"
                        :class="['h-8 text-sm duration-150 bg-white', startTime == index ? 'bg-blue-500 text-white border-0' : '', isOverlap(index) ? 'bg-slate-200 text-gray-300' : 'hover:bg-blue-500 hover:text-white border border-gray-300 hover:border-none']"
                        :disabled="isOverlap(index)">
                        {{ time.startTime }}
                    </button>
                </div>
            </div>
        </div>

        <!-- Button - Submition -->
        <div class="l-w-824 h-12 mx-auto">
            <button
                :class="['w-full h-full text-white duration-150', isAllvalid ? 'bg-slate-200' : 'l-bg-navi hover:bg-slate-800']"
                :disabled="isAllvalid" @click="submit(combineName, email, dateTime, clinicId, note)">Submit</button>
        </div>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUpdate } from "@vue/runtime-core";
import { getAllCategory, createEvent, getEventByCatAndDate } from '../services/FetchServices.js'
import { useRoute, useRouter } from "vue-router";



// @@@@@@ HOOK @@@@@@
onBeforeMount(async () => {
    const res = await getAllCategory()
    clinics.value = res
})

const getTime = computed(async () => {
    const temp = await getEventByCatAndDate(clinicId.value, selectDate.value)
    return temp
})

onBeforeUpdate(async () => {
    TimeBooked.value = await getTime.value
})



// @@@@@@ ATTIBUTE @@@@@@
const myRouter = useRouter()
const clinics = ref([])
const clinicId = ref(0)
const clinicIndex = ref()
const firstName = ref('')
const lastName = ref('')
const group = ref('')
const combineName = computed(() => {
    // return `${firstName.value} ${lastName.value} ${group.value.length != 0 ? '(' + group.value + ')' : ''}`
    return `${firstName.value}`
})
const email = ref('')
const note = ref('')
const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}`
})
const selectDate = ref('')
const TimePeriod = ref([])
const TimeBooked = ref([])
const startTime = ref(-1)
const dateTime = computed(() => {
    return new Date(`${selectDate.value}T${TimePeriod.value[startTime.value].startTime}`)
})



// @@@@@@ FUNCTION @@@@@@
// Create event
const submit = (name, mail, start, categoryId, notes) => {
    createEvent(name, mail, start, categoryId, notes)
    myRouter.push({
        name: 'Home'
    })
}

// Get All start time
const allEventStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        bookedStartTime.value.push(new Date(TimeBooked.value[i].eventStartTime).toLocaleTimeString('th-TH'))
    }
    return bookedStartTime
})

// Check overlap
const isOverlap = (index) => {
    let start = new Date(new Date(currentDate.value).getFullYear(), new Date(currentDate.value).getMonth(), new Date(currentDate.value).getDate(), TimePeriod.value[index].startTime.split(":")[0], TimePeriod.value[index].startTime.split(":")[1]).getTime();
    let cur = new Date().getTime()

    // Check if overlap with booked time
    if (allEventStartTime.value.value.includes(TimePeriod.value[index].startTime) ||
        (start < cur && new Date(selectDate.value).getDate() == new Date(currentDate.value).getDate())
    ) {
        return true
    } else {
        return false
    }
}

// Create time period
const MAX = 480;
const BREAK = 5;
const CATE_DURATION = computed(() => clinics.value[clinicIndex.value].eventCategoryDuration);
const computeTimePeriod = async () => {
    console.log("in time method");
    if (!clinicId.value && selectDate.value == '' || !clinicId.value && selectDate.value !== '' || clinicId.value && selectDate.value == '') { }
    else {
        TimePeriod.value = []
        let init = new Date();
        init.setHours(8);
        init.setMinutes(0);
        init.setSeconds(0);

        let i = 0
        while (i + CATE_DURATION.value < MAX) {
            let start = new Date(init);
            let plusMinutes = start.getMinutes() + CATE_DURATION.value;
            let end = new Date(start);
            end.setMinutes(plusMinutes);
            TimePeriod.value.push({ startTime: start.toLocaleTimeString("th-TH"), endTime: end.toLocaleTimeString("th-TH") })
            init = new Date(end);
            init.setMinutes(end.getMinutes() + BREAK)
            i += (CATE_DURATION.value + BREAK)
        }
    }
}

// Check all validate
const isAllvalid = computed(() => {
    if (wrongEmail.value || firstNameNotValid.value) {
        return true
    }
    if (email.value.length === 0 || firstName.value.length === 0 || selectDate.value.length === 0 || startTime.value == -1) {
        return true
    }
    return false
})

// Email validation
const checkEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z\-0-9]{2,}))$/;
const wrongEmail = ref(false)
const isEmailValid = () => {
    if (checkEmail.test(email.value) && email.value.length <= 100) {
        wrongEmail.value = false;
        return true
    }
    else {
        wrongEmail.value = true;
        return false
    }
}

// Name validation
const firstNameNotValid = ref(false)
const isFirstNameValid = () => {
    if (combineName.value.length == 0 || combineName.value.length > 100) {
        console.log(combineName.value.length);
        firstNameNotValid.value = true
        return false
    } else {
        firstNameNotValid.value = false
        return true
    }
}

</script>
 
<style>
</style>