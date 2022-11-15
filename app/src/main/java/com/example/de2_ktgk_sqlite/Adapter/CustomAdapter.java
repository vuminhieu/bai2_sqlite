package com.example.de2_ktgk_sqlite.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.de2_ktgk_sqlite.Activity.MainActivity;
import com.example.de2_ktgk_sqlite.Model.BaiHat;
import com.example.de2_ktgk_sqlite.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<BaiHat> {
    private MainActivity context;
    private int resource;
    private List<BaiHat> baiHatList;


    public CustomAdapter(@NonNull MainActivity context, int resource, @NonNull List<BaiHat> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.baiHatList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_bai_hat, parent,false);

            viewHolder.textViewTen = convertView.findViewById(R.id.tv_ten);
            viewHolder.textViewCasi = convertView.findViewById(R.id.tv_ca_si);
            viewHolder.textViewThoiGian = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BaiHat baiHat = baiHatList.get(position);
        viewHolder.textViewTen.setText(String.valueOf(baiHat.getmName()));
        viewHolder.textViewThoiGian.setText(String.valueOf(baiHat.getmTime()));
        viewHolder.textViewCasi.setText(String.valueOf(baiHat.getmSinger()));

        viewHolder.textViewTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogUpdate(baiHat);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView textViewTen, textViewThoiGian, textViewCasi;
    }
}
