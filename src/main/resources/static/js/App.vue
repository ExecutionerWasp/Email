<template>
    <v-app dark>
        <AppHeader></AppHeader>
        <router-view></router-view>
    </v-app>
</template>
<script>

    import AppHeader from "./sections/AppHeader.vue";
    export default {
        components: {AppHeader}
    }
</script>
<style>

</style>