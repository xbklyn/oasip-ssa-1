<template>
  <div>
    <div class="flex items-center justify-center">
      <div class="grid grid-rows-5 l-w-612 gap-y-4">
        <div class="text-center">
          <p class="text-2xl">Sign In</p>
          <h1 class="text-8xl">OASIP</h1>
        </div>
        <div>
          <p class="text-base">Email</p>
          <input
            type="text"
            class="w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
        </div>
        <div>
          <p class="text-base">Password</p>
          <input
            type="text"
            class="w-full h-12 pl-2 text-sm bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
          />
        </div>
        <div
          class="h-16 place-content-center flex items-center justify-center l-bg-blue"
        >
          <p class="text-xs font-light text-center text-white">Login</p>
        </div>
        <div class="h-16 flex items-center justify-center">
          <p class="text-xs font-light text-center l-color-gray">Back</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>
