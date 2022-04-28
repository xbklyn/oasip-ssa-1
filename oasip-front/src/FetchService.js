const getMySchedule = async() => {
    const res = await fetch('http://localhost:9999/events')
    if (res.status === 200) {
        return await res.json();
    } else {
        console.log('err');
    }
}

export { getMySchedule };