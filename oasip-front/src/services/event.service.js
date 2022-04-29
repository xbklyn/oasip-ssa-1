export const getAllEvents = async ()  => {
    const res = await fetch('http://oasipbe/api/events')
    if (res.status === 200) {
        console.log(res.status);
        return await res.json();
    } else {
        console.log('Cannot fetch from database');
    }
}

