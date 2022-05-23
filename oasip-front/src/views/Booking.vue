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
                <p class="font-light text-sm l-color-gray-300">Users must follow the following steps<br>
                    Step 1 : Select the clinic you want to book.<br>
                    Step 2 : Fill in your information<br>
                    Step 3 : Select the date you wish to book. and select the desired time slot in the available time
                    slots</p>
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
                    <div class="w-56 truncate">
                        <input :id="value.eventCategoryName" type="radio" :value='value.categoryId' name="eventCategory"
                            v-model="clinicId" @click="clinicIndex = index" @input="computeTimePeriod"
                            @change="startTime = -1" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label :for="value.eventCategoryName"
                            class="ml-2 text-sm font-medium hover:text-blue-400 cursor-pointer text-ellipsis overflow-hidden">
                            {{ value.eventCategoryName }}
                        </label>
                    </div>
                    <p class="text-xs font-light ml-3">{{ value.eventCategoryDuration }} Mins</p>
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
                        Email {{ email.length }}/100<span class="text-red-500"> * </span><span
                            v-show="(email.length > 100)" class="text-sm text-red-500 ml-2">Email size more than 100
                        </span>
                    </label>
                    <p v-show="wrongEmail" class="absolute text-sm text-red-500 ml-2">Incorrect email address</p>

                </div>
                <div class="grid gap-6">

                    <!-- Input - Full name -->
                    <div class="relative">
                        <input type="text" id="firstName" v-model="fullName" @change="isFirstNameValid"
                            :class="['l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', firstNameNotValid ? 'border-red-500 focus:border-red-500' : '']"
                            placeholder=" " />
                        <label for="firstName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Name<span class="text-red-500">*</span>
                        </label>
                        <p v-show="firstNameNotValid" class="absolute text-sm text-red-500 ml-2">Name size must be
                            beween 1 and 100</p>
                    </div>

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

        <div class="l-w-824 h-px bg-black mx-auto" v-show="fullName !== '' && email !== ''"></div>

        <!-- Step 3 - Select time period -->
        <div class="l-w-824 h-full mx-auto flex m-12 mb-12" v-show="fullName !== '' && email !== ''">
            <div class="w-52 h-24 place-items-center">
                <h2>Step 3<span class="text-red-500">*</span></h2>
                <p class="l-text-xxs">Select time period.</p>

            </div>
            <div class="l-w-612 h-full grid gap-6">

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

                <!-- Box - Time selector -->
                <div class=" h-48 l-w-612 grid grid-cols-5 gap-6 mt-6 overflow-y-auto">

                    <button v-for="(time, index) in TimePeriod" :key="index" @click="startTime = index"
                        :class="['h-8 text-sm duration-150 bg-white', startTime == index ? 'bg-blue-500 text-white border-0' : '', isOverlap(index) ? ' text-gray-300' : 'hover:bg-blue-500 hover:text-white border border-gray-300 hover:border-none']"
                        :disabled="isOverlap(index)">
                        {{ `${time.startTime.split(':')[0]}:${time.startTime.split(':')[1]}` }} - {{
                                `${time.endTime.split(':')[0]}:${time.endTime.split(':')[1]}`
                        }}
                    </button>
                </div>

            </div>
        </div>

        <!-- Alert - If booked error -->
        <div v-if="ERROR"
            class="alert l-w-824 mx-auto duration-150 mb-12 flex p-4 mt-2 pb-4 text-sm text-red-700 bg-red-100 rounded place-items-center"
            role="alert">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true"
                role="img" class="iconify iconify--material-symbols mr-2" width="24" height="24"
                preserveAspectRatio="xMidYMid meet" viewBox="0 0 24 24">
                <path fill="currentColor"
                    d="M12 22q-2.075 0-3.9-.788q-1.825-.787-3.175-2.137q-1.35-1.35-2.137-3.175Q2 14.075 2 12t.788-3.9q.787-1.825 2.137-3.175q1.35-1.35 3.175-2.138Q9.925 2 12 2t3.9.787q1.825.788 3.175 2.138q1.35 1.35 2.137 3.175Q22 9.925 22 12t-.788 3.9q-.787 1.825-2.137 3.175q-1.35 1.35-3.175 2.137Q14.075 22 12 22Zm-1-9h2V7h-2Zm1 4q.425 0 .713-.288Q13 16.425 13 16t-.287-.713Q12.425 15 12 15t-.712.287Q11 15.575 11 16t.288.712Q11.575 17 12 17Z">
                </path>
            </svg>
            <div>
                <span class="font-medium">Error! </span> Somthing want wrong, Please try again.
            </div>
        </div>
        <!-- Button - Submition -->
        <div class="l-w-824 h-12 mx-auto">
            <button
                :class="['w-full h-full text-white duration-150', isAllvalid ? 'bg-slate-200' : 'l-bg-navi hover:bg-slate-800']"
                :disabled="isAllvalid" @click="submit(fullName, email, dateTime, clinicId, note)">Submit</button>
        </div>

        <!-- Alert - If booked succesfully -->
        <div v-if="SUCCESFUL"
            class="alert bg-black/30 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 w-full h-full">
            <div class="relative p-4 w-full h-full grid place-items-center justify-center">
                <div class="relative bg-white shadow l-w-520 h-72 grid place-items-center">
                    <div class="grid place-items-center gap-6">
                        <div class="grid justify-center">
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                aria-hidden="true" role="img" class="iconify iconify--clarity text-emerald-500"
                                width="96" height="96" preserveAspectRatio="xMidYMid meet" viewBox="0 0 36 36">
                                <path fill="currentColor"
                                    d="M18 2a16 16 0 1 0 16 16A16 16 0 0 0 18 2Zm10.45 10.63L15.31 25.76L7.55 18a1.4 1.4 0 0 1 2-2l5.78 5.78l11.14-11.13a1.4 1.4 0 1 1 2 2Z"
                                    class="clr-i-solid clr-i-solid-path-1"></path>
                                <path fill="none" d="M0 0h36v36H0z"></path>
                            </svg>
                        </div>
                        <div class="text-center">
                            <h2 class="text-xl font-semibold text-emerald-700 mb-2">Booked succesful!</h2>
                            <p class="text-md l-color-gray-300">your information is already submited.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity"
