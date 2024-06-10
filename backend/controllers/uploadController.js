require("dotenv").config();

const firebase = require("firebase/app");
const {getStorage, ref, uploadBytes} = require("firebase/storage");

const firebaseConfig = {
    apiKey: process.env.APIKEY,
    authDomain: process.env.AUTHDOM,
    projectId: process.env.PROJID,
    storageBucket: process.env.STORAGEBUCKET,
    messagingSenderId: process.env.MESSAGINGID,
};

firebase.initializeApp(firebaseConfig);


module.exports = {
    getStorage,
    ref,
    uploadBytes
}

