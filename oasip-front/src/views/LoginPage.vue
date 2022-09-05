<template>
  <div>
    <div class="flex items-center justify-center w-full h-screen">
      <div class="grid gap-y-6 l-w-612">
        <div class="text-center">
          <p class="text-2xl">Sign In</p>
          <h1 class="text-8xl">OASIP</h1>
        </div>
        <div>
          <p class="text-base">Email</p>
          <input
            v-model="login.email"
            type="text"
            class="w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
          <p v-if="responeError" class="absolute text-sm text-red-500 ml-2">
            {{ responeError?.details?.userEmail }}
          </p>
        </div>
        <div>
          <p class="text-base">Password</p>
          <input
            v-model="login.rawPassword"
            type="password"
            class="w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
          <p v-if="responeError" class="absolute text-sm text-red-500 ml-2">
            {{ responeError?.details?.password }}
          </p>
        </div>
        <div>
          <div
            class="h-12 place-content-center flex items-center justify-center l-bg-blue hover:bg-[#1089ff]/70 duration-150"
          >
            <button
              @click="userLogin"
              class="w-full h-12 text-xs font-light text-center text-white"
            >
              Login
            </button>
          </div>
          <div class="h-12 flex items-center justify-center">
            <button
              @click="backToHome"
              class="w-full h-12 text-xs font-light text-center text-[#8d8d8d] hover:text-[#d4daef]/70 duration-150"
            >
              Back
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from '@vue/reactivity';
import { useRouter } from 'vue-router';

const myRouter = useRouter();
const login = ref({
  email: '',
  rawPassword: '',
});
const responeError = ref({});

const userLogin = async () => {
  responeError.value = {};
  await fetch(`${import.meta.env.VITE_BASE_URL}/authenticate/match`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(login.value),
  }).then(async (res) => {
    if (res.ok) {
      await fetch(`${import.meta.env.VITE_BASE_URL}/authenticate/auth`, {
        method: 'POST',
        headers: {
          'content-type': 'application/json',
        },
        body: JSON.stringify(login.value),
      }).then(async (res) => {
        if (res.ok) {
          alert('Login ได้แล้วควย');
        } else {
        }
      });
    } else {
      alert('ควายโง่');
      return (responeError.value = await res.json());
    }
  });
};

const backToHome = () => {
  myRouter.push({
    name: 'Home',
  });
};
</script>
