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
            {{ responeError }}
          </p>
        </div>
        <div>
          <p class="text-base">Password</p>
          <input
            v-model="login.password"
            type="password"
            class="w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
          <p v-if="responeError" class="absolute text-sm text-red-500 ml-2">
            {{ responeError }}
          </p>
        </div>
        <div>
          <div
            class="h-12 place-content-center flex items-center justify-center l-bg-blue"
          >
            <button
              @click="userLogin"
              class="w-full h-12 text-xs font-light text-center text-white"
            >
              Login
            </button>
          </div>
          <div class="h-12 flex items-center justify-center">
            <p class="text-xs font-light text-center l-color-gray">Back</p>
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
  password: '',
});
const responeError = ref({});

const userLogin = async () => {
  await fetch(`${import.meta.env.VITE_BASE_URL}/match`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(login.value),
  }).then(async (res) => {
    if (res.ok) {
      myRouter.push({
        name: 'Homepage',
      });
    } else {
      return (responeError.value = await res.json());
    }
  });
};
</script>
