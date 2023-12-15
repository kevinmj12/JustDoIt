package com.example.justdoit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    TextView back;
    EditText name,id,pw,pw2,email,birthyear,birthdate,birthday;
    Button pwcheck, submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //뒤로 가기 버튼
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed() );

        //기입 항목
        name = findViewById(R.id.signName);
        id=findViewById(R.id.signID);
        pw=findViewById(R.id.signPW);
        pw2=findViewById(R.id.signPW2);
        email=findViewById(R.id.signmail);
        birthyear=findViewById(R.id.signBirth);
        birthdate=findViewById(R.id.signBirth2);
        birthday=findViewById(R.id.signBirth3);

        //비밀번호 확인 버튼
        pwcheck = findViewById(R.id.pwcheckbutton);
        pwcheck.setOnClickListener(v -> {
            if(pw.getText().toString().equals(pw2.getText().toString())){
                pwcheck.setText("일치");
            }else{
                Toast.makeText(Signup.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
        });
        //회원가입 완료 버튼
        submit = findViewById(R.id.signupbutton);
        submit.setOnClickListener(v -> sendSignupRequest());
        }
    private void sendSignupRequest() {
        String url = "http://10.0.2.2:3123/signup";

        // 사용자 입력 데이터 가져오기
        String userName = name.getText().toString();
        String userId = id.getText().toString();
        String userPw = pw.getText().toString();
        String userEmail = email.getText().toString();
        String userBirthday = birthyear.getText().toString() + "-" + birthdate.getText().toString() + "-" + birthday.getText().toString();

        // JSON으로 파일 만들기
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("user_name", userName);
            parameters.put("user_id", userId);
            parameters.put("user_pw", userPw);
            parameters.put("user_birthday", userBirthday);
            parameters.put("user_email", userEmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // POST 요청 생성
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters,
                response -> {
                    Toast.makeText(Signup.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Login.class);
                    finish();
                },
                error -> Toast.makeText(Signup.this, "회원가입 실패: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        // 요청 큐에 추가
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
