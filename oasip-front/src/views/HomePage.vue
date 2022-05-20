<template>
  <div>
    <div class="l-w-full l-h-1176 mx-auto my-24">
      <p class="text-center">Welcome to</p>
      <h1 class="text-8xl font-medium text-center leading-relaxed">
        Online Appointment<br>
        Sevice for Integrated<br>
        Project Clinic</h1>

      <!-- <div class="box absolute w-1 h-5 bg-yellow-400 top-72 right-24"></div>
            <div class="box absolute w-5 h-5 bg-blue-400 top-76 left-36"></div>
            <div class="box absolute w-2 h-8 bg-red-400 top-64 right-72"></div>
            <div class="box absolute w-8 h-8 text-black text-3xl top-76 left-48">X</div>
            <div class="box absolute w-1 h-5 bg-purple-400 top-36 right-48"></div>
            <div class="box absolute w-1 h-5 bg-green-400 top-76 left-60"></div> -->

      <!-- Dash board -->
      <div class="grid grid-cols-4 w-full gap-6 mt-32">

        <div class="bg-white drop-shadow-lg flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-purple-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">All Booking</p>
            <p class="font-bold text-4xl text-purple-500">
              {{ AllEventsData.length }} <span class="text-xl font-semibold">Event<span class="text-xs"> (s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-lg flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">Upcoming events</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countUpcoming.length }} <span class="text-xl font-semibold">Event<span class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-lg flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-red-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">On going events</p>
            <p class="font-bold text-4xl text-red-500">
              {{ countToday.length }} <span class="text-xl font-semibold">Event<span class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-lg flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-green-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">Already ended</p>
            <p class="font-bold text-4xl text-green-500">
              {{ countComplete.length }} <span class="text-xl font-semibold">Event<span class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

      </div>
    </div>
      <!-- clinic -->
    <div>
      <div class="bg-gray-100">
        <div class="max-w-7xl mx-auto px-4">
          <div class="max-w-5xl mx-auto py-16">
            <h2 class="font-bold text-5xl text-gray-900">Clinics</h2>
            <div>
              <div class="grid grid-cols-2 gap-6 mt-6">
                <div
                  v-for="(clinic, index) in AllCategory"
                  class="group relative"
                >
                  <div
                    class="relative w-full h-80 bg-white overflow-hidden group-hover:opacity-75"
                  >
                    <img
                      :src="clinicImages[index].img"
                      class="w-full h-full object-center object-cover"
                    />
                  </div>
                  <h3 class="mt-6 text-sm text-gray-500">
                    <span class="absolute inset-0" />
                    {{ clinic.eventCategoryName }}
                  </h3>
                  <p class="text-base font-semibold text-gray-900">
                    {{ clinic.eventCategoryDescription }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
 
<script setup>
import { ref } from "@vue/reactivity";
import { computed, onBeforeMount } from "@vue/runtime-core";
import { getAllEvents , getAllCategory } from "../services/FetchServices.js";
onBeforeMount(async () => {
  const res = await getAllEvents();
  const temp = await getAllCategory();
  AllEventsData.value = res;
  AllCategory.value = temp;
});

const AllEventsData = ref([]);
const AllCategory = ref([]);

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

const clinicImages = [
  {
    img: "/images/clinicImages/pic1.png",
  },
  {
    img: "/images/clinicImages/pic2.png",
  },
  {
    img: "/images/clinicImages/pic3.png",
  },
  {
    img: "/images/clinicImages/pic4.png",
  },
  {
    img: "/images/clinicImages/pic5.png",
  },
];
</script>
 
<style>
.box {
  animation: cloudy 30s infinite ease-in-out;
  animation-iteration-count: infinite;
}

@keyframes cloudy {
  from {
    transform: rotate(0deg);
    bottom: 0%;
    opacity: 0;
    animation-duration: 2s;
  }

  50% {
    bottom: 50%;
    opacity: 50;
        animation-duration: 2s;

  }

  to {
    transform: rotate(360deg);
    bottom: 0%;
    opacity: 0;
    animation-duration: 2s;
  }
}
</style>
