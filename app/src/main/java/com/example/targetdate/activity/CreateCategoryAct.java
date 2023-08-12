package com.example.targetdate.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.targetdate.R;
import com.example.targetdate.SignupActivity;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.custom.TimePickerFragment;
import com.example.targetdate.dbutils.DBAction;
import com.example.targetdate.models.Categories;
import com.example.targetdate.models.UserLoginDetails;
import com.example.targetdate.util.DBUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;

public class CreateCategoryAct extends AppCompatActivity {

    private AppCompatImageView ic_back;
    private TextView text_title;
    private EditText category;
    private EditText title;
    private TextView start_date;
    private TextView preferred_time;
    private EditText period;
    private Button create;
    private TextView choose_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        initView();
        actionEvents();


    }

    private void actionEvents() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackActivity();
            }
        });
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalMethods.hideKeyboard(CreateCategoryAct.this);
                showDateDilaouge();
            }
        });
        preferred_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalMethods.hideKeyboard(CreateCategoryAct.this);
                showTimeDilaouge();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkMandatoryFields()){
                    saveCategoryData();
                }
            }


        });
        choose_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CreateCategoryAct.this)
                        .crop()        //Crop image(Optional), Check Customization for more option
                        .compress(1024)   //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080) //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }

    private boolean checkMandatoryFields() {

            if (!GlobalMethods.isNull(((EditText) findViewById(R.id.category)).getText().toString())) {
                GlobalMethods.setEditextError(CreateCategoryAct.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.category)));//  user_firstname.setError(getString(R.string.validate_editext));
                return false;

            }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.title)).getText().toString())) {
                GlobalMethods.setEditextError(CreateCategoryAct.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.title)));//  user_firstname.setError(getString(R.string.validate_editext));
                return false;




            }else if (!GlobalMethods.isNull(((TextView) findViewById(R.id.start_date)).getText().toString())) {
               // GlobalMethods.setEditextError(CreateCategoryAct.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.start_date)));//  user_firstname.setError(getString(R.string.validate_editext));
                GlobalMethods.showNormalToast(CreateCategoryAct.this,getString(R.string.please_select_date),0);
                return false;

            }else if (!GlobalMethods.isNull(((EditText) findViewById(R.id.period)).getText().toString())) {
                GlobalMethods.setEditextError(CreateCategoryAct.this, getString(R.string.validate_editext), ((EditText) findViewById(R.id.period)));//  user_firstname.setError(getString(R.string.validate_editext));
                return false;

            }else if (!GlobalMethods.isNull(((TextView) findViewById(R.id.choose_icon)).getText().toString())) {
              //  GlobalMethods.setEditextError(CreateCategoryAct.this, getString(R.string.validate_editext), ((TextView) findViewById(R.id.choose_icon)));//  user_firstname.setError(getString(R.string.validate_editext));
                GlobalMethods.showNormalToast(CreateCategoryAct.this,getString(R.string.please_choose_icon),0);
                return false;

            }


            else{
                return  true;
            }

    }

    private void saveCategoryData() {
        UserLoginDetails userLoginDetails = DBUtil.getInstance(getApplicationContext()).getValuesFromTable(null,UserLoginDetails.class);
        Categories categories = new Categories();
        categories.setCategory(category.getText().toString());
        categories.setTitle(title.getText().toString());
        categories.setStartDate(start_date.getText().toString());
        categories.setPreferredTime(preferred_time.getText().toString());
        categories.setPeriod(period.getText().toString());
        categories.setUserId(userLoginDetails!=null?userLoginDetails.getUserId():0);
        categories.setIcon(choose_icon.getText().toString());

        int id = DBUtil.getInstance(getApplicationContext()).insertOrUpdateTable(categories, DBAction.INSERT,null);
        if(id>0){
            GlobalMethods.showNormalToast(CreateCategoryAct.this,getString(R.string.add_category_success), 0);
           callBackActivity();
        }else{
            GlobalMethods.showNormalToast(CreateCategoryAct.this,getString(R.string.add_category_un_success), 0);
        }

    }

    private void showTimeDilaouge() {
//        Calendar mcurrentTime = Calendar.getInstance();
//        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//        int minute = mcurrentTime.get(Calendar.MINUTE);
//        TimePickerDialog mTimePicker;
//        mTimePicker = new TimePickerDialog(CreateCategoryAct.this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                preferred_time.setText(selectedHour + ":" + selectedMinute + ":00");
//            }
//        }, hour, minute, true);//Yes 24 hour time
//        mTimePicker.setTitle("Select Time");
//        mTimePicker.show();
        DialogFragment dFragment = new TimePickerFragment();

        // Show the time picker dialog fragment
        // dFragment.setStyle(R.style.AppTheme,0);
        dFragment.show(getFragmentManager(), "Time Picker");
    }


    private void callBackActivity() {
        Intent intent = new Intent(CreateCategoryAct.this,HomeActivity.class);
        startActivity(intent);
        finish();

    }
    private void showDateDilaouge() {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateCategoryAct.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        start_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);



                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Select Date");
        //datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    private void initView() {
        ic_back = (AppCompatImageView)findViewById(R.id.ic_back);
        text_title = (TextView)findViewById(R.id.text_title);

        Bundle b= getIntent().getExtras();
        if(b!=null){
            if(b.containsKey("title")){
                text_title.setText(b.getString("title"));
            }
        }
        category = (EditText)findViewById(R.id.category);
        title = (EditText)findViewById(R.id.title);
        start_date = (TextView)findViewById(R.id.start_date);
        preferred_time = (TextView)findViewById(R.id.preferred_time);
        period = (EditText)findViewById(R.id.period);
        create=(Button)findViewById(R.id.create);
        choose_icon = (TextView) findViewById(R.id.choose_icon);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callBackActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
             Uri imageURo = data.getData();
             choose_icon.setText(imageURo.toString());
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}