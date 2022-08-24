<template>
  <div class="w-[824px] mx-auto mt-12 scroll-smooth" id="top">
    <!-- Menu - Breadcrumbs -->
    <div class="w-[824px] mx-auto mb-4">
      <div class="flex items-center">
        <router-link
          :to="{ name: 'users-manage' }"
          class="inline-flex items-center text-sm font-medium l-color-blue hover:text-blue-600 hover:underline hover:underline-offset-4"
        >
          <svg class="w-5 h-5 mr-1" viewBox="0 0 512 512">
            <path
              d="M427 234.625H167.296l119.702-119.702L256 85 85 256l171 171 29.922-29.924-118.626-119.701H427v-42.75z"
              fill="currentColor"
            ></path>
          </svg>
          Back to user manage
        </router-link>
      </div>
    </div>
    <div class="w-[824px] grid grid-cols-2 gap-6 mb-16" v-if="!editMode">
      <div>
        <h1 class="font-bold text-4xl l-color-navi">User Detail</h1>
        <p class="font-light text-sm l-color-gray-300">
          Lasted upedate:
          {{ moment(user.updatedOn).format('LLL') }}
        </p>
      </div>
      <div class="justify-self-end space-x-6 text-sm place-items-center">
        <button
          @click="clickEdit"
          class="w-20 h-8 border border-blue-600 text-blue-600 hover:bg-blue-700 hover:text-white hover:border duration-150 disabled:border-gray-300 disabled:bg-gray-200 disabled:border disabled:text-gray-400"
        >
          Edit
        </button>
        <button
          class="w-20 h-8 bg-red-500 text-white font-medium hover:bg-red-700 hover:text-white duration-150"
          @click="confirmBox = true"
        >
          Delete
        </button>
      </div>
    </div>

    <!-- header when edit mode -->
    <div class="w-[824px] grid grid-cols-2 gap-6 mb-16" v-else>
      <div>
        <h1 class="font-bold text-4xl l-color-navi">Edit User</h1>
        <p class="font-light text-sm l-color-gray-300">
          Lasted upedate:
          {{ moment(user.updatedOn).format('LLL') }}
        </p>
      </div>
      <div class="justify-self-end space-x-6 text-sm place-items-center">
        <button
          @click="closedEditMode"
          class="w-20 h-8 border border-red-500 text-red-500 hover:bg-red-500 hover:text-white duration-150"
        >
          Close
        </button>
      </div>
    </div>
    <!-- Info - User -->
    <div class="w-[824px] grid grid-cols-2 gap-12" v-if="!editMode">
      <Description :title="'Name'" :text="user.userName" />
      <Description :title="'Email'" :text="user.userEmail" />
      <Description :title="'Role'" :text="user.role" />
      <Description
        :title="'Create at'"
        :text="moment(user.createdOn).format('LLL')"
      />
    </div>

    <!-- Edit User -->
    <div class="w-[824px]" v-else>
      <div>
        <input
          :checked="user.role === 'student'"
          class=""
          type="radio"
          name="role"
          :value="'student'"
          v-model="role"
        />
        <label for=""> Student </label>
        <input
          :checked="user.role === 'lecturer'"
          type="radio"
          name="role"
          :value="'lecturer'"
          v-model="role"
        />
        <label for="">Lecturer</label>
        <input
          :checked="user.role === 'admin'"
          type="radio"
          name="role"
          :value="'admin'"
          v-model="role"
        />
        <label for="">Admin</label>
      </div>
      <div class="grid gap-12 mt-8">
        <div class="relative">
          <input
            type="text"
            v-model="name"
            :class="[
              'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
              responeError?.details?.userName
                ? 'border-red-500 focus:border-red-500'
                : '',
            ]"
            placeholder=" "
          />
          <label
            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >
            Name<span class="text-red-500">*</span>
          </label>
          <p class="absolute text-sm text-red-500 ml-2">
            {{ responeError?.details?.userName }}
          </p>
        </div>
        <div class="relative">
          <input
            type="text"
            v-model="email"
            :class="[
              'w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer',
              responeError?.details?.userEmail
                ? 'border-red-500 focus:border-red-500'
                : '',
            ]"
            placeholder=" "
          />
          <label
            class="absolute text-sm l-color-gray-300 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white px-2 peer-focus:px-2 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >
            Email<span class="text-red-500">*</span>
          </label>
          <p class="absolute text-sm text-red-500 ml-2">
            {{ responeError?.details?.userEmail }}
          </p>
        </div>
        <div class="w-full h-12 mt-12 space-x-6">
          <button
            @click="putUser"
            :class="[
              'w-24 h-full text-white duration-150 bg-emerald-500 disabled:bg-gray-300',
            ]"
          >
            Update
          </button>
          <button
            :class="['text-slate-500 h-full hover:text-slate-300 duration-150']"
          >
            Discard
          </button>
        </div>
      </div>

      <!-- ALERT - Succesfully -->
      <div
        v-if="SUCCESFUL"
        class="alert duration-150 flex p-4 text-sm text-emerald-600 bg-white border place-items-center mt-6"
        role="alert"
      >
        <div>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            aria-hidden="true"
            role="img"
            class="iconify iconify--clarity mr-2"
            width="24"
            height="24"
            preserveAspectRatio="xMidYMid meet"
            viewBox="0 0 36 36"
          >
            <path
              fill="currentColor"
              d="M18 2a16 16 0 1 0 16 16A16 16 0 0 0 18 2Zm10.45 10.63L15.31 25.76L7.55 18a1.4 1.4 0 0 1 2-2l5.78 5.78l11.14-11.13a1.4 1.4 0 1 1 2 2Z"
              class="clr-i-solid clr-i-solid-path-1"
            ></path>
            <path fill="none" d="M0 0h36v36H0z"></path>
          </svg>
        </div>
        <div><span class="font-medium">DONE!</span> Update succesfully.</div>
      </div>

      <!-- ALERT - Error -->
      <div
        v-if="ERROR"
        class="alert duration-150 flex p-4 mt-2 pb-4 text-sm text-red-500 border place-items-center"
        role="alert"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          aria-hidden="true"
          role="img"
          class="iconify iconify--material-symbols mr-2"
          width="24"
          height="24"
          preserveAspectRatio="xMidYMid meet"
          viewBox="0 0 24 24"
        >
          <path
            fill="currentColor"
            d="M12 22q-2.075 0-3.9-.788q-1.825-.787-3.175-2.137q-1.35-1.35-2.137-3.175Q2 14.075 2 12t.788-3.9q.787-1.825 2.137-3.175q1.35-1.35 3.175-2.138Q9.925 2 12 2t3.9.787q1.825.788 3.175 2.138q1.35 1.35 2.137 3.175Q22 9.925 22 12t-.788 3.9q-.787 1.825-2.137 3.175q-1.35 1.35-3.175 2.137Q14.075 22 12 22Zm-1-9h2V7h-2Zm1 4q.425 0 .713-.288Q13 16.425 13 16t-.287-.713Q12.425 15 12 15t-.712.287Q11 15.575 11 16t.288.712Q11.575 17 12 17Z"
          ></path>
        </svg>
        <div>
          <span class="font-medium">ERROR! </span>
          Username {{ responeError?.message }}
        </div>
      </div>
    </div>

    <!-- Modal box - Confirmation -->
    <div
      v-if="confirmBox"
      class="grid place-items-center fixed top-0 right-0 left-0 z-50 bg-black/80 w-screen h-screen"
    >
      <div class="bg-white l-w-960 l-h-520 flex">
        <div>
          <img src="../assets/component/confirmation-2.png" alt="" />
        </div>
        <div class="l-w-520 l-h-520 grid place-items-center">
          <div class="grid justify-center">
            <img src="../assets/component/warning.png" alt="" />
          </div>
          <div class="text-center space-y-3">
            <h2 class="font-medium text-2xl">
              Are you sure delete this event?
            </h2>
            <p class="l-w-400 font-light text-xs l-color-gray-300">
              If you delete this user, it will be gone forever.
            </p>
          </div>
          <div class="grid space-y-3">
            <button
              class="w-80 h-12 bg-white text-green-700 duration-150 border border-green-700 hover:bg-green-700 hover:text-white"
              @click="deleteUserById"
            >
              Confirm
            </button>
            <button
              class="w-80 h-12 bg-red-600 text-white hover:bg-red-800 duration-150"
              @click="confirmBox = false"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from '@vue/reactivity';
