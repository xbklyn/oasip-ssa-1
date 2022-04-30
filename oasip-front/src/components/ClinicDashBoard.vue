<template>
    <div class="grid justify-center mb-24">
        <h1 class="text-4xl font-bold l-color-navi mt-24 mb-8">Clinic</h1>
        <div class="flex space-x-6">
            <div class="grid place-items-center w-80 h-72 bg-black rounded-xl ease-out transition hover:scale-110 drop-shadow-md"
                v-for="value, index in clinics" :key="index">
                <div class="w-64 h-52">
                    <h2 class="font-bold text-white text-2xl">{{ value.name }}</h2>
                    <p class="text-xs text-white">{{ value.description }}</p>
                    <div class="grid justify-center mt-12">
                        <router-link :to="{ name: '' }">
                            <div
                                class="grid place-items-center text-white w-32 h-9 l-bg-blue font-semibold text-xs rounded-md hover:bg-blue-700 ease-out duration-75">
                                Booking now
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<script setup>

const clinics = [
    {
        name: "Front end",
        description: `Lorem ipsum dolorsit amet, consectetur adipiscing elit.Integer etiam nunc, vel lacus volutpat. Id suscipit at
            bibendumnec etiam quis nisi. Id ut nuncornare fusce ipsumtincidunt fermentum. Interdum nulla malesuada nunc in.`
    },
    {
        name: "Back end",
        description: `Lorem ipsum dolorsit amet, consectetur adipiscing elit.Integer etiam nunc, vel lacus volutpat. Id suscipit at
            bibendumnec etiam quis nisi. Id ut nuncornare fusce ipsumtincidunt fermentum. Interdum nulla malesuada nunc in.`
    },
    {
        name: "Database",
        description: `Lorem ipsum dolorsit amet, consectetur adipiscing elit.Integer etiam nunc, vel lacus volutpat. Id suscipit at
            bibendumnec etiam quis nisi. Id ut nuncornare fusce ipsumtincidunt fermentum. Interdum nulla malesuada nunc in.`
    },
    {
        name: "DevOps",
        description: `Lorem ipsum dolorsit amet, consectetur adipiscing elit.Integer etiam nunc, vel lacus volutpat. Id suscipit at
            bibendumnec etiam quis nisi. Id ut nuncornare fusce ipsumtincidunt fermentum. Interdum nulla malesuada nunc in.`
    },
    // {
    //     name: "Project Managemen",
    //     description: `Lorem ipsum dolorsit amet, consectetur adipiscing elit.Integer etiam nunc, vel lacus volutpat. Id suscipit at
    //         bibendumnec etiam quis nisi. Id ut nuncornare fusce ipsumtincidunt fermentum. Interdum nulla malesuada nunc in.`
    // }
]
</script>
 
<style>
</style>