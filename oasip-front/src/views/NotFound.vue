<template>
  <div>
      <div class="grid content-center justify-center place-items-center">
          <div>
        <div class="flex">
          <div class="basis-1/4">
            <img src="../assets/component/NotFound.png" />
          </div>
          <div class="grid place-items-center">
            <div class="basis-3/4">
              <div>
                <h1 class="text-red-400 font-black text-9xl">ERROR 404</h1>
              </div>
              <div>
                <h3 class="font-regular text-5xl">Page not found</h3>
              </div>
              <div >
                <h4 @click="goBack" class="text-blue-400 font-regular text-2xl">
                  Back to home page
                </h4>
              </div>
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
const goBack = () => myRouter.go(-1);
</script>

<style></style>
