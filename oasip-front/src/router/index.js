import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Booking from '../views/Booking.vue'
import Scheduled from '../views/Scheduled.vue'
import EventInfo from '../views/EventInfo.vue'
import BookingForm from '../views/BookingForm.vue'


const history = createWebHistory()
const routes = [{
        path: '/',
        name: 'Home',
        component: HomePage
    },
    {
        path: '/booking',
        name: 'Booking',
        component: Booking
    },
    {
        path: '/scheduled/',
        name: 'Scheduled',
        component: Scheduled
    },
    {
        path: '/form',
        name: 'BookingForm',
        component: BookingForm,
    },
    {
        path: '/scheduled/event-info/:id',
        component: Scheduled,
        children: [{
            path: '',
            component: EventInfo,
            name: 'event-info'
        }]
    },
    {
        path: '/:catchNotMatchPath(.*)',
        name: 'NotFound',
        component: NotFound
    }
]

const router = createRouter({ history, routes })
export default router