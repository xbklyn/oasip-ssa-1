<template>
  <div>
    <div class="header w-srceen h-screen py-24 l-color-navi">
      <p class="text-center font-light">Welcome to</p>
      <h1 class="text-8xl font-medium text-center leading-relaxed mb-6">
        Online Appointment<br>
        Sevice for Integrated<br>
        Project Clinic</h1>

      <p class="l-w-824 text-center mx-auto">
        Website for booking a teacher's consultation.</p>
      <router-link :to="{ name: 'Booking' }" class="flex place-items-center justify-center m-16">
        <button
          class="hover:scale-110 duration-700 booking w-36 h-12 bg-white font-light grid place-items-center border border-green-400  text-green-600 hover:border-green-200 hover:text-white hover:border duration-50">
          <p>Book now</p>
        </button>
      </router-link>
      <div class="w-10 h-10 grid justify-center mx-auto animate-bounce">
        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true" role="img"
          class="" width="32" height="32" preserveAspectRatio="xMidYMid meet" viewBox="0 0 24 24">
          <path fill="currentColor"
            d="M12 13L6 7l1.4-1.4l4.6 4.575L16.6 5.6L18 7Zm0 6l-6-6l1.4-1.4l4.6 4.575l4.6-4.575L18 13Z"></path>
        </svg>
      </div>
      <!-- <div class="box absolute w-1 h-5 bg-yellow-400 top-72 right-24"></div>
            <div class="box absolute w-5 h-5 bg-blue-400 top-76 left-36"></div>
            <div class="box absolute w-2 h-8 bg-red-400 top-64 right-72"></div>
            <div class="box absolute w-8 h-8 text-black text-3xl top-76 left-48">X</div>
            <div class="box absolute w-1 h-5 bg-purple-400 top-36 right-48"></div>
            <div class="box absolute w-1 h-5 bg-green-400 top-76 left-60"></div> -->

      <!-- Dash board -->
      <div class="grid grid-cols-4 l-w-full gap-12 mt-12 mx-auto">

        <div class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-purple-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">All Booking</p>
            <p class="font-bold text-4xl text-purple-500">
              {{ AllEventsData.length }} <span class="text-xl font-semibold">Event<span class="text-xs">
                  (s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">Upcoming events</p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countUpcoming.length }} <span class="text-xl font-semibold">Event<span
                  class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-red-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">On going events</p>
            <p class="font-bold text-4xl text-red-500">
              {{ countToday.length }} <span class="text-xl font-semibold">Event<span class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

        <div class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out">
          <div class="absolute w-2 h-full bg-green-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base l-color-navi mb-4">Already ended</p>
            <p class="font-bold text-4xl text-green-500">
              {{ countComplete.length }} <span class="text-xl font-semibold">Event<span
                  class="text-xs">(s)</span></span>
            </p>
          </div>
        </div>

      </div>
    </div>
    <!-- clinic -->
    <div>
      <div class="l-bg-gray w-full">
        <div class="max-w-7xl mx-auto px-4">
          <div class="max-w-5xl mx-auto py-16">
            <h2 class="font-bold text-5xl text-gray-900 pt-12 mb-12">Clinics</h2>
            <div>
              <div class="grid grid-cols-2 gap-6 mt-6">
                <div v-for="(clinic, index) in AllCategory" :key="index" class="group relative">
                  <div class="relative w-full h-80 bg-white overflow-hidden group-hover:opacity-75">
                    <img :src="clinicImages[index].img" class="w-full h-full object-center object-cover" />
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
    <!-- member -->
    <div class="bg-black w-full ">
      <div class="grid grid-cols-2 text-white mx-auto max-w-7xl">
        <div class="grid grid-cols-1">
          <div>
            <p class="text-white text-2xl font-light pt-24">“มังค์กี้กระป๋องกระแป๋งหำหดตดไหม้”</p>
          </div>
          <div class="pt-96">
            <h1 class="text-2xl font-semibold ">SSA-1 Team</h1>
            <h2 class="text-base font-light bottom-2 pt-2">Adviser: Dr. Sunisa Sathapornvajana</h2>
          </div>
        </div>
        <div>
          <div class="grid grid-cols-1 mt-64 mb-16 pl-24 ">
                <div class="flex">
                  <div class="w-24 h-24 inline-block">
                    <img class="container h-24 rounded-full" src="../assets/profile/std-026.jpeg" alt="" width="96">
                  </div>
                  <div class="ml-6">     
                    <p class="text-xs font-semibold">63130500026</p>
                    <h1 class="text-2xl font-semibold">Chotiwit Souyan</h1>
                    <a href="https://github.com/xbklyn" target="blank" class="flex mt-2"><img src="../assets/component/gitHub.png" width="24"><span class="ml-4">xbklyn</span></a>
                  </div>
              </div>
              <div class="flex mt-24">
                  <div class="w-24 h-24 inline-block">
                    <img class="container h-24 rounded-full" src="../assets/profile/std-034.jpeg" alt="" width="96">
                  </div>
                  <div class="ml-6">
                  <p class="text-xs font-semibold">63130500034</p>
                  <h1 class="text-2xl font-semibold">Nuttida Meeboon</h1>
                  <a href="https://github.com/NUTTIDA-donut" target="blank" class="flex mt-2"><img src="../assets/component/gitHub.png" width="24"><span class="ml-4">NUTTIDA-donut</span></a>
                </div>
              </div>
              <div>
                  <div class="flex mt-24">
                  <div class="w-24 h-24 inline-block">
                    <img class="container h-24 rounded-full" src="../assets/profile/std-065.png" alt="" width="96">
                  </div>
                  <div class="ml-6">
                  <p class="text-xs font-semibold">63130500065</p>
                  <h1 class="text-2xl font-semibold">Narawich Kumpusa</h1>
                  <a href="https://github.com/catintheboxx" target="blank" class="flex mt-2"><img src="../assets/component/gitHub.png" width="24"><span class="ml-4">catintheboxx</span></a>
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
import { getAllEvents, getAllCategory } from "../services/FetchServices.js";
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
    img: "/ssa1/images/clinicImages/pic1.png",
  },
  {
    img: "/ssa1/images/clinicImages/pic2.png",
  },
  {
    img: "/ssa1/images/clinicImages/pic3.png",
  },
  {
    img: "/ssa1/images/clinicImages/pic4.png",
  },
  {
    img: "/ssa1/images/clinicImages/pic5.png",
  },
];
</script>
 
<style>
/* .header {
  background: linear-gradient(45deg, #4848ff,#4848ff,#783eff,#783eff, #a940ff,#a940ff,#783eff,#783eff, #4848ff,#4848ff);
  background-size: 1000% 1000%;
  animation: gradient 8s ease infinite;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
} */

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

.booking {
  background: linear-gradient(-90deg, #ffffff, #ffffff, #36be00), #36be00;
  background-size: 400%;
  background-position: right;
}

.booking:hover {
  animation: gradient 0.5s ease forwards;
}

@keyframes gradient {
  0% {
    background-position: right;

  }

  100% {
    background-position: left;
  }
}
</style>
