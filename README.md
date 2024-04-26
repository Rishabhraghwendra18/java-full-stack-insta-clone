# Requirements (v1)
- User should be able to sign up and sign in
- User should be able to see other posts on it's home feed with likes, comments and caption.
- User should be able to post photos with caption.

# APIs
- /user
    - [POST] /sign-up
    - [POST] /sign-in
    - [POST] /post
- /home
    - [GET] feed
    - [POST] /like/post-id
    - [POST] comment/post-id

# Local Setup Guide
## For Backend
1. Start a mongodb docker container on port 3000 with username as admin and password as password.
2. Connect to docker container in mongodb compass and create a db called `instagram` with a collection `users`
3. Start the spring boot application
4. Now backend is ready to use!

## For Client
1. The client directory is a Next.js application. Make sure you have the latest LTS version Node.js installed on your machine.
2. Create a .env file in the client folder with the following value:
       ```
           NEXT_PUBLIC_SERVER_PORT= http://localhost:8080
       ```
3. Run `npm install` in client directory
4. After successful installation, run `npm run dev` to run the frontend. Note: Make sure backend is running before running the client directory.

# ER Digram
![image](https://github.com/Rishabhraghwendra18/java-full-stack-insta-clone/assets/43534227/7edd2fea-0da8-4218-a630-4a6274a2fc55)

