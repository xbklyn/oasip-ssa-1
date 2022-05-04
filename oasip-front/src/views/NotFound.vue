<template>
  <div>
    <div class="grid place-items-center h-screen">
      <div class="flex">
        <div>
          <img src="../assets/component/NotFound.png" />
        </div>
        <div class="grid place-items-center">
          <div class="">
            <div>
              <h1 class="text-red-400 font-black text-7xl">ERROR 404</h1>
            </div>
            <div>
              <p class="font-semibold text-4xl mb-6">Page not found</p>
            </div>
            <div>
              <p @click="goBack" class="l-color-blue font-regular text-xl underline">
                Back to home page
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
const myRouter = useRouter();
const goBack = () => myRouter.push({
  name: 'Home'
});
</script>

<style>
</style>
