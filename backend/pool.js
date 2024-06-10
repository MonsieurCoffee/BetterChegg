require("dotenv").config();
const { Pool } = require("pg");

const pool = new Pool({
    host: process.env.PGHOST,
    database: process.env.PGDATABASE,
    username: process.env.PGUSER,
    password: process.env.PGPASSWORD,
    port: process.env.PGPORT,

    ssl: {
        require: true
    }
});

pool.connect().then(() => {
    console.log("CONNECTION: POSTGRESQL");
});

module.exports = pool;