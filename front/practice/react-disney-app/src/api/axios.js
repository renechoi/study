import axios from 'axios';

const instance = axios.create({
    baseURL: "https://api.themoviedb.org/3",
    params: {
        api_key: "12f609e2f47854387f17ed07f9f9c3b0",
        language: "ko-KR",
    },
});

export default instance