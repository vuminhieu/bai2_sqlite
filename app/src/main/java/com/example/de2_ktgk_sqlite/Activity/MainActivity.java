package com.example.de2_ktgk_sqlite.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.de2_ktgk_sqlite.Adapter.CustomAdapter;
import com.example.de2_ktgk_sqlite.Database.DBManager;
import com.example.de2_ktgk_sqlite.Model.BaiHat;
import com.example.de2_ktgk_sqlite.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonThem;
    ListView listViewDS;
    CustomAdapter customAdapter;
    List<BaiHat> baiHats;
    private static final int My_Request_Code = 99;
    private int vitri;
    DBManager dbManager = new DBManager(this);


    private  void  mapping() {
        listViewDS = (ListView) findViewById(R.id.lv_danh_sach);
        buttonThem = (Button) findViewById(R.id.btn_them);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.dong_bai_hat, baiHats);
            listViewDS.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            listViewDS.setSelection(customAdapter.getCount() - 1);
        }
    }

    public void updateListSong() {
        baiHats.clear();
        baiHats.addAll(dbManager.getAllSong());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        baiHats = dbManager.getAllSong();
        setAdapter();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, adding.class);
                startActivityForResult(intent,My_Request_Code);

            }
        });

        listViewDS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
            }
        });

        //xoa
        listViewDS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                BaiHat baiHat = baiHats.get(position);
                int result = dbManager.DeleteSong(baiHat.getMid());
                if (result > 0) {
                    updateListSong();
                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Delete false", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (My_Request_Code == requestCode && resultCode == adding.My_Result_Code) {
            String ten = data.getStringExtra("key_ten");
            String casi = data.getStringExtra("key_casi");
            String thoigian = data.getStringExtra("key_thoigian");
//            if (getIntent().getExtras() != null) {
//                BaiHat baiHat = (BaiHat) getIntent().getExtras().get("object_songs");
//            }
            baiHats.add(new BaiHat(ten,casi,thoigian));
            setAdapter();
        }
    }

    public void DialogUpdate(BaiHat baiHat) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_repair);

        EditText editTextIDUpdate = (EditText) dialog.findViewById(R.id.edt_sua_id);
        EditText editTextTenUpdate = (EditText) dialog.findViewById(R.id.edt_sua_ten);
        EditText editTextCaSiUpdate = (EditText) dialog.findViewById(R.id.edt_sua_ca_si);
        EditText editTextThoiGianUpdate = (EditText) dialog.findViewById(R.id.edt_sua_time);
        Button buttonSua = (Button) dialog.findViewById(R.id.btn_update);
        Button buttonHuy = (Button) dialog.findViewById(R.id.btn_huy);

        editTextIDUpdate.setText(baiHat.getMid()+ "");
        editTextTenUpdate.setText(baiHat.getmName() + "");
        editTextCaSiUpdate.setText(baiHat.getmSinger()+ "");
        editTextThoiGianUpdate.setText(baiHat.getmTime() + "");
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiHat baiHat1 = new BaiHat();
                baiHat1.setMid(Integer.parseInt(String.valueOf(editTextIDUpdate.getText())));
                baiHat1.setmName(editTextTenUpdate.getText() + "");
                baiHat1.setmSinger(editTextCaSiUpdate.getText() + "");
                baiHat1.setmTime(editTextThoiGianUpdate.getText() + "");
                int result = dbManager.updateSong(baiHat1);
                if (result > 0) {
                    dbManager.updateSong(baiHat1);
                    updateListSong();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}