import { useRouter } from 'vue-router';
const myRouter = useRouter();

//GET METHOD - All event
export const getAllEvents = async () => {
  let res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  });
  if (res.ok) {
    return await res.json();
  } else {
    if (res.status === 401) {
      await getRefreshToken();
      let retry = await fetch(`${import.meta.env.VITE_BASE_URL}/users/${id}`, {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + localStorage.getItem('access_token'),
        },
      });
      if (retry.ok) {
        return await res.json();
      }
      if (retry.status === 401) {
        myRouter.push('/login');
      }
    }
  }
};

// GET METHOD - Event by id
export const getEventById = async (id) => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  });
  if (res.status === 200) {
    return await res.json();
  } else {
    return res.status;
  }
};

// GET METHOD - All category
export const getAllCategory = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/category`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  });
  if (res.status === 200) {
    return await res.json();
  } else {
    return res.status;
  }
};

// GET METHOD - Event category by id
export const getEventCategoryById = async (id) => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/category/${id}`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('access_token'),
    },
  });
  if (res.status === 200) {
    return await res.json();
  } else {
    return res.status;
  }
};

// GET METHOD - Event by category and date
export const getEventByCatAndDate = async (category, date) => {
  const res = await fetch(
    `${import.meta.env.VITE_BASE_URL}/events/${category}/${date}`,
    {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('access_token'),
      },
    },
  );
  if (res.status === 200) {
    return await res.json();
  } else {
    return res.status;
  }
};

// POST METHOD - Create event
export const createEvent = async (
  name,
  email,
  startTime,
  categoryId,
  notes,
) => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify({
      bookingName: name,
      bookingEmail: email,
      eventStartTime: startTime,
      categoryId: categoryId,
      eventNotes: notes,
    }),
  });
  if (res.status === 201) {
    return res.status;
  } else {
    return res.status;
  }
};

// DELETE METHOD - Delete event by id
export const deleteEventById = async (id) => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/events/${id}`, {
    method: 'DELETE',
  });
  return res.status;
};

// PUT METHOD - Edit event by id
export const editEventById = async (replace) => {
  const res = await fetch(
    `${import.meta.env.VITE_BASE_URL}/events/${replace.id}`,
    {
      method: 'PUT',
      headers: {
        'content-type': 'application/json',
      },
      body: JSON.stringify({
        eventNotes: replace.note,
        eventStartTime: replace.time,
      }),
    },
  );
  return res.status;
};

//PUT METHOD - Edit category by id
export const editCategoryById = async (category) => {
  const res = await fetch(
    `${import.meta.env.VITE_BASE_URL}/category/${category.categoryId}`,
    {
      method: 'PUT',
      headers: {
        'content-type': 'application/json',
      },
      body: JSON.stringify({
        eventCategoryName: category.eventCategoryName,
        eventCategoryDuration: category.eventCategoryDuration,
        eventCategoryDescription: category.eventCategoryDescription,
      }),
    },
  );
  return res.status;
};

export const getRefreshToken = async () => {
  let res = await fetch(`${import.meta.env.VITE_BASE_URL}/auth/refresh_token`, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('refresh_token'),
    },
  }).then(async (res) => {
    if (!res.ok) {
      // localStorage.removeItem('access_token');
      // localStorage.removeItem('refresh_token');
      // localStorage.removeItem('userRole');
      localStorage.clear();
      alert('เวลาของคุณได้หมดลงแล้วกรุณาเติมเงินด้วยค่ะ');
      setTimeout(() => {
        location.reload(1);
      }, 1);
      myRouter.push({ name: 'Home' });
    }
    if (res.ok) {
      let token = await res.json();
      localStorage.setItem('access_token', token.access_token);
      localStorage.setItem('refresh_token', token.refresh_token);
    }
  });
};

// // GET METHOD - Get all users
// export const getAllUsers = async () => {
//   let user;
//   await fetch(`${import.meta.env.VITE_BASE_URL}/users`)
//     .then(async (res) => {
//       if (res.status === 200) return (user = await res.json());
//       throw new Error(res.status);
//     })
//     .catch((e) => {
//       return e.message;
//     });
// };

// // GET METHOD - Get user by Id
// export const getUserById = async (id) => {
//   await fetch(`${import.meta.env.VITE_BASE_URL}/users/${id}`)
//     .then((res) => {
//       if (res.status === 200) return res.json();
//       throw new Error(res.status);
//     })
//     .catch((e) => {
//       return e.message;
//     });
// };

// // POST METHOD - Create new user
// export const postNewUser = async (user) => {
//   await fetch(`${import.meta.env.VITE_BASE_URL}/users`, {
//     method: 'POST',
//     headers: {
//       'content-type': 'application/json',
//     },
//     body: JSON.stringify(user),
//   })
//     .then((res) => {
//       if (res.ok) return res.status;
//       throw new Error(res.status);
//     })
//     .catch((e) => {
//       return e.message;
//     });
// };

// // DELETE METHOD - Delete user
// export const deleteUserById = async (userId) => {
//   await fetch(`${import.meta.env.VITE_BASE_URL}/users/${userId}`, {
//     method: 'DELETE',
//   })
//     .then((res) => {
//       if (res.ok) return res.status;
//       throw new Error(res.status);
//     })
//     .catch((e) => {
//       return e.message;
//     });
// };
