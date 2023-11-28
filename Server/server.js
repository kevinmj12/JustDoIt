// server
const express = require('express');
const app = express();
const port = 3123;
var mysql = require("mysql");

var db = mysql.createConnection({
    host: "127.0.0.1",
    user: "root",
    password: "PASSWORD", // 개인 MySQL 비밀번호 입력할 것
    database: "justdoit",
    port: "3306",
})

db.connect();

app.get('/', function(req, res){
    res.send("JustDoIt");
});


// 유저별 To-DO 리턴
app.get("/todo/:user_id", (req, res) =>{
    db.query(`SELECT * FROM todo WHERE user_id = ${req.params.user_id}`, function(error, result){
        if (error){
            console.log("error");
            console.log(error);
        }
        console.log(result);
        res.json(result);
    })
})

// 유저별 Daily To-DO 리턴
app.get("/dailytodo/:user_id", (req, res) =>{
    db.query(`SELECT * FROM daily_todo WHERE user_id = ${req.params.user_id}`, function(error, result){
        if (error){
            console.log("error");
            console.log(error);
        }
        console.log(result);
        res.json(result);
    })
})


app.listen(port, () => {
    console.log(`Connected at ${port}`);
});