import { onBeforeMount } from '@vue/runtime-core';
import { useRoute, useRouter } from 'vue-router';
import moment from 'moment';
import Description from '../components/commons/lists/Description.vue';
const { params } = useRoute();

onBeforeMount(async () => {
  await getUserById(params.id);
});

const myRouter = useRouter();
const user = ref({});
const confirmBox = ref(false);
const SUCCESFUL = ref(false);
const ERROR = ref(false);
const editMode = ref(false);
const responeError = ref({});

const clickEdit = () => {
  editMode.value = true;
  role.value = userRole.value;
  name.value = userName.value;
  email.value = userEmail.value;
};
const userRole = computed(() => {
  return user.value.role;
});
const userName = computed(() => {
  return user.value.userName;
});
const userEmail = computed(() => {
  return user.value.userEmail;
});
const role = ref('');
const name = ref('');
const email = ref('');

const closedEditMode = () => {
  editMode.value = false;
  responeError.value = {};
};

const uniqueUserName = computed(() => {
  return validSameData.value === userEdit.value.userName;
});

const userEdit = computed(() => {
  return {
    role: role.value,
    userName: name.value.trim(),
    userEmail: email.value.trim(),
  };
});

const checkSameUserData = computed(() => {
  return {
    role: user.value?.role,
    userName: user.value?.userName,
    userEmail: user.value?.userEmail,
  };
});

