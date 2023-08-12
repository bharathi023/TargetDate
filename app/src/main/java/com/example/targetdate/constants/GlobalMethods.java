package com.example.targetdate.constants;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GlobalMethods {
    public static boolean isNull(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1") && !data.equalsIgnoreCase("NULL")) {
                isnull = true;
            }
        } else {
            isnull = false;
        }

        return isnull;
    }
    public static CharSequence setEditextError(Context context, String str, EditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }
    public static boolean isValidEmail(String target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void showNormalToast(Activity activity, String message, int lengthLong) {

            try {
                if (lengthLong == 0) {
                    lengthLong = Toast.LENGTH_LONG;
                }
                Toast.makeText(activity, message, lengthLong).show();
            } catch (Exception e) {
                throw e;
            }

    }

    public static String capitalizeString(String str) {
        String retStr = str;
        try { // We can face index out of bound exception if the string is null
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        }catch (Exception e){}
        return retStr;
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
