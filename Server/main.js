const express = require('express');
const app = express();
const port = 3000;

app.get('/', function(req, res){
    res.send("Test");
});

app.get("/aaaaa", (req, res) =>{
    console.log("aaa");
    res.json("id: aaaaaaaa");
})





app.listen(port, () => {
    console.log("Connected at 3000");
});