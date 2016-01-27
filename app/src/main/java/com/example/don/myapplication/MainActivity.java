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

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener {
    EditText edit;
    EditText edit2;
    TextView text;
    EditText editText;
    SeekBar seekb;

    public void Verzend(View view) {
        String phoneNo = edit2.getText().toString();
        String encrypt = encryptString(edit.getText().toString(), seekb.getProgress());
        text.setText(encrypt);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, encrypt, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
    }

    private String encryptString(String string, int key) {
        StringBuilder result = new StringBuilder("");
        char c;
        for (int i = 0; i < string.length(); i++) {
            result.append(encryptChar(string.charAt(i), key));
        }
        return result.toString();
    }

    private char encryptChar(char c, int key) {
        char encryptedChar = (char) (c + key);
        return encryptedChar;
    }

    public void Reset(View view1) {
        edit.setText("");
        text.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.etbericht);
        text = (TextView) findViewById(R.id.tvEncrypt);
        edit2 = (EditText) findViewById(R.id.telnummer);
        editText = (EditText) findViewById(R.id.etbericht);
        editText.addTextChangedListener(this);
       seekb = (SeekBar) findViewById(R.id.seek);
       seekb.setOnSeekBarChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.about) {
            Intent naam = new Intent(getBaseContext(), AboutActivity.class);
            naam.putExtra("data", seekb.getProgress());

           // startActivity(naam);
           // startActivityForResult(naam);
            startActivityForResult(naam, 1234);

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
        if(edit.getText().toString()== "") return;
        text.setText(encryptString(edit.getText().toString(), seekb.getProgress()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        text.setText(encryptString(edit.getText().toString(),progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {


    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("dikke BMW");
        if (resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            TextView iv = (TextView) findViewById(R.id.textview);
            iv.setText(extras.getString("data"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}