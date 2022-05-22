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
                        <input disabled type="text" id="firstName" v-model="currentData.firstName"
                            class="cursor-not-allowed l-w-612 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="firstName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            Name
                        </label>
                    </div>

                    <!-- Input - Last name -->
                    <!-- <div class="relative">
                        <input disabled type="text" id="lastName" v-model="currentData.lastName"
                            class="cursor-not-allowed l-w-188 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="lastName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                            Last Name
                        </label>
                    </div> -->

                    <!-- Input - Group -->
                    <!-- <div class="relative">
                        <input disabled type="text" id="group" v-model="currentData.group"
                            class="cursor-not-allowed l-w-188 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="group"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                            Group
                        </label>
                    </div> -->
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
                        aria-hidden="true" role="img" class="reset iconify iconify--system-uicons mr-1 stroke-1" width="24" height="24"
                        preserveAspectRatio="xMidYMid meet" viewBox="0 0 21 21">
                        <g fill="none" fill-rule="evenodd" stroke="currentColor" stroke-linecap="round"
                            stroke-linejoin="round">
                            <path d="M3.578 6.487A8 8 0 1 1 2.5 10.5"></path>
                            <path d="M7.5 6.5h-4v-4"></path>
                        </g>
                    </svg></button>
            </div>
            <div class="l-w-612 grid grid-cols-6 gap-6 mt-3 mb-12">
                <button v-for="(time, index) in computeTimePeriod" :key="index" @click="startTime = index"
                    :class="['h-8 text-sm duration-150 bg-white', startTime == index ? 'bg-blue-500 text-white border-0' : '', isOverlap(index) ? ' text-gray-300' : 'hover:bg-blue-500 hover:text-white border border-gray-300 hover:border-none']"
                    :disabled="isOverlap(index)">
                    {{ time.startTime }}
                </button>
            </div>

            <div class="l-w-612 h-px bg-black mx-auto"></div>
        </div>

        <div class="l-w-612 h-12 mx-auto my-12 ">
            <button @click="$emit('edit', currentData)"
                class="w-full h-full text-white duration-150 bg-emerald-400 hover:bg-emerald-600">Update</button>
        </div>
    </div>
</template>
 
<script setup>
import { computed, onBeforeMount, onBeforeUnmount, onBeforeUpdate, ref } from "@vue/runtime-core"
import { useRoute, useRouter } from 'vue-router'
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
    MAX: {
        type: Number,
        require: false
    },
    BREAK: {
        type: Number,
        require: false
    }
})

defineEmits(['edit', 'getDate'])
onBeforeMount(async () => {
    TimeBooked.value = await getTime.value
})

const currentData = computed(() => {
    const firstName = ref()
    const lastName = ref()
    const group = ref()

    const newDate = computed(() => {
        return new Date(dateTime.value)
    })

    firstName.value = prop.data.bookingName.split(" ")[0]
    lastName.value = prop.data.bookingName.split(" ")[1]
    group.value = prop.data.bookingName.split(" ")[2]

    return {
        id: params.id,
        email: prop.data.bookingEmail,
        firstName: firstName.value,
        lastName: lastName.value,
        group: group.value,
        note: note.value,
        time: TimePeriodWithDate.value[startTime.value].startTime
    }
})

const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}`
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

    return `${date.value}-${month.value}-${day.value}`
})

const allEventStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < TimeBooked.value.length; i++) {
        bookedStartTime.value.push(new Date(TimeBooked.value[i].eventStartTime).toLocaleTimeString('th-TH'))
    }
    return bookedStartTime
})

const allStartTime = computed(() => {
    const bookedStartTime = ref([])
    for (let i = 0; i < computeTimePeriod.value.length; i++) {
        bookedStartTime.value.push(computeTimePeriod.value[i].startTime)
    }
    return bookedStartTime
})

const withoutBooked = computed(() => {
    return allEventStartTime.value.value.filter(e => {
        return e !== new Date(prop.data.eventStartTime).toLocaleTimeString('th-TH')
    })
})

const note = ref(prop.data.eventNotes)

const getTime = computed(async () => {
    const temp = await getEventByCatAndDate(prop.data.categoryId, selectDate.value)
    return temp
})

onBeforeUpdate(async () => {
    TimeBooked.value = await getTime.value
})


const MAX = parseInt(480);
const BREAK = parseInt(5);
const CATE_DURATION = computed(() => parseInt(prop.duration));

// const TimePeriod = ref([])
const selectDate = ref(continueDate.value)

const TimeBooked = ref([])


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

// Check overlap
const isOverlap = (index) => {
    let start = new Date(new Date(currentDate.value).getFullYear(), new Date(currentDate.value).getMonth(), new Date(currentDate.value).getDate(), computeTimePeriod.value[index].startTime.split(":")[0], computeTimePeriod.value[index].startTime.split(":")[1]).getTime();
    let cur = new Date().getTime()

    let START_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), computeTimePeriod.value[index].startTime.split(":")[0], computeTimePeriod.value[index].startTime.split(":")[1])
    let END_TIME = new Date(new Date(selectDate.value).getFullYear(), new Date(selectDate.value).getMonth(), new Date(selectDate.value).getDate(), computeTimePeriod.value[index].endTime.split(":")[0], computeTimePeriod.value[index].endTime.split(":")[1])

    // Check if future
    if (
        withoutBooked.value.includes(computeTimePeriod.value[index].startTime) ||
        start < cur && new Date(selectDate.value).getDate() == new Date(currentDate.value).getDate())
        return true

    // CHECK OVERLAP
    let isOverRapYo = false
    TimeBookedWithDate.value.forEach(e => {

        //OUTSIDE -> INSIDE -> START_TIME BETWEEN -> END_TIME BETWEEN
        if (
            (e.startTime < START_TIME && e.endTime > END_TIME) ||
            (e.startTime > START_TIME && e.endTime < END_TIME) ||
            (e.startTime >= START_TIME && e.startTime <= END_TIME) ||
            (e.endTime >= START_TIME && e.endTime <= END_TIME)
        ) {
            isOverRapYo = true
        }
    })
    return isOverRapYo
}

// Create time period
// const dateTime = computed(() => {
//     return `${selectDate.value}T${computeTimePeriod.value[startTime.value]}`
// })

const TimePeriodWithDate = ref([])
const computeTimePeriod = computed(() => {
    console.log("in time method");
    if (selectDate.value == '') { }
    const TimePeriod = ref([])
    TimePeriodWithDate.value = []
    let init = new Date(selectDate.value);
    init.setHours(8);
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
        i += parseInt(CATE_DURATION.value + BREAK)
        console.log(i);
    }

    return TimePeriod.value
})

const h = computed(() => {
    for (let i = 0; i < TimePeriodWithDate.value.length; i++) {
        if (TimePeriodWithDate.value[i].startTime > new Date(prop.data.eventStartTime)) {
            return i
        }
    }

})
const timeContain = computed(() => {
    return allStartTime.value.value.indexOf(new Date(prop.data.eventStartTime).toLocaleTimeString('th-TH'))
})

const startTime = ref(timeContain.value == -1 ? h.value - 1 : timeContain.value)

const reset = () => {
    return startTime.value = timeContain.value == -1 ? h.value - 1 : timeContain.value
}
</script>
 
<style>
.reset{
    transition: transform 0.5s ease-in-out;
}
.reset:active{
    transition: transform 1s ease-in-out;
    animation:rotation 0.1s ease-in-out;
}

@keyframes rotation {
    from{
        transform: rotate(0deg);
    }
    to{
        transform: rotate(-360deg);
    }
}
</style>