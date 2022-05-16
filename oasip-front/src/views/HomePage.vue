<template>
    <div>
        <div class="relative l-w-full l-h-1176 mx-auto my-24">
            <p class="text-center">Welcome to</p>
            <h1 class="text-8xl font-light text-center leading-relaxed">
                Online Appointment<br>
                Sevice for Integrated<br>
                Project Clinic</h1>

            <!-- <div class="box absolute w-1 h-5 bg-yellow-400 top-72 right-24"></div>
            <div class="box absolute w-5 h-5 bg-blue-400 top-76 left-36"></div>
            <div class="box absolute w-2 h-8 bg-red-400 top-76 right-72"></div>
            <div class="box absolute w-8 h-8 text-black text-3xl top-76 left-48">X</div>
            <div class="box absolute w-1 h-5 bg-purple-400 top-36 right-48"></div>
            <div class="box absolute w-1 h-5 bg-green-400 top-76 left-60"></div> -->
        </div>
    </div>
</template>
 
<script setup>

</script>
 
<style>
.box {
    animation: cloudy 2s infinite ease-in-out;
    animation-iteration-count: infinite;
}

@keyframes cloudy {
    from {
        transform: rotate(0deg);
        bottom: 0%;
    }
    50%{
        bottom: 10%;
    }

    to {
        transform: rotate(360deg);
        bottom: 0%;
    }
}
</style>