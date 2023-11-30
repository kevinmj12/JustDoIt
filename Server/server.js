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
app.get("/todo/1", (req, res) =>{
    db.query(`SELECT * FROM todo WHERE user_id = ${req.params.user_id}`, function(error, result){
        if (error){
            console.log("error");
            console.log(error);
        }
        console.log(result);
        res.json(result);
    })})

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

// 회원가입
app.post("/signup", (req, res) => {
    db.query(`INSERT INTO user_info(user_name, user_id, user_pw, user_birthday, user_email) VALUES ('cheolsu', 'abc123','abc2134' , '2000-01-01', 'abc@abc.com');`,
        function (error, result) {
            if (error) {
                console.log(error);
                res.send(error.code);
            
            } else {
                res.json({ msg: "success" });
            }
        }
    );
    console.log(req.body);
});


app.listen(port, () => {
    console.log(`Connected at ${port}`);
});