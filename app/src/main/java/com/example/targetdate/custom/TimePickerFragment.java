package com.example.targetdate.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.targetdate.R;
import com.example.targetdate.constants.GlobalMethods;

import java.util.Calendar;


/**
 * Created by durgaprasad.polaki on 23/04/18.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get a Calendar instance
        final Calendar calendar = Calendar.getInstance();
        // Get the current hour and minute
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT
                ,this, hour, minute, false);

        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText("Select Time");
        tvTitle.setBackgroundColor(GlobalMethods.getColor(getActivity(), R.color.colorPrimary));
        tvTitle.setPadding(20, 20, 20, 20);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        tvTitle.setTextColor(GlobalMethods.getColor(getActivity(), R.color.white));
        tpd.setCustomTitle(tvTitle);

        // Return the TimePickerDialog
        return tpd;
    }

    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
        // Set a variable to hold the current time AM PM Status
        // Initially we set the variable value to AM
        String status = "AM";

//
        TextView select_time = ((TextView) getActivity().findViewById(R.id.preferred_time));
        // select_time.setText(hour_of_12_hour_format + ":" + minute +"  " + status);



        int hour = selectedHour;
        int minute = selectedMinute;
        String timeSet = "";
        if (selectedHour > 12) {
            selectedHour -= 12;
            timeSet = "PM";
//            timeSet1="p.m.";
        } else if (selectedHour == 0) {
            selectedHour += 12;
            timeSet = "AM";
//            timeSet1="a.m.";
        } else if (hour == 12) {
            timeSet = "PM";
//            timeSet1="p.m.";
        } else {
            timeSet = "AM";
//            timeSet1="a.m.";
        }

        String min = "";
        if (selectedMinute < 10)
            min = "0" + selectedMinute;
        else
            min = String.valueOf(selectedMinute);
        String selectedHour1 = "";
        if (selectedHour < 10) {
            selectedHour1 = "0" + selectedHour;
        } else {
            selectedHour1 = selectedHour + "";
        }

            select_time.setText(selectedHour1 + ":" + min + " " + timeSet);

    }


}
