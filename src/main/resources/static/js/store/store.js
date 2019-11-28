import Vue from 'vue';
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: {
            email : 'y.kulpin@stollerukraine.com',
        },
        sendingMessage: null,
        sandedMessages: [],
        deletedMessage: null
    },

    mutations: {
        sendMessage(state, message) {
            state.sendingMessage = message;
        },

        sandedMessagesFrom(state, messages) {
            state.sandedMessages = messages;
        },

        sandedMessagesTo(state, messages) {
            state.sandedMessages = messages;
        },

        deleteMessage(state, message) {
            if (state.sandedMessages !== null) {
                state.sandedMessages.forEach(m => {
                    if (m.id === message) {
                        state.sandedMessages.splice(state.sandedMessages.indexOf(m), 1);
                        state.deletedMessage = m;
                    }
                });
            }
        },
    },

    actions: {
        sandMessage({commit}, message) {
            return axios
                .post(
                    "/email/sand"
                    + "?from=" + message.from
                    + "&to=" + message.to
                    + "&title=" + message.title
                    + "&content=" + encodeURI(message.content))
                .then(responseMessage => commit("sendMessage", responseMessage));
        },

        sandedMessagesFrom({commit}, email) {
            return axios
                .get("/email/sandedFrom/" + email)
                .then(messages => commit("sandedMessagesFrom", messages.data));
        },

        sandedMessagesTo({commit}, email) {
            return axios
                .get("/email/sandedMessagesTo/" + email)
                .then(messages => commit("sandedMessagesTo", messages.data));
        },

        deleteMessage({commit}, id) {
            return axios
                .post("/email/delete/" + id)
                .then(message => commit("deleteMessage", message.data.id));
        }
    }
});