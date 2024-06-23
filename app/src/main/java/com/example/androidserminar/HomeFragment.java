package com.example.androidserminar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.listview);

        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("Iphone 6", 500, 1, "https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-thumbnew-600x600.jpg"));
        listProduct.add(new Product("Iphone 7", 450, 2,"https://cdn.tgdd.vn/Products/Images/42/249948/samsung-galaxy-s23-ultra-green-thumbnew-600x600.jpg"));
        listProduct.add(new Product("Sony Abc", 800, 3,"https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        listProduct.add(new Product("Samsung XYZ",900, 4,"https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        listProduct.add(new Product("SP 5", 500, 5,"https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        listProduct.add(new Product("SP 6", 700, 6, "https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        listProduct.add(new Product("SP 7", 800, 7, "https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        listProduct.add(new Product("SP 8", 900, 8, "https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-2-600x600.jpg"));
        ProductListViewAdapter listViewAdapter = new ProductListViewAdapter(listProduct);
        listView.setAdapter(listViewAdapter);

        return view;
    }
}