package com.example.targetdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.targetdate.activity.HomeActivity;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.dbutils.DBAction;
import com.example.targetdate.models.RegistrationCore;
import com.example.targetdate.models.UserLoginDetails;
import com.example.targetdate.util.DBUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actionEvents();
        checkIsRememberEnable();
    }

    private void checkIsRememberEnable() {
        UserLoginDetails userLoginDetails = DBUtil.getInstance(getApplicationContext()).getValuesFromTable(null,UserLoginDetails.class);
        if(userLoginDetails!=null){
            if(userLoginDetails.isRemember()){
                ((EditText)findViewById(R.id.inputEmail)).setText(userLoginDetails.getEmail());
                ((EditText)findViewById(R.id.inputPassword)).setText(userLoginDetails.getPassword());
                ((CheckBox)findViewById(R.id.reminder_me)).setChecked(true);
            }
        }
    }

    private void actionEvents() {
        ((LinearLayout) findViewById(R.id.gotoRegister_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSignupPage();
            }
        });

        ((TextView) findViewById(R.id.forgot_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callForgotPasswordPage();
            }
        });

        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkMandatoryFields()) {
                    validateLogin();
                }
            }
        });
    }

    private void validateLogin() {
        RegistrationCore registrationCore2=   DBUtil.getInstance(getApplicationContext()).getValuesFromTable("email = '"+((EditText)findViewById(R.id.inputEmail)).getText().toString()+"' AND password = '"+((EditText) findViewById(R.id.inputPassword)).getText().toString()+"'",RegistrationCore.class);
     if(registrationCore2!=null){
         callHomeScreen(registrationCore2);
     }else{
         GlobalMethods.showNormalToast(LoginActivity.this, getResources().getString(R.string.invalid_login_or_password), 0);
     }

    }

    private boolean checkMandatoryFields() {
        if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(LoginActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputEmail)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;
        } else if (!GlobalMethods.isValidEmail(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(LoginActivity.this, getString(R.string.invalidate_email), ((EditText) findViewById(R.id.inputEmail)));
            return false;
        } else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputPassword)).getText().toString())) {
            GlobalMethods.setEditextError(LoginActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputPassword)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;
        } else {
            return true;
        }
    }

    private void callForgotPasswordPage() {
        Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(i);
    }

    private void callSignupPage() {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
        //finish();
    }

    private void callHomeScreen( RegistrationCore core) {
        DBUtil.getInstance(getApplicationContext()).dropTable(UserLoginDetails.class);
        UserLoginDetails userLoginDetails = new UserLoginDetails();
        userLoginDetails.setUserId(core.get_id());
        userLoginDetails.setUserName(core.getUserName());
        userLoginDetails.setEmail(core.getEmail());
        userLoginDetails.setMobileNumber(core.getMobileNumber());
        userLoginDetails.setPassword(core.getPassword());
        if(((CheckBox)findViewById(R.id.reminder_me)).isChecked()){
            userLoginDetails.setRemember(true);
        }else{
            userLoginDetails.setRemember(false);
        }

        int loginUserID = DBUtil.getInstance(getApplicationContext()).insertOrUpdateTable(userLoginDetails, DBAction.INSERT, null);
        if (loginUserID > 0) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }
}