<template>
  <div class="bg-black">
    <div class="grid place-items-center h-screen">
      <div class="flex">
        <div class="grid place-items-center">
          <div>
            <div>
              <h1 class="text-white font-bold text-9xl"><span class="text-red-600">ERROR</span> 404</h1>
            </div>
            <div>
              <p class="text-white text-2xl mb-6 text-center mt-8">Page not found</p>
            </div>
            <div class="flex place-content-center mt-24">
              <img src="../assets/component/backNotFound.png" width="24" >
              <p @click="goBack" class="duration-150 l-color-blue font-regular text-xl ml-4 hover:underline hover:underline-offset-8 cursor-pointer">
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
