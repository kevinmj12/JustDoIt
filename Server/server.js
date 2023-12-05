// server
const express = require('express');
const app = express();
const port = 3123;
const bcrypt = require("bcrypt");
const session = require('express-session');
var mysql = require("mysql");

app.use(express.json());

//세션 설정
app.use(session({
    secret: 'secret_key', // 세션을 암호화하기 위한 비밀키
    resave: false,
    saveUninitialized: true
}));

var db = mysql.createConnection({
    host: "127.0.0.1",
    user: "root",
    password: "pjpj5027!!",
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
app.post("/signup", async (req, res) => {
    const { user_name, user_id, user_pw, user_birthday, user_email } = req.body;

    // 비밀번호가 제공되었는지 확인
    if (!user_pw) {
        return res.status(400).send("비밀번호가 제공되지 않았습니다.");
    }

    try {
        //const hashedPassword = await bcrypt.hash(user_pw, 10); // 비밀번호 암호화
        db.query("INSERT INTO user_info(user_name, user_id, user_pw, user_birthday, user_email) VALUES (?, ?, ?, ?, ?);",
            [user_name, user_id, user_pw, user_birthday, user_email],
            function (error, result) {
                if (error) {
                    console.log(error);
                    return res.status(500).send(error.code);
                } else {
                    res.json({ msg: "success" });
                }
            }
        );
    } catch (err) {
        console.error(err);
        res.status(500).send("서버 오류");
    }
});


app.post("/login", (req, res) => {
    const { user_name, user_pw } = req.body;
    console.log("Received login request:", req.body);

    if (user_name && user_pw) {
        db.query("SELECT * FROM user_info WHERE user_name = ?", [user_name], function (error, results, fields) {
            if (error) {
                console.log(error);
                return res.status(500).send("Internal Server Error");
            }

            if (results.length > 0) {
                if (user_pw === results[0].user_pw) {
                    // 로그인 성공 처리
                    req.session.is_logined = true;
                    req.session.user_name = user_name;
                    res.json({ isLogin: "True" });
                } else {
                    res.json({ isLogin: "로그인 정보가 일치하지 않습니다." });
                }
            } else {
                res.json({ isLogin: "아이디 정보가 일치하지 않습니다." });
            }
        });
    } else {
        res.json({ isLogin: "아이디와 비밀번호를 입력하세요!" });
    }
});


// 로그아웃
app.get('/logout', function (req, res) {
    req.session.destroy(function (err) {
        if(err) {
            console.log(err);
            return res.status(500).send("Internal Server Error");
        }
        res.redirect('/');
    });
});

// 로그인 체크
app.get('/authcheck', (req, res) => {
    const sendData = { isLogin: "" };
    if (req.session.is_logined) {
        sendData.isLogin = "True";
    } else {
        sendData.isLogin = "False";
    }
    res.send(sendData);
})

app.listen(port, () => {
    console.log(`Connected at ${port}`);
});