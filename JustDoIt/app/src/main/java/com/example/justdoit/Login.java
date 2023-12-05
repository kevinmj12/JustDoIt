package com.example.justdoit;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.android.volley.Request;
        import com.android.volley.toolbox.Volley;
        import com.android.volley.toolbox.JsonObjectRequest;
        import org.json.JSONException;
        import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private static final String LOGIN_URL = "http://10.0.2.2:3123/login";
    EditText editID, ediPassword;
    TextView sign;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = findViewById(R.id.editID);
        ediPassword = findViewById(R.id.ediPassword);
        loginButton = findViewById(R.id.loginbutton);

        //회원가입 버튼
        sign = findViewById(R.id.signin);

        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String userId = editID.getText().toString();
        String password = ediPassword.getText().toString();
        String url = LOGIN_URL;

        JSONObject loginParams = new JSONObject();
        try {
            loginParams.put("user_name", userId);
            loginParams.put("user_pw", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, loginParams,
                response -> {
                    // 서버 응답 처리
                    if (response.toString().contains("True")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    // 에러 처리
                    Toast.makeText(Login.this, "네트워크 오류가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                }
        );

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}