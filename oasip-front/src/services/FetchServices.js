//GET All event
export const getAllEvents = async() => {
    const res = await fetch('http://10.4.56.107:8080/api/events')
    if (res.status === 200) {
        console.log(res.status);
        return await res.json();
    } else {
        console.log('Cannot fetch from database');
    }
}

// GET Event by id
export const getEventById = async(id) => {
    const res = await fetch(`http://10.4.56.107:8080/api/events/${id}`)
    if (res.status === 200) {
        return await res.json()
    } else {
        console.log('err');
    }
}