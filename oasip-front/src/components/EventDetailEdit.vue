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
                    <textarea type="text" id="note" maxlength="500" v-model="currentData.note"
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
                    <input type="date" id="dateTime" :min="currentDate" v-model="dateTime"
                        class="block l-w-294 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" />
                    <label for="dateTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Date
                    </label>
                </div>
                
                <!-- Input - Start time -->
                <div class="relative">
                    <input type="time" id="startTime" v-model="currentData.startTime"
                        class="block l-w-294 h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" " />
                    <label for="startTime"
                        class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1">
                        Start time
                    </label>
                </div>
            </div>
        </div>

        <div class="l-w-612 h-px bg-black mx-auto"></div>

        <div class="l-w-612 h-12 mx-auto my-12">
            <button @click="$emit('edit', currentData)"
                class="w-full h-full bg-green-600 text-white hover:bg-green-700 duration-150">Update</button>
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

defineEmits(['edit'])

const currentDate = computed(() => {
    const date = ref(new Date().getFullYear())
    const month = ref((new Date().getMonth() + 1).toString())
    const day = ref(new Date().getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}`
})

const continueDate = computed(()=>{
    const date = ref(new Date(prop.data.eventDate).getFullYear())
    const month = ref((new Date(prop.data.eventDate).getMonth() + 1).toString())
    const day = ref(new Date(prop.data.eventDate).getDate())
    if (month.value.length === 1) {
        return `${date.value}-0${month.value}-${day.value}`
    }
    return `${date.value}-${month.value}-${day.value}`
})

const dateTime = ref(continueDate.value)

const startTime = ref()

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
        note: prop.data.eventNotes,
        date: dateTime.value,
        startTime: prop.data.eventStartTime
    }
})
</script>
 
<style>
</style>