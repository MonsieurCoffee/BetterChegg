const pool = require("../pool");

async function register(req, res) {
    const {username, email, password} = req.body;

    try{
        const result = await pool.query(
            'INSERT INTO users (username, email, password) VALUES ($1, $2, $3) RETURNING *',
            [username, email, password]
        );

        const newMember = result.rows;
        res.status(200).json(newMember);
    }
    catch(error){
        console.log(error);
        res.status(500).json({error: "Internal Server Error"});
    }
}

module.exports = {
    register
}