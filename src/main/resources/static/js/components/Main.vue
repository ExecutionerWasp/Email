<template>
    <v-container>
        <v-card>
            <v-card-title>SEND EMAIL</v-card-title>
            <v-card>
                <v-content>
                    <v-flex xs4 class="pl-5 pb-5">
                        <v-text-field v-model="message.from" placeholder="From"></v-text-field>
                        <v-text-field v-model="message.to" placeholder="To"></v-text-field>
                        <v-text-field v-model="message.title" placeholder="Title"></v-text-field>
                    </v-flex>
                    <v-flex>
                    </v-flex>
                </v-content>
                <v-textarea class="pl-5 pr-5" row-height="20" rows="20" v-model="message.content" placeholder="Content"></v-textarea>
            </v-card>
            <v-card-actions>
                <v-btn block @click="onSend()">Send</v-btn>
            </v-card-actions>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "Main",

        data() {
            return {
                message: {
                    from: '',
                    to: '',
                    title: '',
                    content: ''
                }
            }
        },

        methods: {
            onSend() {
                return this.$store.dispatch("sendMessage", this.message);
            }
        }
    }
</script>

<style scoped>

</style>