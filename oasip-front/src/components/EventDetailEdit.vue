<template>
    <div>
        <!-- Step 1 - Select clinic -->
        <div class="l-w-824 h-36 mx-auto mt-24 grid justify-center">
            <div class="l-w-612">
                <h2 class="text-2xl mb-6"><span class="font-bold"> Step 1 </span>- Choose clinic</h2>
                <div class="grid grid-cols-2 place-items-center">
                    <div class="justify-self-start">
                        <input checked disabled :id="data.eventCategoryName" type="radio"
                            :value='data.eventCategoryName' name="eventCategory"
                            class="cursor-not-allowed w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
                        <label :for="data.eventCategoryName" class="ml-2 text-sm font-light">
                            {{ data.eventCategoryName }}
                        </label>
                    </div>
                    <p class="justify-self-start l-text-xxs font-semibold ml-3">{{ data.eventCategoryDuration }} Mins
                    </p>
                </div>
            </div>

        </div>
        <div class="l-w-612 h-px bg-black mx-auto"></div>

        <!-- Step 2 - Fill infomation -->
        <div class="l-w-824 h-70 mx-auto grid justify-center m-12">
            <div class="">
                <h2 class="text-2xl mb-6"><span class="font-bold"> Step 2 </span>- Fill infomation</h2>
            </div>
            <div class="space-y-6">

                <!-- Input - Email -->
                <div class="relative">
                    <input disabled type="text" id="email" v-model="currentData.email"
                        class="cursor-not-allowed l-w-612 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                        placeholder=" " />
                    <label for="email"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Email
                    </label>
                </div>

                <div class="grid gap-6">

                    <!-- Input - First name -->
                    <div class="relative">
                        <input disabled type="text" id="firstName" v-model="currentData.name"
                            class="cursor-not-allowed l-w-612 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="firstName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Name
                        </label>
                    </div>
                </div>

                <!-- Input - Note -->
                <div class="relative">
                    <textarea type="text" id="note" maxlength="500" v-model="note"
                        class="resize-none l-w-612 h-44 pl-2 pt-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder="Your note..." />
                    <label for="note"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10  bg-white px-2 left-1">
                        Note
                    </label>
                </div>
            </div>
        </div>

        <div class="l-w-612 h-px bg-black mx-auto"></div>

        <!-- Step 3 - Select time period -->
        <div class="l-w-824 h-full mx-auto grid justify-center mt-12">
            <div class="">
                <h2 class="text-2xl mb-6"><span class="font-bold"> Step 3 </span>- Select time period</h2>
            </div>
            <div class="l-w-612 h-full grid grid-cols-2 gap-6">

                <!-- Input - Date -->
                <div class="relative">
                    <input type="date" id="dateTime" v-model="selectDate" :min="currentDate"
                        @change="startTime = timeContain"
                        class="block l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" " />
                    <label for="dateTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Date
                    </label>
                </div>
            </div>

            <!-- Button - Time selector -->
            <div class="flex justify-self-end mt-12">
                <button @click="reset" class="text-blue-500 font-medium flex w-18 h-8 justify-self-end duration-150">
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                        aria-hidden="true" role="img" class="reset iconify iconify--system-uicons mr-1 stroke-1"
                        width="24" height="24" preserveAspectRatio="xMidYMid meet" viewBox="0 0 21 21">
                        <g fill="none" fill-rule="evenodd" stroke="currentColor" stroke-linecap="round"
                            stroke-linejoin="round">
                            <path d="M3.578 6.487A8 8 0 1 1 2.5 10.5"></path>
                            <path d="M7.5 6.5h-4v-4"></path>
                        </g>
                    </svg></button>
            </div>
            <div class="mb-6 h-48 l-w-612 grid grid-cols-5 gap-6 mt-6 overflow-y-auto">

                <button v-for="(time, index) in computeTimePeriod" :key="index" @click="startTime = index"
                    :class="['h-8 text-sm duration-150 bg-white', startTime == index ? 'bg-blue-500 text-white border-0' : '', isOverlap(index) ? ' text-gray-300' : 'hover:bg-blue-500 hover:text-white border border-gray-300 hover:border-none']"
                    :disabled="isOverlap(index)">
                    {{ `${time.startTime.split(':')[0]}:${time.startTime.split(':')[1]}` }} - {{
                            `${time.endTime.split(':')[0]}:${time.endTime.split(':')[1]}`
                    }}
                </button>
            </div>

            <div class="l-w-612 h-px bg-black mx-auto mb-6"></div>
        </div>
        <div v-if="ERROR"
            class="alert l-w-612 mx-auto duration-150 mb-12 flex p-4 mt-2 pb-4 text-sm text-red-700 bg-red-100 rounded place-items-center"
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

        <div class="l-w-612 h-12 mx-auto my-12 ">
            <button @click="$emit('edit', currentData)"
                class="w-full h-full text-white duration-150 bg-emerald-400 hover:bg-emerald-600">Update</button>
        </div>
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
                            <h2 class="text-xl font-semibold text-emerald-700 mb-2">Update succesful!</h2>
                            <p class="text-md l-color-gray-300">your information is already updated.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import { computed, onBeforeMount, onBeforeUpdate, ref } from "@vue/runtime-core"
