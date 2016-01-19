package com.example.don.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener {/**/
    EditText et;
    EditText tel;
    TextView tv;
    EditText editText;
    SeekBar sb;

    public void Verzend(View view) {
        String phoneNo = tel.getText().toString();
        String encrypt = encryptString(et.getText().toString(), 4);
        tv.setText(encrypt);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, encrypt, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
    }

    private String encryptString(String string, int key) {
        StringBuilder result = new StringBuilder("");
        char c;
        for (int i = 0; i < string.length(); i++) {
//            c = string.charAt(i);
//            c = (char) (c + key);
            result.append(encryptChar(string.charAt(i), key));
        }
        return result.toString();
    }

    private char encryptChar(char c, int key) {
        char encryptedChar = (char) (c + key);
        return encryptedChar;
    }

    public void Reset(View view1) {
        et.setText("");
        tv.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.etbericht);
        tv = (TextView) findViewById(R.id.tvEncrypt);
        tel = (EditText) findViewById(R.id.telnummer);
        editText = (EditText) findViewById(R.id.etbericht);
        editText.addTextChangedListener(this);
//        sb = (SeekBar) findViewById(R.id.seek);
//        sb.setOnSeekBarChangeListener(this);
//        sb.setProgress(0);
//        sb.setMax(11);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable str) {
        char chr = str.charAt(str.length() - 1);
       // tv.setText(encryptChar(chr, 4));
        tv.setText(tv.getText().toString()+encryptChar(chr,4));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}