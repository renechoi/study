// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import "firebase/auth"
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyBPpkwF9Bk7WOjsx4C5CbHAeqcCFid7pFQ",
    authDomain: "disney-react-app-635e8.firebaseapp.com",
    projectId: "disney-react-app-635e8",
    storageBucket: "disney-react-app-635e8.appspot.com",
    messagingSenderId: "330690354027",
    appId: "1:330690354027:web:9a378871220a13fe9dca2e",
    measurementId: "G-7W7RMGXB32"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export default app;