const validSameData = computed(() => {
  if (
    JSON.stringify(checkSameUserData.value) === JSON.stringify(userEdit.value)
  )
    return true;
});

const validUserName = computed(() => {
  return user.value?.userName;
});

const successAlert = () => {
  responeError.value = {};
  SUCCESFUL.value = true;
  ERROR.value = false;
  setTimeout(() => {
    SUCCESFUL.value = false;
  }, 1500);
};

const errorAlert = () => {
  SUCCESFUL.value = false;
  ERROR.value = true;
  setTimeout(() => {
    ERROR.value = false;
  }, 1500);
};

// Fetch service
// GET METHOD - Get user by Id
const getUserById = async (id) => {
  await fetch(`${import.meta.env.VITE_BASE_URL}/users/${id}`)
    .then(async (res) => {
      if (res.status === 200) return (user.value = await res.json());
      throw new Error();
    })
    .catch((e) => {
      return e.message;
    });
};

// DELETE METHOD - Delete user
const deleteUserById = async () => {
  await fetch(`${import.meta.env.VITE_BASE_URL}/users/${params.id}`, {
    method: 'DELETE',
  }).then(async (res) => {
    if (res.ok) {
      successAlert();
      myRouter.push({
        name: `users-manage`,
      });
      return;
    } else {
      errorAlert();
      return (responeError.value = await res.json());
    }
  });
};

// PUT METHOD - Edit user
const putUser = async () => {
  if (validSameData.value) {
    successAlert();
    setTimeout(() => {
      editMode.value = false;
    }, 1500);
    return;
  }
  await fetch(`${import.meta.env.VITE_BASE_URL}/users/${params.id}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(userEdit.value),
  }).then(async (res) => {
    if (res.ok) {
      successAlert();
      setTimeout(() => {
        editMode.value = false;
      }, 1500);
      responeError.value = {};
      await getUserById(params.id);
      return;
    } else {
      errorAlert();
      return (responeError.value = await res.json());
    }
  });
};
</script>

<style></style>
