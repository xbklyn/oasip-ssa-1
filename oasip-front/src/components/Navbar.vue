<template>
    <nav class="grid grid-cols-3 justify-center place-items-center bg-white w-full drop-shadow-sm">

        <!-- Icon -->
        <div class="justify-self-start ml-24">
            <router-link :to="{ name: 'Home' }">
                <div class="flex space-x-3">
                    <img src="../assets/logo.png" alt="" width="" class="">
                    <p class="font-medium text-2xl">OASIP</p>
                </div>
            </router-link>
        </div>

        <!-- Menu - main nav -->
        <div class="flex justify-center space-x-12 h-24 place-items-center text-xs font-light l-text-navi drop-shadow-sm">

            <!-- Dashboard -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Home' ? 'text-blue-400 border-b-2 border-blue-600' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Home' }">
                    Dashboard
                </router-link>
            </div>

            <!-- Scheduled -->
            <div :class="['grid place-items-center l-color-navi h-24 w-24',
            $route.name == 'Scheduled' ? 'text-blue-400 border-b-2 border-blue-600' : '']">
                <router-link class="hover:text-blue-400 ease-out duration-150" :to="{ name: 'Scheduled' }">
                    Scheduled
                </router-link>
            </div>
        </div>

        <!-- Booking && Manage -->
        <div class="grid grid-cols-2 justify-self-end gap-x-12 mr-24">

            <router-link :to="{ name: 'Booking' }" class="flex place-items-center">
                <button
                    class="w-28 h-10 bg-white font-light grid place-items-center border border-green-600  text-green-600 hover:bg-gradient-to-tl to-emerald-400 from-lime-500 hover:border-none hover:text-white hover:border-2 duration-150">
                    <p>Book now</p>
                </button>
            </router-link>

            <router-link :to="{ name: 'clinic-manage' }" class="flex place-items-center">
                <button
                    class="flex gap-x-1 w-28 h-10 text-sm font-light place-items-center text-blue-600 duration-150 hover:text-blue-300">
                    <svg width="1em" height="1em" viewBox="0 0 24 24">
                        <path fill="currentColor"
                            d="m9.25 22l-.4-3.2q-.325-.125-.612-.3q-.288-.175-.563-.375L4.7 19.375l-2.75-4.75l2.575-1.95Q4.5 12.5 4.5 12.337v-.675q0-.162.025-.337L1.95 9.375l2.75-4.75l2.975 1.25q.275-.2.575-.375q.3-.175.6-.3l.4-3.2h5.5l.4 3.2q.325.125.613.3q.287.175.562.375l2.975-1.25l2.75 4.75l-2.575 1.95q.025.175.025.337v.675q0 .163-.05.338l2.575 1.95l-2.75 4.75l-2.95-1.25q-.275.2-.575.375q-.3.175-.6.3l-.4 3.2Zm2.8-6.5q1.45 0 2.475-1.025Q15.55 13.45 15.55 12q0-1.45-1.025-2.475Q13.5 8.5 12.05 8.5q-1.475 0-2.488 1.025Q8.55 10.55 8.55 12q0 1.45 1.012 2.475Q10.575 15.5 12.05 15.5Zm0-2q-.625 0-1.062-.438q-.438-.437-.438-1.062t.438-1.062q.437-.438 1.062-.438t1.063.438q.437.437.437 1.062t-.437 1.062q-.438.438-1.063.438ZM12 12Zm-1 8h1.975l.35-2.65q.775-.2 1.438-.588q.662-.387 1.212-.937l2.475 1.025l.975-1.7l-2.15-1.625q.125-.35.175-.738q.05-.387.05-.787t-.05-.788q-.05-.387-.175-.737l2.15-1.625l-.975-1.7l-2.475 1.05q-.55-.575-1.212-.963q-.663-.387-1.438-.587L13 4h-1.975l-.35 2.65q-.775.2-1.437.587q-.663.388-1.213.938L5.55 7.15l-.975 1.7l2.15 1.6q-.125.375-.175.75q-.05.375-.05.8q0 .4.05.775t.175.75l-2.15 1.625l.975 1.7l2.475-1.05q.55.575 1.213.962q.662.388 1.437.588Z">
                        </path>
                    </svg>
                    <p>Manage</p>
                </button>
            </router-link>

        </div>
    </nav>
</template>
 
<script setup>

</script>
 
<style>

</style>