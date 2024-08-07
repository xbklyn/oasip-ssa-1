import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import Booking from '../views/Booking.vue';
import Scheduled from '../views/Scheduled.vue';
import ScheduledInfo from '../views/ScheduledInfo.vue';
import Manage from '../views/Manage.vue';
import ClinicManage from '../views/ClinicManage.vue';
import UserManage from '../views/UserManage.vue';
import UserInfo from '../views/UserInfo.vue';
import NotFound from '../views/NotFound.vue';
import LoginPage from '../views/LoginPage.vue';
import Account from '../views/AccountManage.vue';

const router = createRouter({
    history: createWebHistory('/ssa1'),
    routes: [{
            path: '/:catchNotMatchPath(.*)',
            name: 'NotFound',
            component: NotFound,
            meta: { title: 'Sorry! 404 Not found - OASIP' },
        },
        {
            path: '/',
            name: 'Home',
            component: HomePage,
            meta: {
                title: 'OASIP - Welcome to Online Appointment Sevice for IntegratedProject Clinic.',
            },
        },
        {
            path: '/booking',
            name: 'Booking',
            component: Booking,
            meta: { title: 'OASIP - Choose a clinic for admission.' },
        },
        {
            path: '/scheduled',
            name: 'Scheduled',
            component: Scheduled,
            meta: { title: 'OASIP - List of all scheduled events.' },
        },
        {
            path: '/scheduled/scheduled-info/:id',
            name: 'scheduled-info',
            component: ScheduledInfo,
            meta: { title: 'OASIP - Scheduled Infomation.' },
        },
        {
            path: '/manage',
            name: 'Manage',
            component: Manage,
            meta: { title: 'OASIP - Manage your setting.' },
            children: [{
                    path: '/account',
                    name: 'account',
                    component: Account,
                },
                {
                    path: 'clinic',
                    name: 'clinic-manage',
                    component: ClinicManage,
                },
                {
                    path: '/users',
                    name: 'users-manage',
                    component: UserManage,
                },
                {
                    path: '/users/user/:id',
                    name: 'user-info',
                    component: UserInfo,
                },
            ],
        },
        {
            path: '/login',
            name: 'Login',
            component: LoginPage,
            meta: { title: 'OASIP - Sign In' },
        },
    ],
    scrollBehavior(to, from, savedPosition) {
        return { top: 0 };
    },
});
export default router;