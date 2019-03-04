package com.example.adindariztiaputri.twitternew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adindariztiaputri.twitternew.model.Token;
import com.example.adindariztiaputri.twitternew.remote.APIService;
import com.example.adindariztiaputri.twitternew.remote.APIUtils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mToken;
//    public Token coba;
    private APIService mAPIService;
//    private Token resToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bind id for button login
        Button btn = findViewById(R.id.btn_login);

//        get email & password from edittext
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        mToken = findViewById(R.id.showToken);

//        get API service
        mAPIService = APIUtils.getAPIService();

//        set action when button login is clicked
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = email.getText().toString();
                String loginPassword = password.getText().toString();

                if ((!TextUtils.isEmpty(loginEmail))&&(!TextUtils.isEmpty(loginPassword))){
                    goSignIn(loginEmail, loginPassword);
                }
            }
        });
    }

    public void goSignIn(String email, String password){
        mAPIService.signIn(email, password).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    showResponse(response.body().toString());
                } else{
                    Toast.makeText(MainActivity.this, "masuk ke else. kenapa masuk ke elseeee", Toast.LENGTH_SHORT).show();

//                    showResponse(response.body().toString());
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Signin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showResponse(String response){
        if (mToken.getVisibility() == View.GONE){
            mToken.setVisibility(View.VISIBLE);
        }
        mToken.setText(response);
    }


}
