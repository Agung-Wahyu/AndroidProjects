package com.example.kamus;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TambahActivity extends Activity implements  OnClickListener{
    EditText inIndo, inJawa;
    Button bSimpan;
    ListView lv;
    DatabaseManager dm;
    EntitasKamus komponenkamus;
    ArrayList<EntitasKamus>isikamus = new ArrayList<EntitasKamus>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_tambah);
        dm=new DatabaseManager(this);
        inIndo=findViewById(R.id.addIndo);
        inJawa=findViewById(R.id.addJawa);
        bSimpan=findViewById(R.id.bSimpan);
        lv=findViewById(R.id.listView1);
        bSimpan.setOnClickListener(this);
        tampilKamus();
    }
    @Override
    public void onClick(View v){
        String indo=inIndo.getText().toString();
        String jawa=inJawa.getText().toString();
        dm.addKamus(indo,jawa);
        tampilKamus();
        inIndo.setText("");
        inJawa.setText("");
    }
    private void tampilKamus(){
        isikamus.clear();
        ArrayList<ArrayList<Object>> data=dm.ambilSemuaBaris();
        for (int p=0;p<data.size();p++){
            komponenkamus=new EntitasKamus();
            ArrayList<Object>baris= data.get(p);
            Log.e("baris",baris.get(0).toString());
            Log.e("baris",baris.get(1).toString());
            komponenkamus.setIndo(baris.get(0).toString());
            komponenkamus.setJawa(baris.get(1).toString());
        }
        KamusBaseAdapter datakamus = new KamusBaseAdapter(TambahActivity.this,isikamus);
        lv.setAdapter(datakamus);
    }
}

