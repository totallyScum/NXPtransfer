package com.zthl.nxp.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zthl.nxp.MainActivity;
import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.constant.UrlConstant;
import com.zthl.nxp.model.request.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.presenter.LoginResponseBodyPresenter;
import com.zthl.nxp.presenterView.LoginResponsePv;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private LoginResponseBodyPresenter mLoginResponseBodyPresenter =new LoginResponseBodyPresenter(this);
    private EditText usernameEditText;
    private TextView setServerIP;
    private AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        usernameEditText.setText(sharedPreferences.getString("account",""));


        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        setServerIP=findViewById(R.id.change_server_ip);
        mLoginResponseBodyPresenter.onCreate();
        mLoginResponseBodyPresenter.BindPresentView(mUserInfoPv);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(usernameEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest=new LoginRequest();
                loginRequest.setLoginName(usernameEditText.getText().toString());
                loginRequest.setLoginPassword(passwordEditText.getText().toString());
                mLoginResponseBodyPresenter.getLoginResponseInfo(loginRequest);

            }
        });
        setServerIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInput();
            }
        });
    }


    private void showInput() {
        final EditText editText = new EditText(this);
        builder = new AlertDialog.Builder(this).setTitle("输入服务器IP").setView(editText)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LoginActivity.this, "IP地址为：" + editText.getText().toString()
                                , Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
                        //步骤2： 实例化SharedPreferences.Editor对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //步骤3：将获取过来的值放入文件
                        editor.putString("IP",editText.getText().toString());
                        editor.commit();
                        UrlConstant.setBaseUrl(editText.getText().toString());
                    }
                });
        builder.create().show();
    }


    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }


    private LoginResponsePv mUserInfoPv = new LoginResponsePv(){
        @Override
        public void onSuccess(LoginResponseBody loginResponseBody) {
            Log.d("2134",loginResponseBody.toString());
            if (loginResponseBody.getState()==1)
            {
                //步骤1：创建一个SharedPreferences对象
                SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
                //步骤2： 实例化SharedPreferences.Editor对象
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //步骤3：将获取过来的值放入文件
                editor.putString("account",usernameEditText.getText().toString());
                editor.putString("pkId",loginResponseBody.getAccount().getPkID());
                editor.putString("role",loginResponseBody.getAccount().getRole());
                MyApplication.setAccount(usernameEditText.getText().toString());
                MyApplication.setPkId(usernameEditText.getText().toString());
                MyApplication.setRole(loginResponseBody.getAccount().getRole());
                MyApplication.setPkId(loginResponseBody.getAccount().getPkID());
                Log.d("pkId",loginResponseBody.getAccount().getPkID());
                //步骤4：提交
                editor.commit();

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            if (loginResponseBody.getState()==0)
            {
                Toast.makeText(getApplicationContext(),"用户不存在",Toast.LENGTH_LONG);
            }
        }


        @Override
        public void onError(String result) {
            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
        }
    }

    ;

}
