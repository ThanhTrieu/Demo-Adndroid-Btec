package com.example.androidserminar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListViewAdapter extends BaseAdapter {
    List<Product> products;

    public ProductListViewAdapter(List<Product> items){
        super();
        this.products = items;
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).idProduct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.product_view, null);
        } else {
            viewProduct = convertView;
        }

        //Bind sữ liệu phần tử vào View
        Product product = (Product) getItem(position);
        ImageView img = viewProduct.findViewById(R.id.imgProduct);
        ((TextView) viewProduct.findViewById(R.id.idProduct)).setText(String.format("ID = %d", product.idProduct));
        ((TextView) viewProduct.findViewById(R.id.nameProduct)).setText(String.format("Tên SP : %s", product.name));
        Picasso.get().load(product.image).into(img);
        ((TextView) viewProduct.findViewById(R.id.priceProduct)).setText(String.format("Giá %d", product.price));
        return viewProduct;
    }
}
