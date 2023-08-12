package com.example.targetdate.util;

import android.content.Context;


import com.example.targetdate.dbutils.DBSupportUtil;
import com.example.targetdate.dbutils.core.TableDetails;
import com.example.targetdate.models.Categories;
import com.example.targetdate.models.RegistrationCore;
import com.example.targetdate.models.UserLoginDetails;

import java.util.ArrayList;



public class DBUtil extends DBSupportUtil {

    private static final int DB_VERSION_NUMBER = 1;
    public static final String DB_NAME = "ChharoCustomerDb";
    private static DBUtil instance = null;
    private static boolean nonSingleInstance = false;


    private DBUtil(Context context) {
        super( context );
    }

    @Override
    protected ArrayList<TableDetails> getAllTableDetails(ArrayList<TableDetails> allTableDefinitions) {

        allTableDefinitions.add( TableDetails.getTableDetails( RegistrationCore.class ) );
        allTableDefinitions.add( TableDetails.getTableDetails( UserLoginDetails.class ) );
        allTableDefinitions.add(TableDetails.getTableDetails(Categories.class));

        return allTableDefinitions;
    }

    public static DBUtil getWDInstance(Context context) {
        return getInstance( context );
    }

    public static DBUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtil( context );
        }
        return instance;
    }

    @Override
    protected String getDatabaseFileName() {
        return DB_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return DB_VERSION_NUMBER;
    }


}
