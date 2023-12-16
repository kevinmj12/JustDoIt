// server
const express = require('express');
const app = express();
const port = 3123;
const bcrypt = require("bcrypt");
const session = require('express-session');
var mysql = require("mysql");
const cors = require("cors");
const bodyParser = require("body-parser");

var db = mysql.createConnection({
    host: "127.0.0.1",
    user: "root",
    password: "sunnylee123",
    database: "justdoit",
    port: "3306",
})


db.connect();


app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// ���� ����
app.use(session({
    secret: 'secret_key', // ������ ��ȣȭ�ϱ� ���� ���Ű
    resave: false,
    saveUninitialized: true
}));

app.get('/', function(req, res){
    res.send("JustDoIt");
});


// ������ To-DO ����
app.get("/todo/:user_id", (req, res) =>{
    db.query(`SELECT * FROM todo WHERE user_id = ${req.params.user_id}`, function(error, result){
        if (error){
            console.log("error");
            console.log(error);
        }
        console.log(result);
        res.json(result);
    })})

// ������ Daily To-DO ����
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

// To-Do �߰�
app.post("/create/todo", (req, res) => {
    const { user_id, todo_name, deadline } = req.body;
    console.log(req.body);

    const query = `INSERT INTO todo (user_id, todo_name, present_progress, deadline) VALUES ('${user_id}', '${todo_name}', 0, '${deadline}');`;

    db.query(query, function (error, result) {
        if (error) {
            console.log(error);
            res.json({ msg: "error" });
        } else {
            res.json({ msg: "success" });
        }
    });
});

// ȸ������
app.post("/signup", async (req, res) => {
    const { user_name, user_id, user_pw, user_birthday, user_email } = req.body;

    // ��й�ȣ�� �����Ǿ����� Ȯ��
    if (!user_pw) {
        return res.status(400).send("��й�ȣ�� �������� �ʾҽ��ϴ�.");
    }

    try {
        //const hashedPassword = await bcrypt.hash(user_pw, 10); // ��й�ȣ ��ȣȭ
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
        res.status(500).send("���� ����");
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
                    // �α��� ���� ó��
                    req.session.is_logined = true;
                    req.session.user_name = user_name;
                    res.json({ isLogin: "True" });
                } else {
                    res.json({ isLogin: "�α��� ������ ��ġ���� �ʽ��ϴ�." });
                }
            } else {
                res.json({ isLogin: "���̵� ������ ��ġ���� �ʽ��ϴ�." });
            }
        });
    } else {
        res.json({ isLogin: "���̵�� ��й�ȣ�� �Է��ϼ���!" });
    }
});


// �α׾ƿ�
app.get('/logout', function (req, res) {
    req.session.destroy(function (err) {
        if(err) {
            console.log(err);
            return res.status(500).send("Internal Server Error");
        }
        res.redirect('/');
    });
});

// �α��� üũ
app.get('/authcheck', (req, res) => {
    const sendData = { isLogin: "" };
    if (req.session.is_logined) {
        sendData.isLogin = "True";
    } else {
        sendData.isLogin = "False";
    }
    res.send(sendData);
})

// To-Do ����
app.post("/delete", (req, res) => {
    const { todo_name } = req.body;
    console.log(req.body);
    db.query(`DELETE FROM todo WHERE todo_name = ?;`, [todo_name], function (error, result) {
        if (error) {
            console.log(error);
            res.json({ msg: "error" });
        } else {
            res.json({ msg: "success" });
        }
    }
    );
}
);

// To-DO ����
app.post("/edit/todo", (req, res) => {
    const { user_id, todo_name, deadline } = req.body;
    console.log(req.body);
    const query = `UPDATE todo SET deadline = ?, todo_name = ? WHERE user_id = ?;`;
    db.query(query, [deadline, todo_name, user_id], function (error, result) {
        if (error) {
            console.log(error);
            res.json({ msg: "error" });
        } else {
            res.json({ msg: "success" });
        }
    });
});


app.listen(port, () => {
    console.log(`Connected at ${port}`);
});