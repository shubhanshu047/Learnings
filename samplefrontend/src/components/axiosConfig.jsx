import axios from "axios";
import { Navigate } from "react-router-dom";

const api = axios.create({
  baseURL: "http://localhost:8080", // or your API URL
  headers: {
    "Content-Type": "application/json"
  }
});

api.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
    res=>res,
    err=>{
        if(err.response?.status===401){
            sessionStorage.removeItem("token");
            alert("Session expired, please login again");
            window.location.href = "/login";
        }
        return Promise.reject(err);
    }
)

export default api;



