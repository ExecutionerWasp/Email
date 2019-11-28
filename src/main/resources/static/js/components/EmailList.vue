<template>
    <v-container>
        <v-card color="success">
            <v-card v-for="item in messages" class="mt-3 pl-5 pr-5">
                <v-card-title><h1>Title : {{item.title}}</h1></v-card-title>
                <v-divider class="mb-3"></v-divider>
                <h3>From : {{item.from}}</h3>
                <h3>To :
                    <v-btn class="quickMessageTo" color="#616161" @click="onSend(item.to)">{{item.to}}</v-btn>
                </h3>
                <v-divider class="mb-3 mt-3"></v-divider>
                <p class="pt-3 pb-3">Content : {{item.content}}</p>
                <v-divider></v-divider>
                <p class="pt-5">{{getDate(item.sendingDate)}}</p>
                <p class="">{{getTime(item.sendingDate)}}</p>
                <!--                <p class="pl-5 pb-5">Status : {{item.status}}</p>-->
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn  class="delete-btn mb-5" color="#5C6BC0" @click="deleteEmail(item.id)">Delete</v-btn>
                </v-card-actions>
            </v-card>
        </v-card>
        <v-dialog
                v-model="dialog"
                width="500"
        >
            <quick-email :to="this.to" :dialog="dialog"></quick-email>
        </v-dialog>
    </v-container>
</template>
<script>
    import QuickEmail from "./QuickEmail.vue";

    export default {
        name: "EmailList",
        components: {QuickEmail},
        data() {
            return {
                dialog: false,
                to: ''
            }
        },

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

            onSend(email) {
                this.to = email;
                this.dialog = true;
            }
        }
    }
</script>

<style scoped>
    .delete-btn {
        min-width: 300px;
    }

    .quickMessageTo {
        border-radius: 25px;
    }
</style>