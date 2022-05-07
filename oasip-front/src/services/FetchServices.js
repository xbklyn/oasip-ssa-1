//GET METHOD - All event
export const getAllEvents = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`)
    // const res = await fetch('http://localhost:9999/events')
    if (res.status === 200) {
        console.log(res.status);
        return await res.json();
    } else {
        console.log('Cannot fetch from database');
    }
}

// GET METHOD - Event by id
export const getEventById = async (id) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`)
    // const res = await fetch(`http://localhost:9999/events/${id}`)
    if (res.status === 200) {
        return await res.json()
    } else {
        console.log('err');
    }
}

// GET METHOD - Event category by id
export const getEventCategoryById = async (id) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`)
    // const res = await fetch(`http://localhost:9999/eventCategory/${id}`)
    if (res.status === 200) {
        return await res.json()
    } else {
        return console.log('err');
    }
}

// POST METHOD - Create event
export const createEvent = async (
    name, email, date, startTime, duration, categoryId, notes) => {
    const res = await fetch('${import.meta.env.VITE_BASE_URL}/events',{
    // const res = await fetch(`http://localhost:9999/test`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            bookingName: name,
            bookingEmail: email,
            eventDate: date,
            eventStartTime: startTime,
            eventDuration: duration,
            categoryId: categoryId,
            eventNotes: notes
        })
    })
    if (res.status === 201) {
        return await res.json()
    } else {
        console.log('err');
    }
}

// DELETE METHOD - Delete event by id
export const deleteEventById = async (id) => {
    await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`, {
    // await fetch(`http://localhost:9999/events/${id}`, {
        method: 'DELETE'
    })
}
