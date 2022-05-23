//GET METHOD - All event
export const getAllEvents = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`)
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

// GET METHOD - Event by category and date
export const getEventByCatAndDate = async (category, date) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${category}/${date}`)
    if (res.status === 200) {
        return await res.json()
    } else {
        return console.log('err');
    }
}

// POST METHOD - Create event
export const createEvent = async (
    name, email, startTime, categoryId, notes) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`,{
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            bookingName: name,
            bookingEmail: email,
            eventStartTime: startTime,
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
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`, {
        method: 'DELETE'
    })
    return res.status
}

// PUT METHOD - Edit event by id
export const editEventById = async (replace) => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${replace.id}`, {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            eventNotes:replace.note,
            eventStartTime:replace.time
        })
    })
    return res.status
}

//PUT METHOD - Edit category by id
export const editCategoryById = async(category) => {
    const res = await fetch (`${import.meta.env.VITE_BASE_URL}/category/${category.categoryId}`, {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            eventCategoryName:category.eventCategoryName,
            eventCategoryDuration: category.eventCategoryDuration,
            eventCategoryDescription:category.eventCategoryDescription
        })
    })
    return res.status
}
