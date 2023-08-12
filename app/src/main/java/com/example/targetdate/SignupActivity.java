package com.example.targetdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.targetdate.activity.HomeActivity;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.dbutils.DBAction;
import com.example.targetdate.models.RegistrationCore;
import com.example.targetdate.models.UserLoginDetails;
import com.example.targetdate.util.DBUtil;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
       // hideBottomLayoutWhileKeyBoardOpen();
        actionEvents();
    }

    private void hideBottomLayoutWhileKeyBoardOpen() {
        RelativeLayout rootView =((RelativeLayout)findViewById(R.id.root_view));
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);

                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                    //ok now we know the keyboard is up...
                    ((LinearLayout)findViewById(R.id.goto_login_layout)).setVisibility(View.GONE);


                } else {
                    //ok now we know the keyboard is down...
                    ((LinearLayout)findViewById(R.id.goto_login_layout)).setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void actionEvents() {
        ((LinearLayout)findViewById(R.id.goto_login_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginPage();
            }
        });
        ((Button)findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkMandatoryFields()){
                    saveData();
                }
            }
        });
    }

    private void saveData() {
        RegistrationCore registrationCore = new RegistrationCore();
        registrationCore.setUserName(((EditText)findViewById(R.id.inputUsername)).getText().toString());
        registrationCore.setEmail(((EditText)findViewById(R.id.inputEmail)).getText().toString());
        registrationCore.setMobileNumber(((EditText)findViewById(R.id.inputMobile)).getText().toString());
        registrationCore.setPassword(((EditText)findViewById(R.id.inputPassword)).getText().toString());

     RegistrationCore registrationCore1=   DBUtil.getInstance(getApplicationContext()).getValuesFromTable("userName = '"+((EditText)findViewById(R.id.inputUsername)).getText().toString()+"'",RegistrationCore.class);
        RegistrationCore registrationCore2=   DBUtil.getInstance(getApplicationContext()).getValuesFromTable("email = '"+((EditText)findViewById(R.id.inputEmail)).getText().toString()+"'",RegistrationCore.class);
        RegistrationCore registrationCoreFormobile=   DBUtil.getInstance(getApplicationContext()).getValuesFromTable("mobileNumber = '"+((EditText)findViewById(R.id.inputMobile)).getText().toString()+"'",RegistrationCore.class);
        if(registrationCore1!=null){
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.user_name_exist), ((EditText) findViewById(R.id.inputUsername)));
        }else if (registrationCore2!=null){
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.email_exist), ((EditText) findViewById(R.id.inputEmail)));
        }else if(registrationCoreFormobile!=null){
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.mobile_exist), ((EditText) findViewById(R.id.inputMobile)));
        }

        else{
            int id = DBUtil.getInstance(getApplicationContext()).insertOrUpdateTable(registrationCore, DBAction.INSERT,null);

             if(id > 0){
                 GlobalMethods.showNormalToast(SignupActivity.this,getString(R.string.registration_success), 0);
                 callHomeScreen(id,registrationCore);
             }else {
                 GlobalMethods.showNormalToast(SignupActivity.this,getString(R.string.registration_un_success), 0);
             }
        }

    }

    private void callHomeScreen(int userId,RegistrationCore core) {
        DBUtil.getInstance(getApplicationContext()).dropTable(UserLoginDetails.class);
        UserLoginDetails userLoginDetails = new UserLoginDetails();
        userLoginDetails.setUserId(userId);
        userLoginDetails.setUserName(core.getUserName());
        userLoginDetails.setEmail(core.getEmail());
        userLoginDetails.setMobileNumber(core.getMobileNumber());
        userLoginDetails.setPassword(core.getPassword());
        userLoginDetails.setRemember(false);
       int loginUserID = DBUtil.getInstance(getApplicationContext()).insertOrUpdateTable(userLoginDetails,DBAction.INSERT,null);
        if(loginUserID>0) {
            Intent i = new Intent(SignupActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean checkMandatoryFields() {
        if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputUsername)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputUsername)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputEmail)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if (!GlobalMethods.isValidEmail(((EditText) findViewById(R.id.inputEmail)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.invalidate_email), ((EditText) findViewById(R.id.inputEmail)));
            return false;


        }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputMobile)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputMobile)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputPassword)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputPassword)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.inputConfirmPassword)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.inputConfirmPassword)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if (!((EditText) findViewById(R.id.inputPassword)).getText().toString().equals(((EditText) findViewById(R.id.inputConfirmPassword)).getText().toString())) {
            GlobalMethods.setEditextError(SignupActivity.this, getString(R.string.editext_validatePassword), ((EditText) findViewById(R.id.inputConfirmPassword)));//  user_firstname.setError(getString(R.string.validate_editext));
            return false;

        }else if(!((CheckBox)findViewById(R.id.terms_and_condition_check)).isChecked()){
            GlobalMethods.showNormalToast(SignupActivity.this, getString(R.string.please_select_trerms_and_conditions), 0);//  user_firstname.setError(getString(R.string.validate_editext));
            return false;
        }


        else{
            return  true;
        }
    }

    private void callLoginPage() {

        finish();
    }
}