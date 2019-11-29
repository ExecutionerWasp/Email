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
        deletedMessage: null,

        sandedMessages: [],
        emailAddresses: []
    },

    getters : {
        USER : state => {
            return state.user
        },

        USER_EMAIL : state => {
            return state.user.email
        },

        SANDED_MESSAGES : state =>  {
          return state.sandedMessages
        },

        EMAIL_ADDRESSES : state => {
            return state.emailAddresses
        }
    },

    mutations: {
        sendMessage(state, payload) {
            state.sendingMessage = payload;
        },

        sandedMessagesFrom(state, payload) {
            state.sandedMessages = payload;
        },

        sandedMessagesTo(state, payload) {
            state.sandedMessages = payload;
        },

        allEmailAddresses(state, payload){
            state.emailAddresses = payload;
        },

        deleteMessage(state, payload) {
            if (state.sandedMessages !== null) {
                state.sandedMessages.forEach(m => {
                    if (m.id === payload) {
                        state.sandedMessages.splice(state.sandedMessages.indexOf(m), 1);
                        state.deletedMessage = m;
                    }
                });
            }
        },
    },

    actions: {
        sendMessage({commit}, payload) {
            return axios
                .post(`/email/sand?from=${payload.from}&to=${payload.to}&title=${payload.title}&content=${encodeURI(payload.content)}`)
                .then(responseMessage => commit("sendMessage", responseMessage));
        },

        sandedMessagesFrom({commit}, payload) {
            return axios
                .get(`/email/sandedFrom?from=${payload}`)
                .then(messages => commit("sandedMessagesFrom", messages.data));
        },

        sandedMessagesTo({commit}, payload) {
            return axios
                .get(`/email/sandedMessagesTo?to=${payload}`)
                .then(messages => commit("sandedMessagesTo", messages.data));
        },

        emailAddresses({commit}, payload) {
            return axios
                .get("/email/addresses")
                .then(addresses => commit("emailAddresses", addresses.data));
        },

        deleteMessage({commit}, payload) {
            return axios
                .delete(`/email/delete?id=${payload}`)
                .then(message => commit("deleteMessage", message.data.id));
        }
    }
});