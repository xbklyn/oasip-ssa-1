<template>
    <div>
        <div class="relative l-w-full l-h-1176 mx-auto my-24">
            <p class="text-center">Welcome to</p>
            <h1 class="text-8xl font-light text-center leading-relaxed">
                Online Appointment<br>
                Sevice for Integrated<br>
                Project Clinic</h1>

            <!-- <div class="box absolute w-1 h-5 bg-yellow-400 top-72 right-24"></div>
            <div class="box absolute w-5 h-5 bg-blue-400 top-76 left-36"></div>
            <div class="box absolute w-2 h-8 bg-red-400 top-76 right-72"></div>
            <div class="box absolute w-8 h-8 text-black text-3xl top-76 left-48">X</div>
            <div class="box absolute w-1 h-5 bg-purple-400 top-36 right-48"></div>
            <div class="box absolute w-1 h-5 bg-green-400 top-76 left-60"></div> -->

               <!-- Dash board -->
      <div class="grid grid-cols-4 w-full gap-6 mt-32">
        <div class="bg-white drop-shadow-md flex">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base text-gray-100 mb-4">All Booking</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ AllEventsData.length }}
            </p>
          </div>
        </div>
        <div class="bg-white drop-shadow-md flex">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base text-gray-100 mb-4">Upcoming events</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countUpcoming.length }}
            </p>
          </div>
        </div>
        <div class="bg-white drop-shadow-md flex">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base text-gray-100 mb-4">Today event</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countToday.length }}
            </p>
          </div>
        </div>
        <div class="bg-white drop-shadow-md flex">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base text-gray-100 mb-4">Already ended</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countComplete.length }}
            </p>
          </div>
        </div>
      </div>
   
        </div>
    </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity";
import { computed, onBeforeMount } from "@vue/runtime-core";
import { getAllEvents } from "../services/FetchServices.js";
onBeforeMount(async () => {
  const res = await getAllEvents();
  console.log(res);
  AllEventsData.value = res;
});

const AllEventsData = ref([]);

const countUpcoming = computed(() =>
  AllEventsData.value.filter(
    (event) =>
      event.statusName.toLowerCase() == "upcoming" ||
      event.statusName.toLowerCase() == "ongoing"
  )
);
const countToday = computed(() =>
  AllEventsData.value.filter(
    (event) => event.statusName.toLowerCase() == "ongoing"
  )
);
const countComplete = computed(() =>
  AllEventsData.value.filter(
    (event) => event.statusName.toLowerCase() == "completed"
  )
);
</script>
 
<style>
.box {
    animation: cloudy 2s infinite ease-in-out;
    animation-iteration-count: infinite;
}

@keyframes cloudy {
    from {
        transform: rotate(0deg);
        bottom: 0%;
    }
    50%{
        bottom: 10%;
    }

    to {
        transform: rotate(360deg);
        bottom: 0%;
    }
}
</style>