import { useRoute } from 'vue-router'
import { getEventByCatAndDate } from '../services/FetchServices.js';
const { params } = useRoute()


const prop = defineProps({
    data: {
        type: Object,
        require: false
    },
    duration: {
        type: Number,
        require: false
    },
    SUCCESFUL: {
        type: Boolean,
        require: false
    },
    ERROR: {
        type: Boolean,
        require: false
    }
})

defineEmits(['edit', 'getDate'])

onBeforeMount(async () => {
    TimeBooked.value = await getTime.value
})

const getTime = computed(async () => {
    const temp = await getEventByCatAndDate(prop.data.categoryId, selectDate.value)
    return temp
})

onBeforeUpdate(async () => {
    TimeBooked.value = await getTime.value
})

// DATA - Edited data
const currentData = computed(() => {
    return {
        id: params.id,
        email: prop.data.bookingEmail,
        name: prop.data.bookingName,
        note: note.value,
        time: TimePeriodWithDate.value[startTime.value == -1 ? indexOfTime.value - 1: startTime.value].startTime
    }
})
const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}` // Current date time
})

const continueDate = computed(() => {
    const date = ref(new Date(prop.data.eventStartTime).getFullYear())
    const month = ref((new Date(prop.data.eventStartTime).getMonth() + 1).toString())
    const day = ref(new Date(prop.data.eventStartTime).getDate().toString())
    const hours = ref(new Date(prop.data.eventStartTime).getHours().toString())
    const min = ref(new Date(prop.data.eventStartTime).getMinutes())

    if (month.value.length === 1) {
        month.value = `0${month.value}`
    }
    if (day.value.length === 1) {
        day.value = `0${day.value}`
    }

    return `${date.value}-${month.value}-${day.value}` // Date time from user selected
})
const note = ref(prop.data.eventNotes)
const selectDate = ref(continueDate.value)



const TimeBooked = ref([])

// STATUS - Check succesfully
const SUCCESFUL = computed(() => {
    return prop.SUCCESFUL
})

// STATUS - Check error
const ERROR = computed(() => {
    return prop.ERROR
})



// GET - All only of event start time from time booked
const allEventStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        bookedStartTime.value.push(new Date(TimeBooked.value[i].eventStartTime).toLocaleTimeString('th-TH'))
    }
    return bookedStartTime
})

// GET - All only start time of computeTimePeriod
const allStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < computeTimePeriod.value.length; i++) {
        bookedStartTime.value.push(computeTimePeriod.value[i].startTime)
    }
    return bookedStartTime
})

// FILTER - All only of event start time from time booked with out user time selected
const withoutBooked = computed(() => {
    return allEventStartTime.value.value.filter(e => {
        return e !== new Date(prop.data.eventStartTime).toLocaleTimeString('th-TH')
    })
})

// GET - Booked time with date time
const TimeBookedWithDate = computed(() => {
    const BOOKED_DATE = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        BOOKED_DATE.value.push({
            startTime: new Date(TimeBooked.value[i].eventStartTime),
            endTime: new Date(TimeBooked.value[i].eventEndTime)
        })
    }
    return BOOKED_DATE.value
})

// CHECK - Overlap validation
const isOverlap = (index) => {
    let start = new Date(new Date(currentDate.value).getFullYear(), new Date(currentDate.value).getMonth(), new Date(currentDate.value).getDate(), computeTimePeriod.value[index].startTime.split(":")[0], computeTimePeriod.value[index].startTime.split(":")[1]).getTime();
    let cur = new Date().getTime()

    let START_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), computeTimePeriod.value[index].startTime.split(":")[0], computeTimePeriod.value[index].startTime.split(":")[1])
    let END_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), computeTimePeriod.value[index].endTime.split(":")[0], computeTimePeriod.value[index].endTime.split(":")[1])


    if (
        // CHECK - Is FUTURE
        withoutBooked.value.includes(computeTimePeriod.value[index].startTime) ||
        start < cur && new Date(selectDate.value).getDate() == new Date(currentDate.value).getDate())
        return true

    let overLap = false
    TimeBookedWithDate.value.forEach(e => {

        // CHECK OVERLAP
        if (
            (e.startTime < START_TIME && e.endTime > END_TIME) || // OUT SIDE
            (e.startTime > START_TIME && e.endTime < END_TIME) || // IN SIDE
            (e.startTime >= START_TIME && e.startTime <= END_TIME) || // START_TIME BETWEEN
            (e.endTime >= START_TIME && e.endTime <= END_TIME) // END_TIME BETWEEN
        ) {
            overLap = true
        }
    })
    return overLap
}

// GET - All time period
const MAX = 1440;
const BREAK = 5;
const CATE_DURATION = computed(() => parseInt(prop.duration));
const TimePeriodWithDate = ref([])
const computeTimePeriod = computed(() => {
    if (selectDate.value == '') { }
    const TimePeriod = ref([])
    TimePeriodWithDate.value = []
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
        TimePeriod.value.push({ startTime: start.toLocaleTimeString('th-TH'), endTime: end.toLocaleTimeString('th-TH') })
        TimePeriodWithDate.value.push({ startTime: start, endTime: end })
        init = new Date(end);
        init.setMinutes(end.getMinutes() + BREAK)
        i += CATE_DURATION.value + BREAK
    }
    return TimePeriod.value
})

// GET - Index of booked time between computeTimePeriod
const indexOfTime = computed(() => {
    for (let i = 0; i < computeTimePeriod.value.length; i++) {
        if (computeTimePeriod.value[i].startTime > new Date(prop.data.eventStartTime).toLocaleTimeString('th-TH')) {
            return i
        }
    }
})
const timeContain = computed(() => {
    return allStartTime.value.value.indexOf(new Date(prop.data.eventStartTime).toLocaleTimeString('th-TH'))
})
const startTime = ref(timeContain.value == -1 ? indexOfTime.value - 1 : timeContain.value)


// RESET - Discard all selected
const reset = () => {
    return startTime.value = timeContain.value == -1 ? indexOfTime.value - 1 : timeContain.value
}
</script>
 
<style>
.reset {
    transition: transform 0.5s ease-in-out;
}

.reset:active {
    transition: transform 1s ease-in-out;
    animation: rotation 0.1s ease-in-out;
}

@keyframes rotation {
    from {
        transform: rotate(0deg);
    }

    to {
        transform: rotate(-360deg);
    }
}
</style>