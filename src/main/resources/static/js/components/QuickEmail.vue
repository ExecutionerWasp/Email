<template>
    <v-card>
        <v-card-title>
            <h3>From : {{this.$store.state.user.email}}</h3>
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
</template>

<script>
    export default {
        name: "QuickEmail",
        props: ["to"],

        data() {
            return {
                title: '',
                content: '',
                dialog: true
            }
        },

        methods: {
            onSend() {
                this.$store.dispatch("sendMessage",
                    {
                        from: this.$store.state.user.email,
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

</style>