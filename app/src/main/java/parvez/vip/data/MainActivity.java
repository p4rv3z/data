package parvez.vip.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import parvez.vip.data.helper._Crypto;

public class MainActivity extends AppCompatActivity {
    AppCompatTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text);
        String data = getResources().getString(R.string.data);
//        String password = "Parvez";
//        _Preferences.saveBoolean(this,true,"par",password);
//        String deData = ""+_Preferences.loadBoolean(this,"par",password);
//        _File.saveFile(this,"par.db",data,"par");
//        String deData = _File.loadFile(this,"par.db","par");

        _DB.save(this, data,"par2", "par");
        String deData = _DB.load(this,"par2","par");

        tv.setText(deData);
    }
}
