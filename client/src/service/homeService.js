import axios from "axios";

const baseUrl = process.env.NEXT_PUBLIC_SERVER_PORT;

const getFeed = async ()=>{
    return await axios.get(`${baseUrl}/home/`,{
    //     headers:{
    //     'Authorization':'Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjY2MTBlMWM2MDk4YTEyMDA2OWZiMzQ0YSIsImVtYWlsIjoicmlzaGFiaEB0ZXN0Iiwic3ViIjoiUmlzaGFiaCIsImlhdCI6MTcxMjM4MjQyNCwiZXhwIjoxNzEyNDAwNDI0fQ.qmHY4sbZdtfO55zQk4sUwQmdoF0Rm189ikPoWKDDJl5gHoKJkMr-Upn9zRp9nY6mOBMPkcg74EN6tM3Gzy1Yow'
    // }
    withCredentials:true
}
    );
}
const likePost = async (postId,isLiked)=>{
    return await axios.post(`${baseUrl}/home/like/${postId}`,{isLiked});
}
const commentPost = async (postId,comment)=>{
    return await axios.post(`${baseUrl}/home/comment/${postId}`,{comment});
}
export {
    getFeed,
    commentPost,
    likePost
}