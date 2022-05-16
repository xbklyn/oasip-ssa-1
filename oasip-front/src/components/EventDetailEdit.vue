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
                    <p class="justify-self-start l-text-xxs font-semibold ml-3">{{ data.eventDuration }} Mins</p>
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

                <div class="grid grid-cols-3 gap-6">

                    <!-- Input - First name -->
                    <div class="relative">
                        <input disabled type="text" id="firstName" v-model="currentData.firstName"
                            class="cursor-not-allowed l-w-188 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="firstName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                            First Name
                        </label>
                    </div>

                    <!-- Input - Last name -->
                    <div class="relative">
                        <input disabled type="text" id="lastName" v-model="currentData.lastName"
                            class="cursor-not-allowed l-w-188 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="lastName"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                            Last Name
                        </label>
                    </div>

                    <!-- Input - Group -->
                    <div class="relative">
                        <input disabled type="text" id="group" v-model="currentData.group"
                            class="cursor-not-allowed l-w-188 h-12 pl-2 text-sm text-gray-400 border-2 appearance-none l-bg-gray-100 peer"
                            placeholder=" " />
                        <label for="group"
                            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 left-1">
                            Group
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
        <div class="l-w-824 h-36 mx-auto grid justify-center mt-12">
            <div class="">
                <h2 class="text-2xl mb-6"><span class="font-bold"> Step 3 </span>- Select time period</h2>
            </div>
            <div class="l-w-612 h-24 grid grid-cols-2 gap-6">

                <!-- Input - Date -->
                <div class="relative">
                    <input type="datetime-local" id="dateTime" :min="currentDate" @input="isTimeValid"
                        v-model="startTime"
                        class="block l-w-612 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" />
                    <label for="dateTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Date
                    </label>
                    <p v-show="timeNotValid" class="absolute text-sm text-red-500 ml-2">Time invalid</p>
                </div>

                <!-- Input - Start time -->
                <!-- <div class="relative">
                    <input type="time" id="startTime" @input="isTimeValid"  v-model="startTime"
                        :class="['block l-w-294 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer', timeNotValid ? 'border-red-500 focus:border-red-600' : '']"
                        placeholder=" " />
                    <label for="startTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Start time
                    </label>
                    <p v-show="timeNotValid" class="absolute text-sm text-red-500 ml-2">Time invalid</p>
                </div> -->
            </div>
        </div>
        {{ continueDate }}
        <div class="l-w-612 h-px bg-black mx-auto"></div>

        <div class="l-w-612 h-12 mx-auto my-12">
            <button @click="$emit('edit', currentData)" :disabled="timeNotValid"
                :class="['w-full h-full text-white  duration-150', timeNotValid ? 'bg-slate-300' : 'bg-green-600  hover:bg-green-700']">Update</button>
        </div>
    </div>
</template>
 
<script setup>
import { computed, ref } from "@vue/runtime-core"
import { useRoute, useRouter } from 'vue-router'
const { params } = useRoute()

const prop = defineProps({
    data: {
        type: Object,
        require: false
    }
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
        time: new Date(startTime.value)
    }
})
defineEmits(['edit'])

const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}T00:00`
    }
    return `${date.value}-${month.value}-${day.value}T00:00`
})

const continueDate = computed(() => {
    const date = ref(new Date(prop.data.eventStartTime).getFullYear())
    const month = ref((new Date(prop.data.eventStartTime).getMonth() + 1).toString())
    const day = ref(new Date(prop.data.eventStartTime).getDate())
    const hours = ref(new Date(prop.data.eventStartTime).getHours().toString())
    const min = ref(new Date(prop.data.eventStartTime).getMinutes())

    // if (month.value.length === 1 || hours.value.length === 1) {
    //     return `${date.value}-0${month.value}-${day.value}T0${hours.value}:${min.value}`
    // }

    if (month.value.length === 1) {
        month.value = `0${month.value}`
    }
    if (hours.value.length === 1) {
        hours.value = `0${hours.value}`
    }
    return `${date.value}-${month.value}-${day.value}T${hours.value}:${min.value}`
})

// const dateTime = ref(continueDate.value)
const startTime = ref(continueDate.value)
// const time = computed(() => {
//     if(startTime.value == prop.data.eventStartTime){
//         return prop.data.eventStartTime
//     }
//     return `${startTime.value}:00`
// })

const timeNotValid = ref(false)
const isTimeValid = () => {
    if (new Date(startTime.value) < new Date()) {
        timeNotValid.value = true
        return false
    } else {
        timeNotValid.value = false
        return true
    }
}

const note = ref(prop.data.eventNotes)

</script>
 
<style>
</style>