import { computed, onBeforeMount, onBeforeUpdate, registerRuntimeCompiler } from "@vue/runtime-core";
import { getAllCategory, createEvent, getEventByCatAndDate } from '../services/FetchServices.js'
import { useRoute, useRouter } from "vue-router";



// HOOK
onBeforeMount(async () => {
    const res = await getAllCategory()
    clinics.value = res // All category
})

const getTime = computed(async () => {
    const temp = await getEventByCatAndDate(clinicId.value, selectDate.value)
    return temp // All of booked date time in category
})

onBeforeUpdate(async () => {
    TimeBooked.value = await getTime.value
})

// ATTIBUTE 
const myRouter = useRouter()
const clinics = ref([])
const clinicId = ref(0)
const clinicIndex = ref()
const fullName = ref('')
const email = ref('')
const note = ref('')
const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}` // Today date time
})
const selectDate = ref('')
const TimePeriod = ref([])
const TimeBooked = ref([])
const TimePeriodWithDate = ref([])
const TimeBookedWithDate = computed(() => {
    const BOOKED_DATE = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        BOOKED_DATE.value.push({
            startTime: new Date(TimeBooked.value[i].eventStartTime),
            endTime: new Date(TimeBooked.value[i].eventEndTime)
        })
    }
    return BOOKED_DATE.value // All of booked time with Date time 
})
const startTime = ref(-1)
const dateTime = computed(() => {
    return new Date(`${selectDate.value}T${TimePeriod.value[startTime.value].startTime}`)
})



// FUNCTION 
// CREATE - Event
const SUCCESFUL = ref(false)
const ERROR = ref(false)
const submit = async (name, mail, start, categoryId, notes) => {
    let status = await createEvent(name, mail, start, categoryId, notes)
    if (status == 500 || status == 400) {
        ERROR.value = true
        SUCCESFUL.value = false
        setTimeout(function () {
            ERROR.value = false
        }, 4000);
    } else {
        SUCCESFUL.value = true
        setTimeout(function () {
            myRouter.push({
                name: 'Home'
            })
        }, 2000);
    }
}

// GET - All TimeBooked start time
const allEventStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        bookedStartTime.value.push(new Date(TimeBooked.value[i].eventStartTime).toLocaleTimeString('th-TH'))
    }
    return bookedStartTime
})

// CHECK - Overlap validation
const isOverlap = (index) => {
    let start = new Date(new Date(currentDate.value).getFullYear(), new Date(currentDate.value).getMonth(), new Date(currentDate.value).getDate(), TimePeriod.value[index].startTime.split(":")[0], TimePeriod.value[index].startTime.split(":")[1]).getTime();
    let cur = new Date().getTime()

    let START_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), TimePeriod.value[index].startTime.split(":")[0], TimePeriod.value[index].startTime.split(":")[1])
    let END_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), TimePeriod.value[index].endTime.split(":")[0], TimePeriod.value[index].endTime.split(":")[1])

    // CHECK - Is FUTURE
    if (
        allEventStartTime.value.value.includes(TimePeriod.value[index].startTime) || // Is all events start time contain a time period
        start < cur && new Date(selectDate.value).getDate() == new Date(currentDate.value).getDate()) // Is time period is not a future
        return true

    // CHECK - Is OVERLAP
    let overLap = false
    TimeBookedWithDate.value.forEach(e => {
        if (
            (e.startTime < START_TIME && e.endTime > END_TIME) || // OUT SIDE
            (e.startTime > START_TIME && e.endTime < END_TIME) || // IN SIDE
            (e.startTime > START_TIME && e.startTime < END_TIME) || // START_TIME BETWEEN
            (e.endTime > START_TIME && e.endTime < END_TIME) // END_TIME BETWEEN
        ) {
            overLap = true
        }
    })
    return overLap
}

// CREATE - Time period
const MAX = 1440;
const BREAK = 5;
const CATE_DURATION = computed(() => clinics.value[clinicIndex.value].eventCategoryDuration);
const computeTimePeriod = async () => {
    if (!clinicId.value && selectDate.value == '' || !clinicId.value && selectDate.value !== '' || clinicId.value && selectDate.value == '') { }
    else {
        TimePeriod.value = []   //Time period only locale time
        TimePeriodWithDate.value = []   //Time period with date time
        let init = new Date(selectDate.value);
        init.setHours(0);
        init.setMinutes(0);
        init.setSeconds(0);

        let i = 0
        while (i + CATE_DURATION.value < MAX) {
            let start = new Date(init);
            let plusMinutes = start.getMinutes() + CATE_DURATION.value;
            let end = new Date(start);
            end.setMinutes(plusMinutes);
            TimePeriod.value.push({ startTime: start.toLocaleTimeString("th-TH"), endTime: end.toLocaleTimeString("th-TH") })
            TimePeriodWithDate.value.push({ startTime: start, endTime: end })
            init = new Date(end);
            init.setMinutes(end.getMinutes() + BREAK)
            i += (CATE_DURATION.value + BREAK)
        }
    }
}

// CHECK - Is all validation
const isAllvalid = computed(() => {
    if (wrongEmail.value || firstNameNotValid.value) {
        return true
    }
    if (email.value.length === 0 || fullName.value.length === 0 || selectDate.value.length === 0 || startTime.value == -1) {
        return true
    }
    return false
})

// CHECK - Email validation
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

// CHECK - Name validation
const firstNameNotValid = ref(false)
const isFirstNameValid = () => {
    if (fullName.value.length == 0 || fullName.value.length > 100) {
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