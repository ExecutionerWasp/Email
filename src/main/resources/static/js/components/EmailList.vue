<template>
    <v-container>
        <v-card color="success">
            <v-card-title>Email List</v-card-title>
            <v-card v-for="item in messages" class="pl-5">
                <v-card-title><h1>Title : {{item.title}}</h1></v-card-title>
                <h3>From : {{item.from}}</h3>
                <h3>To : <v-btn color="#616161" @click="">{{item.to}}</v-btn></h3>
                <p class="pt-5">Content : {{item.content}}</p>
                <p class="pl-5 pt-5">{{getDate(item.sendingDate)}}</p>
                <p class="pl-5">{{getTime(item.sendingDate)}}</p>
                <!--                <p class="pl-5 pb-5">Status : {{item.status}}</p>-->
                <v-card-actions>
                    <v-btn class="mb-5" @click="deleteEmail(item.id)">Delete</v-btn>
                </v-card-actions>
            </v-card>
        </v-card>
    </v-container>
</template>
<script>
    export default {
        name: "EmailList",

        created() {
            this.$store.dispatch("sandedMessagesFrom", this.$store.state.user.email);
        },

        computed: {
            messages() {
                return this.$store.state.sandedMessages;
            }
        },

        methods: {
            getDate(s) {
                let date = new Date(s);
                s = date.getDate() + ' . ' + date.getMonth() + ' . ' + date.getFullYear();
                return s;
            },

            getTime(s) {
                let date = new Date(s);
                s = date.getHours() + ' : ' + date.getMinutes() + ' : ' + date.getSeconds();
                return s;
            },

            deleteEmail(id) {
                this.$store.dispatch("deleteMessage", id);
            },

            onSend() {

            }
        }
    }
</script>

<style scoped>

</style>