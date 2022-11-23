package com.example.de2_ktgk_sqlite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.de2_ktgk_sqlite.Adapter.CustomAdapter;
import com.example.de2_ktgk_sqlite.Database.DBManager;
import com.example.de2_ktgk_sqlite.Model.BaiHat;
import com.example.de2_ktgk_sqlite.R;

import java.util.List;

public class adding extends AppCompatActivity {
    Button buttonSave;
    EditText editTextTen, editTextCasi, editTextId, editTextThoiGian;
    public static final int My_Result_Code = 33;
    private CustomAdapter customAdapter;
    private List<BaiHat> baiHats;
    DBManager dbManager = new DBManager(this);

    private void mapping() {
        buttonSave = (Button) findViewById(R.id.btn_luu);
        editTextTen = (EditText) findViewById(R.id.edt_ten);
        editTextId = (EditText) findViewById(R.id.edt_id);
        editTextCasi = (EditText) findViewById(R.id.edt_ca_si);
        editTextThoiGian = (EditText) findViewById(R.id.edt_time);
    }
    private BaiHat createStudent() {
        String name = editTextTen.getText().toString().trim();
        String singger = editTextCasi.getText().toString().trim();
        String time = editTextThoiGian.getText().toString().trim();
        Intent intent = new Intent(adding.this, MainActivity.class);
        intent.putExtra("key_ten", name);
        intent.putExtra("key_casi", singger);
        intent.putExtra("key_thoigian", time);
        setResult(My_Result_Code, intent);
        BaiHat baiHat = new BaiHat(name, singger, time);
        return baiHat;
    }

    public void updateListStudent() {
        baiHats.clear();
        baiHats.addAll(dbManager.getAllSong());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        mapping();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = editTextId.getText().toString().trim();
//                String ten = editTextTen.getText().toString().trim();
//                String casi = editTextCasi.getText().toString().trim();
//                String thoigian = editTextThoiGian.getText().toString().trim();

                BaiHat baiHat = createStudent();
                if (baiHat != null) {
                    dbManager.addSong(baiHat);
                }
                updateListStudent();
//                su dung bundle de truyen du lieu
//                BaiHat baiHat = new BaiHat(id, ten, casi, thoigian);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("object_songs", baiHat);
//                intent.putExtras(bundle);
                finish();
            }
        });

    }





}