const admin = require('firebase-admin');

async function downloadLink(filename){
    const options = {
        version: "v2",
        action: "read",
        expires: Date.now() + 1000 * 60 * 60
    };

}