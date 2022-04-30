<template>
    <div>
        <h1 class="font-bold text-center text-4xl l-color-navi mt-24">All Schedule</h1>
        <div class="flex justify-center text-xs">
            <div class="flex justify-center w-8/12 h-6 my-24 space-x-3">
                <div class="" v-for="value, index in catagory" :key="index">
                    <div v-if="value.catagory == 'all'"
                        :class="['grid justify-center place-items-center w-9 h-6 l-color-navi rounded-md', this.$route.name == 'Scheduled' ? 'bg-blue-400 text-white' : '']">
                        <router-link :to="{ name: 'Scheduled', params: { catagory: value.catagory } }">
                            {{ value.name }}
                            </router-link>
                    </div>
                    <div v-else-if="value.catagory == 'project-management'"
                        class="grid justify-center place-items-center w-36 h-6 l-color-navi rounded-md hover:">
                        <router-link :to="{ name: 'Scheduled', params: { catagory: value.catagory } }">
                            <p >{{ value.name }}</p>
                        </router-link>
                    </div>
                    <div v-else class="grid justify-center place-items-center w-20 h-6 l-color-navi rounded-md">
                        <router-link :to="{ name: 'Scheduled', params: { catagory: value.catagory } } ">
                            <p @click="$emit('filter')">{{ value.name }}</p>
                        </router-link>
                    </div>
                </div>
                <input class="w-48 h-6 rounded-md l-bg-gray px-4 border border-blue-400" type="text"
                    placeholder="Search">
            </div>
        </div>
    </div>
    
</template>
 
<script setup>

defineEmits([
    'filter'
])

const catagory = [
    {
        name: "All",
        catagory: "all"
    },
    {
        name: "Upcoming",
        catagory: "upcoming"
    },
    {
        name: "Front-end",
        catagory: "front-end"
    },
    {
        name: "Back-end",
        catagory: "back-end"
    },
    {
        name: "Database",
        catagory: "Database"
    },
    {
        name: "DevOps",
        catagory: "devops"
    },
    {
        name: "Project Management",
        catagory: "project-management"
    }
]
</script>
 
<style>
</style>