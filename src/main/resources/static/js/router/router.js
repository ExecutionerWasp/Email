import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../components/Main.vue'
import EmailList from "../components/EmailList.vue";

Vue.use(VueRouter);

const routes = [
    {path: '/', component: Main},
    {path: '/list', component: EmailList},
    {path: '*', component: Main}
];

export default new VueRouter({
    mode: 'history',
    routes
})