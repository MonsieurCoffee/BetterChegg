const pool = require("./pool");

//EXPRESS
const express = require("express");
const bodyParser = require("body-parser");
//MULTER
const multer = require("multer");
const upload = multer({ storage: multer.memoryStorage()});

const app = express();
const port = 1111;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.listen(port, () => {
    console.log("SERVER:", port);
});

//ROUTES
const registerRoute = require("./controllers/registerController");
const loginRoute = require("./controllers/loginController");
const uploadRoute = require("./controllers/uploadController");

app.post("/register", registerRoute.register);
app.post("/login", loginRoute.login);

app.post("/upload", upload.single("pdf"), (req, res) => {
    
    const storageRef = uploadRoute.ref(uploadRoute.getStorage(), req.file.originalname);
    uploadRoute.uploadBytes(storageRef, req.file.buffer)
    .then((snapshot) => {
        console.log("file uploaded");
    });
});
