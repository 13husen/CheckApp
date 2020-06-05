package com.designer.checkapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designer.checkapp.R;
import com.designer.checkapp.helper.ApiService;
import com.designer.checkapp.helper.PostResponse;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    TextView regText;
    Button btnLogin,btnBack;
    ProgressDialog loading;

    Context mContext;
    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mContext = this;
//        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
    }

    private void  initComponents(){

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        regText = findViewById(R.id.gotoregist);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


    }



    private void requestLogin(){
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://18.141.178.15:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", email);
        jsonObject.addProperty("password", pass);

        ApiService service = retrofit.create(ApiService.class);
        Call<PostResponse> call = service.loginData(jsonObject);
        //calling the api
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                //hiding progress dialog
                loading.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getStatusCode().equals("2110")){
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
//                    Toast.makeText(getApplicationContext(), "Post submitted Title: "+response.body().getStatusCode()+" Body: "+response.body().getMessage()+" PostId: "+response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
