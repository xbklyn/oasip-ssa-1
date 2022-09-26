<template>
  <div>
    <div
      class="header w-full text-white mx-auto pt-24 pb-36 grid gap-4 justify-center bg-gradient-to-b to-indigo-700 from-sky-300"
    >
      <h1 class="font-bold text-center text-4xl">All Schedule Event</h1>
      <p class="text-center w-96 text-sm">
        This page lists all events from the clinic booking in each clinic. which
        users can filter events by selecting category or status and can also
        filter by date.
      </p>
    </div>

    <!-- Menu - Filter category -->
    <div
      class="h-24 bg-white mx-auto sticky top-0 z-50 grid place-items-center"
    >
      <div
        class="l-w-full h-6 grid grid-cols-3 gap-6 mx-auto place-items-center"
      >
        <!-- FILTER - Categories -->
        <div class="l-w-188 h-6">
          <select
            class="l-w-188 border border-gray-300 text-sm l-color-gray-300 pl-2 py-1 focus:outline-none focus:ring-0 focus:border-blue-500"
            v-model="filter"
          >
            <option disabled selected>Category</option>
            <option value="0">All</option>
            <option
              v-for="(value, index) in allEventCategory"
              :key="index"
              :value="value.categoryId"
            >
              {{ value.eventCategoryName }}
            </option>
          </select>
        </div>

        <!-- FILTER - Status -->
        <div class="flex gap-6">
          <button
            @click="status = ''"
            :class="[
              'grid justify-center place-items-center w-12 h-8 text-sm font-light l-color-navi duration-75',
              status == ''
                ? 'border-b-2 border-blue-500 text-blue-500'
                : 'hover:border-gray-300 hover:border-b-2',
            ]"
          >
            All
          </button>
          <button
            @click="status = 'upcoming'"
            :class="[
              'grid justify-center place-items-center w-28 h-8 text-sm font-light l-color-navi duration-75',
              status == 'upcoming'
                ? 'border-b-2 border-blue-500 text-blue-500'
                : 'hover:border-gray-300 hover:border-b-2',
            ]"
          >
            Upcoming
          </button>
          <button
            @click="status = 'ongoing'"
            :class="[
              'grid justify-center place-items-center w-28 h-8 text-sm font-light l-color-navi duration-75',
              status == 'ongoing'
                ? 'border-b-2 border-blue-500 text-blue-500'
                : 'hover:border-gray-300 hover:border-b-2',
            ]"
          >
            On going
          </button>
          <button
            @click="status = 'completed'"
            :class="[
              'grid justify-center place-items-center w-28 h-8 text-sm font-light l-color-navi duration-75',
              status == 'completed'
                ? 'border-b-2 border-blue-500 text-blue-500'
                : 'hover:border-gray-300 hover:border-b-2',
            ]"
          >
            Completed
          </button>
        </div>

        <!-- FILTER - Date time -->
        <div class="l-w-188 h-6">
          <input
            type="date"
            v-model="selectDate"
            class="pl-2 py-1 l-w-188 border l-color-gray-300 text-sm l-color-gray-300 focus:outline-none focus:ring-0 focus:border-blue-500"
          />
        </div>
      </div>
    </div>

    <!-- LIST - All events scheduled -->
    <div class="bg-slate-50">
      <BaseEvent :data="sortByDate" :status="status" @delete="events($event)" />
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount, computed } from 'vue';
import { getRefreshToken, getAllCategory } from '../services/FetchServices.js';
import BaseEvent from '../components/BaseEvent.vue';

onBeforeMount(async () => {
  //   const res = await getAllEvents();
  await getEvents();
  const temp = await getAllCategory();
  //   AllEventsData.value = res;
  allEventCategory.value = temp;
});

const AllEventsData = ref([]);
const allEventCategory = ref([]);

const getEvents = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  })
    .then(async (res) => {
      if (!res.ok) {
        await getRefreshToken();
        return await fetch(`${import.meta.env.VITE_BASE_URL}/events`, {
          method: 'GET',
          headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
          },
        });
      }
      return await res.json();
    })
    .then((data) => {
      // if (res.ok) {
      console.log(data);
      return (AllEventsData.value = data);
      // return await res.json();
    });
};

// Filter - event category
const filter = ref('Category');

// SORT - By category
const sortByCategory = computed(() => {
  if (filter.value == 0 || filter.value == 'Category') {
    return AllEventsData.value;
  } else {
    return AllEventsData.value.filter(
      (event) => event.categoryId == filter.value,
    );
  }
});

// SORT - By status
const status = ref('');
const sortByStatus = computed(() => {
  if (status.value == '') return sortByCategory.value;
  else if (status.value == 'upcoming') {
    let temp = sortByCategory.value.filter((event) => {
      return (
        event.statusName.toLowerCase() == 'upcoming' ||
        event.statusName.toLowerCase() == 'ongoing'
      );
    });
    return temp.sort((e) => -1);
  } else if (status.value == 'ongoing') {
    return sortByCategory.value.filter((event) => {
      return event.statusName.toLowerCase() == 'ongoing';
    });
  } else {
    let temp = sortByCategory.value.filter((event) => {
      return event.statusName.toLowerCase() == 'completed';
    });
    return temp;
  }
});

// SORT - By date
const selectDate = ref('');
const sortByDate = computed(() => {
  if (selectDate.value == '') {
    return sortByStatus.value;
  }

  let temp = sortByStatus.value.filter((event) => {
    return convert(event.eventStartTime) == selectDate.value;
  });
  if (
    temp.length == 0 ||
    temp.includes(
      (e) =>
        e.statusName.toLowerCase() == 'ongoing' ||
        e.statusName.toLowerCase() == 'upcoming',
    )
  ) {
    return temp;
  }

  return temp.sort((e) => -1);
});
const convert = (time) => {
  let date = new Date(time);
  return (
    date.getFullYear() +
    '-' +
    ('0' + (date.getMonth() + 1)).slice(-2) +
    '-' +
    ('0' + date.getDate()).slice(-2)
  );
};
</script>

<style></style>
