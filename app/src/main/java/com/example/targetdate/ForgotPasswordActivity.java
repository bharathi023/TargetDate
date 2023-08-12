package com.example.targetdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.models.RegistrationCore;
import com.example.targetdate.util.DBUtil;

public class ForgotPasswordActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        builder = new AlertDialog.Builder(this);
        actionEvents();
    }
    private void actionEvents() {
        ((LinearLayout)findViewById(R.id.goto_login_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginPage();
            }
        });


        ((Button)findViewById(R.id.btnSend)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkMandatoryFields()){
                    checkPassword();
                }
            }
        });
    }

    private void checkPassword() {
        RegistrationCore registrationCore2=   DBUtil.getInstance(getApplicationContext()).getValuesFromTable("email = '"+((EditText)findViewById(R.id.inputEmail)).getText().toString()+"'",RegistrationCore.class);

        if(registrationCore2!=null){
            showAlertDialogWithTwoButtons(registrationCore2);

        }else{
            GlobalMethods.showNormalToast(ForgotPasswordActivity.this,getString(R.string.user_not_exist),0);
        }
    }

    private boolean checkMandatoryFields() {
        if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(ForgotPasswordActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputEmail)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;
        } else if (!GlobalMethods.isValidEmail(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(ForgotPasswordActivity.this, getString(R.string.invalidate_email), ((EditText) findViewById(R.id.inputEmail)));
            return false;
        }  else {
            return true;
        }
    }

    private void callLoginPage() {

        finish();
    }

    public void showAlertDialogWithTwoButtons(RegistrationCore core)  {
        try {

            builder.setMessage("Your password is : "+core.getPassword())
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();

                        }
                    });

            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Alert!");
            alert.show();

        } catch (Exception e) {

        }
    }
}