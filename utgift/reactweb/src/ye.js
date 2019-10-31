/*const express = require("express");
const request = require("request");

const e = express();

e.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    next();
});

e.get("/utgiftlist", (req, res) => {
    request(
        { url: "http://localhost:8080/utgiftlist" },
        (error, response, body) => {
            if (error || response.statusCode !== 200) {
                return res
                    .status(500)
                    .json({ type: "error", message: err.message });
            }

            res.json(JSON.parse(body));
        }
    );
});

const PORT = process.env.PORT || 3001;
e.listen(PORT, () => console.log(`listening on ${PORT}`));
*/
