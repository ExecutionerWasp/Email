<template>
    <v-toolbar>
        <v-toolbar-title>
            STOLLER UKRAINE EMAIL
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn color="success" @click="onSendEmail()">SEND EMAIL</v-btn>
        <v-btn color="warning" @click="onEmailList()">EMAILS LIST</v-btn>
    </v-toolbar>
</template>

<script>
    export default {
        name: "AppHeader",

        methods: {
            onSendEmail(){
                this.$router.push("/");
            },

            onEmailList(){
                this.$router.push("/list");
            }
        }
    }
</script>

<style scoped>

</style>