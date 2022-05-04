<template>
    <div class="grid justify-center">
        <div class="flex text-base mt-24 mb-16">
            <router-link :to="{ name: 'Booking' }" class="l-color-blue underline">Booking</router-link>
            <p> > Enter you infomation</p>
        </div>
        <h1 class="text-7xl font-bold mb-24">Enter your information</h1>
        <div class="l-w-824 grid grid-cols-2 gap-6 space-y-4 mb-24">
            <div class="">
                <h2 class="l-color-gray-300 text-base">Clinic Selected</h2>
                <select class="l-w-400 h-12 l-bg-gray">
                    <option value="Front-end">Front-end</option>
                    <option value="Back-end">Back-end</option>
                    <option value="Database">Database</option>
                    <option value="DevOps">DevOps</option>
                    <option value="PM">PM</option>
                </select>
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base">Duration</h2>
                <input type="text" class="l-w-400 h-12 bg-white pl-6" :disabled="true" />
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base">Fisrt name</h2>
                <input type="text" class="l-w-400 h-12 l-bg-gray pl-2" />
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base">Last name</h2>
                <input type="text" class="l-w-400 h-12 l-bg-gray pl-2" />
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Email</h2>
                <input type="text" class="l-w-824 h-12 l-bg-gray pl-2" />
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base">Start Time</h2>
                <input type="text" class="l-w-400 h-12 l-bg-gray pl-2" />
            </div>
            <div>
                <h2 class="l-color-gray-300 text-base" >End Time</h2>
                <input type="text" class="l-w-400 h-12 bg-white pl-6" :disabled="true"/>
            </div>
            <div class="col-span-2">
                <h2 class="l-color-gray-300 text-base">Note (Optional)</h2>
                <input type="text" class="l-w-824 l-bg-gray pb-24 pl-2" />
            </div>
        </div>
        <button class="mb-24 l-bg-blue l-w-824 h-12 font-xl text-white">Submit</button>
    </div>
</template>
 
<script setup>

</script>
 
<style>
</style>