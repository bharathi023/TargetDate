package com.example.targetdate.fragements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.targetdate.R;
import com.example.targetdate.activity.CreateCategoryAct;
import com.example.targetdate.adapters.CategoriesAdapter;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.models.Categories;
import com.example.targetdate.models.UserLoginDetails;
import com.example.targetdate.util.DBUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends HomeBaseFragment {


    private TextView welcomenote;
    private Button customBtn;
    private RecyclerView categories_list;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initCategoryList();
        actionEvents();

        return view;

    }

    private void actionEvents() {
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreateCategoryFragment();
            }
        });
    }

    private void callCreateCategoryFragment() {
        Intent intent= new Intent(getActivity(), CreateCategoryAct.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",getString(R.string.custom));
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();


    }

    private void initCategoryList() {
        UserLoginDetails userLoginDetails = DBUtil.getInstance(getActivity()).getValuesFromTable(null,UserLoginDetails.class);

        if(userLoginDetails!=null){
            welcomenote.setText("Welcome "+ GlobalMethods.capitalizeString(userLoginDetails.getUserName()));
        }


        ArrayList<Categories> categoriesData = DBUtil.getInstance(getActivity()).getAllValuesFromTable("userId ='" +userLoginDetails.getUserId()+ "'", Categories.class, "_id asc");

        if(categoriesData!=null&&categoriesData.size()>0) {
            categories_list.setHasFixedSize(true);
            categories_list.setLayoutManager(new GridLayoutManager(getActivity(), calculateNoOfColumns(getActivity())));
            CategoriesAdapter categoriesAdatpter= new CategoriesAdapter(getActivity(),categoriesData);

            categories_list.setAdapter(categoriesAdatpter);
        }
    }
    public int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    private void initView(View view) {
        welcomenote=  (TextView)view.findViewById(R.id.welcomenote);
        customBtn = (Button)view.findViewById(R.id.customBtn);
        categories_list = (RecyclerView)view.findViewById(R.id.categories_list);




    }

    @Override
    public void onTabSelected() {

    }
}