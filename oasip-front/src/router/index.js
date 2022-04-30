import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Booking from '../views/Booking.vue'
import Scheduled from '../views/Scheduled.vue'
import EventInfo from '../views/EventInfo.vue'


const history = createWebHistory()
const routes = [{
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/booking',
        name: 'Booking',
        component: Booking
    },
    {
        path: '/scheduled',
        name: 'Scheduled',
        component: Scheduled
    },
    {
        path: '/scheduled/event-info/:id',
        name: 'event-info',
        component: EventInfo
    }
]

const router = createRouter({ history, routes })
export default router