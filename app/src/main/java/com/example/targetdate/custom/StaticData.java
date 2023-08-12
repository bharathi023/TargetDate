package com.example.targetdate.custom;

import android.content.Context;
import android.net.Uri;

import com.example.targetdate.R;
import com.example.targetdate.dbutils.DBAction;
import com.example.targetdate.models.Categories;
import com.example.targetdate.models.UserLoginDetails;
import com.example.targetdate.util.DBUtil;

import java.util.ArrayList;

public class StaticData {

    public static void saveAllCategories(Context context){
        UserLoginDetails userLoginDetails = DBUtil.getInstance(context).getValuesFromTable(null,UserLoginDetails.class);

        if(userLoginDetails!=null) {
            ArrayList<Categories> categoriesData = DBUtil.getInstance(context).getAllValuesFromTable("userId ='" +userLoginDetails.getUserId()+ "'", Categories.class, null);


            if(categoriesData!=null&&categoriesData.size()<=0) {

                Categories categories = new Categories();
                categories.setCategory("Food");
                categories.setTitle("Food");
                categories.setIcon(getURLForResource(R.drawable.food));

                categories.setUserId(userLoginDetails.getUserId());

                Categories categories1 = new Categories();
                categories1.setCategory("Subscriptions");
                categories1.setTitle("Subscriptions");
                categories1.setUserId(userLoginDetails.getUserId());
                categories1.setIcon(getURLForResource(R.drawable.subscription));

                Categories categories2 = new Categories();
                categories2.setCategory("Credit bills");
                categories2.setTitle("Credit bills");
                categories2.setUserId(userLoginDetails.getUserId());
                categories2.setIcon(getURLForResource(R.drawable.cards));

                Categories categories3 = new Categories();
                categories3.setCategory("Medicines");
                categories3.setTitle("Medicines");
                categories3.setUserId(userLoginDetails.getUserId());
                categories3.setIcon(getURLForResource(R.drawable.medicine));

                Categories categories4 = new Categories();
                categories4.setCategory("Loan/Insurance");
                categories4.setTitle("Loan/Insurance");
                categories4.setUserId(userLoginDetails.getUserId());
                categories4.setIcon(getURLForResource(R.drawable.loan));

                Categories categories5 = new Categories();
                categories5.setCategory("Assignments");
                categories5.setTitle("Assignments");
                categories5.setUserId(userLoginDetails.getUserId());
                categories5.setIcon(getURLForResource(R.drawable.assignments));


                Categories categories6 = new Categories();
                categories6.setCategory("Wifi/electricity");
                categories6.setTitle("Wifi/Electricity");
                categories6.setUserId(userLoginDetails.getUserId());
                categories6.setIcon(getURLForResource(R.drawable.electri));


                Categories categories7 = new Categories();
                categories7.setCategory("Recharge");
                categories7.setTitle("Recharge");
                categories7.setUserId(userLoginDetails.getUserId());
                categories7.setIcon(getURLForResource(R.drawable.recharge));

                DBUtil.getInstance(context).insertOrUpdateTable(categories, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories1, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories2, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories3, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories4, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories5, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories6, DBAction.INSERT,null);
                DBUtil.getInstance(context).insertOrUpdateTable(categories7, DBAction.INSERT,null);







            }


        }


    }
    public static String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
