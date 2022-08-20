<template>
  <div>
    <!-- HEADER -->
    <div class="w-srceen h-full py-24 l-color-navi">
      <p class="text-center font-light">Welcome to</p>
      <h1
        class="text-3xl md:text-5xl lg:text-6xl xl:text-8xl font-medium text-center leading-relaxed mb-6"
      >
        Online Appointment<br />
        Sevice for Integrated<br />
        Project Clinic
        <p>{{ formatDate(time) }}</p>
      </h1>

      <p class="text-center mx-auto text-xs md:text-base">
        Website for booking a teacher's consultation.
      </p>
      <router-link
        :to="{ name: 'Booking' }"
        class="flex place-items-center justify-center m-12 w-36 h-12 mx-auto"
      >
        <button
          class="hover:scale-110 hover:border-none transition duration-1000 booking w-36 h-12 bg-white font-light grid place-items-center border border-green-400 text-green-600 hover:border-green-200 hover:text-white hover:border duration-50"
        >
          <p>Book now</p>
        </button>
      </router-link>
      <div class="w-10 h-10 grid justify-center mx-auto animate-bounce">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          aria-hidden="true"
          role="img"
          class=""
          width="32"
          height="32"
          preserveAspectRatio="xMidYMid meet"
          viewBox="0 0 24 24"
        >
          <path
            fill="currentColor"
            d="M12 13L6 7l1.4-1.4l4.6 4.575L16.6 5.6L18 7Zm0 6l-6-6l1.4-1.4l4.6 4.575l4.6-4.575L18 13Z"
          ></path>
        </svg>
      </div>

      <!-- ELEMENT - Flying -->
      <div class="box6 absolute w-1 h-5 bg-yellow-400"></div>
      <div class="box2 absolute w-2 h-8 bg-red-400"></div>
      <div class="box absolute w-5 h-5 bg-purple-400"></div>
      <div class="box7 absolute w-6 h-6 bg-blue-400"></div>
      <div class="box5 absolute w-6 h-6 border-4 border-fuchsia-300"></div>
      <div
        class="box3 absolute w-3 h-3 rounded-full border-2 border-lime-600"
      ></div>
      <div class="box4 absolute w-2 h-2 bg-orange-300"></div>
      <div class="box8 absolute w-2 h-2 font-bold text-2xl">1</div>
      <div class="box9 absolute w-8 h-2 bg-violet-800"></div>
      <div class="box10 absolute w-2 h-2 rounded-full bg-blue-800"></div>

      <!-- Dash board -->
      <div
        class="grid px-12 md:grid-cols-2 xl:grid-cols-4 xl:l-w-full gap-12 mt-24 mx-auto"
      >
        <div
          class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out"
        >
          <div class="absolute w-2 h-full bg-indigo-400"></div>
          <div class="ml-6 my-4">
            <p class="text-base font-medium text-indigo-400 mb-4">
              All Booking
            </p>
            <p class="font-bold text-4xl text-indigo-400">
              {{ AllEventsData.length }}
              <span class="text-xl font-semibold"
                >Event<span class="text-xs"> (s)</span></span
              >
            </p>
          </div>
        </div>

        <div
          class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out"
        >
          <div class="absolute w-2 h-full bg-blue-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base font-medium text-blue-500 mb-4">
              Upcoming events
            </p>
            <p class="font-bold text-4xl text-blue-500">
              {{ countUpcoming.length }}
              <span class="text-xl font-semibold"
                >Event<span class="text-xs">(s)</span></span
              >
            </p>
          </div>
        </div>

        <div
          class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out"
        >
          <div class="absolute w-2 h-full bg-red-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base font-medium text-red-500 mb-4">
              On going events
            </p>
            <p class="font-bold text-4xl text-red-500">
              {{ countToday.length }}
              <span class="text-xl font-semibold"
                >Event<span class="text-xs">(s)</span></span
              >
            </p>
          </div>
        </div>

        <div
          class="bg-white drop-shadow-md flex hover:scale-105 duration-150 ease-in-out"
        >
          <div class="absolute w-2 h-full bg-lime-500"></div>
          <div class="ml-6 my-4">
            <p class="text-base font-medium text-lime-500 mb-4">Completed</p>
            <p class="font-bold text-4xl text-lime-500">
              {{ countComplete.length }}
              <span class="text-xl font-semibold"
                >Event<span class="text-xs">(s)</span></span
              >
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- Clinics -->
    <div>
      <div class="l-bg-gray w-full pb-24">
        <div class="mx-auto">
          <div class="mx-auto p-16">
            <h2 class="font-bold text-4xl md:mt-24">Clinics</h2>
            <div>
              <div class="grid md:grid-cols-2 xl:grid-cols-3 gap-12 mt-12">
                <div
                  v-for="(clinic, index) in AllCategory"
                  :key="index"
                  class="grid w-full"
                >
                  <div class="duration-150">
                    <img
                      :src="clinicImages[index].img"
                      class="w-full object-center object-cover bg-center"
                    />
                  </div>
                  <h3
                    class="grid grid-cols-2 w-full mt-6 text-sm text-gray-500 mb-2"
                  >
                    {{ clinic.eventCategoryName
                    }}<span class="justify-self-end font-medium text-xs"
                      >Duration {{ clinic.eventCategoryDuration }} Mins.</span
                    >
                  </h3>
                  <p class="text-base font-medium text-gray-900 truncate">
                    {{
                      clinic.eventCategoryDescription.length == 0
                        ? 'No description'
                        : clinic.eventCategoryDescription
                    }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Team member -->
    <div class="bg-black w-full">
      <div class="grid grid-cols-2 text-white mx-auto py-12 px-6">
        <div class="grid grid-rows-5 xl:mt-24 xl:ml-24">
          <div class="row-span-4">
            <p class="text-white text-sm xl:text-xl font-light">
              “มังค์กี้กระป๋องกระแป๋งหำหดตดไหม้”
            </p>
          </div>
          <div class="">
            <h1 class="text-sm xl:text-xl font-light">SSA-1 Team</h1>
            <h2 class="text-xs xl:text-md font-light">
              Adviser | Dr. Sunisa Sathapornvajana
            </h2>
          </div>
        </div>
        <div class="justify-self-end xl:mr-24 grid grid-rows-2 mb-24">
          <div class="grid gap-12 row-start-2">
            <div class="flex">
              <div class="w-16 h-16 xl:w-24 xl:h-24 inline-block">
                <img
                  class="container h-16 xl:h-24 rounded-full"
                  src="../assets/profile/std-026-2.jpeg"
                  alt=""
                  width="96"
                />
              </div>
              <div class="ml-6">
                <p class="text-xs xl:text-xs font-semibold">63130500026</p>
                <h1 class="text-xs xl:text-2xl font-semibold">
                  Chotiwit Souyan
                </h1>
                <a
                  href="https://github.com/xbklyn"
                  target="_blank"
                  class="flex mt-2"
                  ><img
                    src="../assets/component/gitHub.png"
                    width="24"
                    class="hover:scale-110 duration-150"
                  /><span
                    class="grid place-items-center text-xs xl:text-sm ml-2"
                    >xbklyn</span
                  ></a
                >
              </div>
            </div>
            <div class="flex">
              <div class="w-16 h-16 xl:w-24 xl:h-24 inline-block">
                <img
                  class="container h-16 xl:h-24 rounded-full"
                  src="../assets/profile/std-034-2.jpeg"
                  alt=""
                  width="96"
                />
              </div>
              <div class="ml-6">
                <p class="text-xs xl:text-xs font-semibold">63130500034</p>
                <h1 class="text-xs xl:text-2xl font-semibold">
                  Nuttida Meeboon
                </h1>
                <a
                  href="https://github.com/NUTTIDA-donut"
                  target="_blank"
                  class="flex mt-2"
                  ><img
                    src="../assets/component/gitHub.png"
                    width="24"
                    class="hover:scale-110 duration-150"
                  /><span
                    class="grid place-items-center text-xs xl:text-sm ml-2"
                    >NUTTIDA-donut</span
                  ></a
                >
              </div>
            </div>
            <div>
              <div class="flex">
                <div class="w-16 h-16 xl:w-24 xl:h-24 inline-block">
                  <img
                    class="container h-16 xl:h-24 rounded-full"
                    src="../assets/profile/std-065.jpeg"
                    alt=""
                    width="96"
                  />
                </div>
                <div class="ml-6">
                  <p class="text-xs xl:text-xs font-semibold">63130500065</p>
                  <h1 class="text-xs xl:text-2xl font-semibold">
                    Narawich Kumpusa
                  </h1>
                  <a
                    href="https://github.com/catintheboxx"
                    target="_blank"
                    class="flex mt-2"
                    ><img
                      src="../assets/component/gitHub.png"
                      width="24"
                      class="hover:scale-110 duration-150"
                    /><span
                      class="grid place-items-center text-xs xl:text-sm ml-2"
                      >catintheboxx</span
                    ></a
                  >
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
import { ref } from '@vue/reactivity';
import { computed, onBeforeMount, onMounted } from '@vue/runtime-core';
import moment from 'moment';
import { getAllEvents, getAllCategory } from '../services/FetchServices.js';
import { formatDate } from '../services/moment';
// import moment from 'moment';
// import 'moment/locale/th';

onBeforeMount(async () => {
  const res = await getAllEvents();
  const temp = await getAllCategory();
  AllEventsData.value = res;
  AllCategory.value = temp.sort((c) => -1);
});

onMounted(() => {
  setInterval(() => {
    currentTime();
  }, 1000);
});

const AllEventsData = ref([]);
const AllCategory = ref([]);
const time = ref();

const countUpcoming = computed(() =>
  AllEventsData.value.filter(
    (event) =>
      event.statusName.toLowerCase() == 'upcoming' ||
      event.statusName.toLowerCase() == 'ongoing',
  ),
);
const countToday = computed(() =>
  AllEventsData.value.filter(
    (event) => event.statusName.toLowerCase() == 'ongoing',
  ),
);
const countComplete = computed(() =>
  AllEventsData.value.filter(
    (event) => event.statusName.toLowerCase() == 'completed',
  ),
);

const currentTime = () => {
  time.value = moment();
};

const clinicImages = [
  {
    img: '/ssa1/images/clinicImages/pic-5.png',
  },
  {
    img: '/ssa1/images/clinicImages/pic-4.png',
  },
  {
    img: '/ssa1/images/clinicImages/pic-3.png',
  },
  {
    img: '/ssa1/images/clinicImages/pic-2.png',
  },
  {
    img: '/ssa1/images/clinicImages/pic-1.png',
  },
];
</script>

<style>
.header {
  background: linear-gradient(
    45deg,
    #489dff,
    #489dff,
    #783eff,
    #783eff,
    #c640ff,
    #c640ff,
    #783eff,
    #783eff,
    #489dff,
    #489dff
  );
  background-size: 1000% 1000%;
  animation: gradient 24s ease infinite;
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
}

.box {
  top: 80%;
  right: 10%;
  animation: upDown 16s infinite ease-in-out;
  opacity: 0;
}

.box2 {
  top: 60%;
  left: 10%;
  animation: upDown2 16s infinite ease-in-out;
  opacity: 0;
}

.box3 {
  top: 90%;
  right: 40%;
  animation: upDown3 16s infinite ease-in-out;
  opacity: 0;
}

.box4 {
  top: 30%;
  left: 20%;
  animation: upDown4 16s infinite ease-in-out;
  opacity: 0;
}

.box5 {
  top: 75%;
  left: 20%;
  animation: upDown5 16s infinite ease-in-out;
  opacity: 0;
}

.box6 {
  top: 15%;
  right: 10%;
  animation: upDown6 16s infinite ease-in-out;
  opacity: 100;
}

.box7 {
  top: 15%;
  left: 30%;
  animation: upDown7 16s infinite ease-in-out;
  opacity: 100;
}

.box8 {
  top: 75%;
  right: 30%;
  animation: upDown8 16s infinite ease-in-out;
  opacity: 100;
}

.box9 {
  top: 70%;
  right: 10%;
  animation: upDown9 16s infinite ease-in-out;
  opacity: 100;
}

.box10 {
  top: 85%;
  left: 5%;
  animation: upDown10 16s infinite ease-in-out;
  opacity: 100;
}

@keyframes upDown {
  from {
    transform: rotate(0deg);
    top: 30%;
    right: 10%;
    opacity: 0;
  }

  50% {
    top: 50%;
    right: 5%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 30%;
    right: 10%;
    opacity: 0;
  }
}

@keyframes upDown2 {
  from {
    transform: rotate(0deg);
    top: 60%;
    left: 10%;
    opacity: 0;
  }

  50% {
    top: 50%;
    left: 20%;
    bottom: 30%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 60%;
    left: 10%;
    opacity: 0;
  }
}

@keyframes upDown3 {
  from {
    transform: rotate(0deg);
    top: 90%;
    right: 10%;
    opacity: 0;
  }

  50% {
    top: 60%;
    right: 20%;
    bottom: 30%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 90%;
    right: 10%;
    opacity: 0;
  }
}

@keyframes upDown4 {
  from {
    transform: rotate(0deg);
    top: 30%;
    left: 10%;
    opacity: 0;
  }

  50% {
    top: 30%;
    left: 5%;
    opacity: 50;
  }

  to {
    transform: rotate(90deg);
    top: 30%;
    left: 10%;
    opacity: 0;
  }
}

@keyframes upDown5 {
  from {
    transform: rotate(0deg);
    top: 75%;
    left: 20%;
    opacity: 0;
  }

  50% {
    top: 80%;
    left: 25%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 75%;
    left: 20%;
    opacity: 0;
  }
}

@keyframes upDown6 {
  from {
    transform: rotate(0deg);
    top: 15%;
    right: 10%;
    opacity: 0;
  }

  50% {
    top: 20%;
    right: 25%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 15%;
    right: 16%;
    opacity: 0;
  }
}

@keyframes upDown7 {
  from {
    transform: rotate(0deg);
    top: 15%;
    left: 15%;
    opacity: 0;
  }

  50% {
    top: 20%;
    left: 30%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 15%;
    left: 15%;
    opacity: 0;
  }
}

@keyframes upDown8 {
  from {
    transform: rotate(0deg);
    top: 75%;
    right: 30%;
    opacity: 0;
  }

  50% {
    top: 70%;
    right: 25%;
    opacity: 50;
  }

  to {
    transform: rotate(180deg);
    top: 75%;
    right: 30%;
    opacity: 0;
  }
}

@keyframes upDown9 {
  from {
    transform: rotate(0deg);
    top: 70%;
    right: 10%;
    opacity: 0;
  }

  50% {
    top: 80%;
    right: 10%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 70%;
    right: 10%;
    opacity: 0;
  }
}

@keyframes upDown10 {
  from {
    transform: rotate(0deg);
    top: 85%;
    left: 5%;
    opacity: 0;
  }

  50% {
    top: 70%;
    left: 10%;
    opacity: 50;
  }

  to {
    transform: rotate(360deg);
    top: 85%;
    left: 5%;
    opacity: 0;
  }
}

.booking {
  background: linear-gradient(-90deg, #ffffff, #ffffff, #36be00), #36be00;
  background-size: 300%;
  background-position: right;
  transition: background-position 0.5s;
  transition: 0.5s;
}

.booking:hover,
.booking:focus {
  /* animation: gradient 0.5s ease forwards; */
  transition: 0.5s;
  background-position: left;
}

/* @keyframes gradient {
  0% {
    background-position: right;

  }

  100% {
    background-position: left;
  }
} */
</style>
