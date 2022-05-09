//GET METHOD - All event
export const getAllEvents = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`, {
        headers: {
            'Access-Control-Allow-Origin': origin
        }
    })
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
    if (res.status === 200) {
        return await res.json()
    } else {
        console.log('err');
    }
}
// GET METHOD - All category
export const getAllCategory = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/category`)
    if (res.status === 200) {
        return await res.json()
    } else {
        return console.log('err');
    }
}

// GET METHOD - Event category by id
export const getEventCategoryById = async (id) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/category/${id}`)
    if (res.status === 200) {
        return await res.json()
    } else {
        return console.log('err');
    }
}

// POST METHOD - Create event
export const createEvent = async (
    name, email, date, startTime, duration, categoryId, notes) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`,{
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
        console.log('pass');
    } else {
        console.log('err');
    }
}

// DELETE METHOD - Delete event by id
export const deleteEventById = async (id) => {
    await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`, {
        method: 'DELETE'
    })
}
