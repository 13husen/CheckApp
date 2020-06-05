package com.designer.checkapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designer.checkapp.R;
//import com.designer.checkapp.helper.ApiService;
import com.designer.checkapp.helper.ApiService;
import com.designer.checkapp.helper.PostResponse;
import com.designer.checkapp.helper.UtilsApi;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText etNama;
    EditText etEmail;
    EditText etPassword;
    Button btnRegister;
    TextView txtLogin;
    ProgressDialog loading;

    Context mContext;
//    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
//        mApiService = UtilsApi.getAPIService();
        initComponents();
    }

    private void initComponents(){
        etNama = findViewById(R.id.etNama);
        etEmail =  findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestRegister();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void requestRegister(){
                  String nama = etNama.getText().toString();
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
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", pass);
        jsonObject.addProperty("username", nama);

        ApiService service = retrofit.create(ApiService.class);
        Call<PostResponse> call = service.registerData(jsonObject);
        //calling the api
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                //hiding progress dialog
                loading.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getStatusCode().equals("2110")){
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
