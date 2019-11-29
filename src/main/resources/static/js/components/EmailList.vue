<template>
    <v-container>
        <v-card color="success">
            <v-card v-for="item in messages" class="mt-3 pl-5 pr-5">
                <v-card-title class="color">
                    <h1>Title : {{item.title}}</h1>
                </v-card-title>
                <v-divider class="mb-3"></v-divider>
                <h3>From : {{item.from}}</h3>
                <h3>To :
                    <v-btn class="quickMessageTo" color="#616161" @click="onTrigger(item.to)">{{item.to}}</v-btn>
                </h3>
                <v-divider class="mb-3 mt-3"></v-divider>
                <pre class="pt-3 pb-3" v-html="item.content"></pre>
                <v-divider></v-divider>
                <p class="pt-5">{{getDate(item.sendingDate)}}</p>
                <p class="">{{getTime(item.sendingDate)}}</p>
                <!--                <p class="pl-5 pb-5">Status : {{item.status}}</p>-->
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn class="delete-btn mb-5" color="#5C6BC0" @click="deleteEmail(item.id)">Delete</v-btn>
                </v-card-actions>
            </v-card>
        </v-card>
        <v-dialog
                v-model="dialog"
                width="500"
        >
            <v-card>
                <v-card-title>
                    <h3>From : {{this.$store.getters.USER_EMAIL}}</h3>
                    <h3>To : {{this.to}}</h3>
                </v-card-title>
                <v-divider></v-divider>
                <v-card-text>
                    <v-text-field v-model="title" placeholder="Title"></v-text-field>
                    <v-textarea v-model="content" placeholder="Message"></v-textarea>
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="primary"
                            text
                            @click="onSend()"
                    >
                        Send quick message
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>
<script>

    export default {
        name: "EmailList",

        data() {
            return {
                dialog: false,
                to: '',
                title: '',
                content: '',
            }
        },

        created() {
            this.$store.dispatch("sandedMessagesFrom", this.$store.getters.USER_EMAIL);
        },

        computed: {
            messages() {
                return this.$store.getters.SANDED_MESSAGES;
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

            onTrigger(email) {
                this.dialog = true;
                this.to = email;
            },

            onSend() {
                this.$store.dispatch("sendMessage",
                    {
                        from: this.$store.getters.USER_EMAIL,
                        to: this.to,
                        title: this.title,
                        content: this.content
                    });
                this.dialog = false;
            },
        }
    }
</script>

<style scoped>

    .color {
        color: #ffa900;
    }

    .delete-btn {
        min-width: 300px;
    }

    .quickMessageTo {
        border-radius: 25px;
    }
</style>