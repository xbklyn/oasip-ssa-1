import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Booking from '../views/Booking.vue'
import Scheduled from '../views/Scheduled.vue'
import EventInfo from '../views/EventInfo.vue'
import BookingForm from '../views/BookingForm.vue'
import NotFound from '../views/NotFound.vue'


const history = createWebHistory()
const routes = [
    {
        path: '/:catchNotMatchPath(.*)',
        name: 'NotFound',
        component: NotFound,
        meta: { title: 'Sorry! 404 Not found - OASIP' }
    }, {
        path: '/',
        name: 'Home',
        component: HomePage,
        meta: { title: 'OASIP - Welcome to Online Appointment Sevice for IntegratedProject Clinic.' 
    }
    },
    {
        path: '/booking',
        name: 'Booking',
        component: Booking,
        meta: { title: 'OASIP - Choose a clinic for admission.' }
    },
    {
        path: '/scheduled/',
        name: 'Scheduled',
        component: Scheduled,
        meta: { title: 'OASIP - List of all schedule events.' }
    },
    {
        path: '/booking/form/:id',
        name: 'BookingForm',
        component: BookingForm,
        meta: { title: 'OASIP - Please complete your information.' }
    },
    {
        path: '/scheduled/event-info/:id',
        component: Scheduled,
        meta: { title: 'OASIP - List of all schedule events.' },
        children: [{
            path: '',
            component: EventInfo,
            name: 'event-info'
        },
    ]
    }
]

const router = createRouter({ history, routes })
export default router