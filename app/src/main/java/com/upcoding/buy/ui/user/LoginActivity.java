package com.upcoding.buy.ui.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.upcoding.buy.R;
import com.upcoding.buy.databinding.ActivityLoginBinding;
import com.upcoding.buy.ui.ToolbarActivity;

/**
 * Created by Heboot on 2017/5/22.
 */

public class LoginActivity extends ToolbarActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    public void login(View view) {
        if (!validate()) {
            onLoginFailed();
        }
    }

    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//        _loginButton.setEnabled(true);
    }

    @Override
    protected void initData() {

    }

    public boolean validate() {
        boolean valid = true;

        String username = loginBinding.inputEmail.getText().toString();
        String password = loginBinding.inputPassword.getText().toString();

        if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            loginBinding.inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            loginBinding.inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            loginBinding.inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            loginBinding.inputPassword.setError(null);
        }

        return valid;
    }
}
