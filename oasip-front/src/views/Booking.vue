<template>
    <div class="l-w-full my-24 mx-auto">
        <h1 class="text-4xl font-bold l-color-navi text-center mb-12">What clinic do you want to book?</h1>
        <div class="space-y-6">
            <div>
                <router-link :to="{ name: '' }">
                    <div class="w-full h-48 rounded-lg flex justify-center place-items-center space-x-32 l-bg-blue">
                        <div class="w-8/12 h-28 text-white">
                            <h2 class="text-2xl font-bold mb-1">Front-end Clinic</h2>
                            <p class="text-base font-light">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa faucibus eu nulla
                                molestie.
                                Interdum enim, tempus ullamcorper quam ultrices. Integer sit nulla magna pellentesque
                                viverra
                                dolor. Eget et mauris, auctor nunc, faucibus leo.
                            </p>
                        </div>
                        <div class="w-24 h-24 text-7xl flex justify-center place-items-center">
                            <img src="../assets/next.png" />
                        </div>
                    </div>
                </router-link>
            </div>
            <div>
                <router-link :to="{ name: '' }">
                    <div class="w-full h-48 rounded-lg flex justify-center place-items-center space-x-32 l-bg-blue">
                        <div class="w-8/12 h-28 text-white">
                            <h2 class="text-2xl font-bold mb-1">Back-end Clinic</h2>
                            <p class="text-base font-light">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa faucibus eu nulla
                                molestie.
                                Interdum enim, tempus ullamcorper quam ultrices. Integer sit nulla magna pellentesque
                                viverra
                                dolor. Eget et mauris, auctor nunc, faucibus leo.
                            </p>
                        </div>
                        <div class="w-24 h-24 text-7xl flex justify-center place-items-center">
                            <img src="../assets/next.png" />
                        </div>
                    </div>
                </router-link>
            </div>
            <div>
                <router-link :to="{ name: '' }">
                    <div class="w-full h-48 rounded-lg flex justify-center place-items-center space-x-32 l-bg-blue">
                        <div class="w-8/12 h-28 text-white">
                            <h2 class="text-2xl font-bold mb-1">Database Clinic</h2>
                            <p class="text-base font-light">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa faucibus eu nulla
                                molestie.
                                Interdum enim, tempus ullamcorper quam ultrices. Integer sit nulla magna pellentesque
                                viverra
                                dolor. Eget et mauris, auctor nunc, faucibus leo.
                            </p>
                        </div>
                        <div class="w-24 h-24 text-7xl flex justify-center place-items-center">
                            <img src="../assets/next.png" />
                        </div>
                    </div>
                </router-link>
            </div>
            <div>
                <router-link :to="{ name: '' }">
                    <div class="w-full h-48 rounded-lg flex justify-center place-items-center space-x-32 l-bg-blue">
                        <div class="w-8/12 h-28 text-white">
                            <h2 class="text-2xl font-bold mb-1">DevOps Clinic</h2>
                            <p class="text-base font-light">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa faucibus eu nulla
                                molestie.
                                Interdum enim, tempus ullamcorper quam ultrices. Integer sit nulla magna pellentesque
                                viverra
                                dolor. Eget et mauris, auctor nunc, faucibus leo.
                            </p>
                        </div>
                        <div class="w-24 h-24 text-7xl flex justify-center place-items-center">
                            <img src="../assets/next.png" />
                        </div>
                    </div>
                </router-link>
            </div>
            <div>
                <router-link :to="{ name: '' }">
                    <div class="w-full h-48 rounded-lg flex justify-center place-items-center space-x-32 l-bg-blue">
                        <div class="w-8/12 h-28 text-white">
                            <h2 class="text-2xl font-bold mb-1">Project Management Clinic</h2>
                            <p class="text-base font-light">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa faucibus eu nulla
                                molestie.
                                Interdum enim, tempus ullamcorper quam ultrices. Integer sit nulla magna pellentesque
                                viverra
                                dolor. Eget et mauris, auctor nunc, faucibus leo.
                            </p>
                        </div>
                        <div class="w-24 h-24 text-7xl flex justify-center place-items-center">
                            <img src="../assets/next.png" />
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>
 
<script setup>
import EventLists from '../components/EventLists.vue';
</script>
 
<style>
</style>