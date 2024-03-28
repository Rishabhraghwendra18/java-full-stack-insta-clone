import axios from "axios";

const baseUrl = process.env.NEXT_PUBLIC_SERVER_PORT;

const signUp = async (userData)=>{
    return await axios.post(`${baseUrl}/user/sign-up`,userData);
}
const signIn = async (userData)=>{
    return await axios.post(`${baseUrl}/user/sign-in`,userData);
}
export {signIn,signUp}