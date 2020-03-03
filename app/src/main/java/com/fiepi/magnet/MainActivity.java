package com.fiepi.magnet;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String string = getIntent().getStringExtra(Intent.EXTRA_PROCESS_TEXT);

        if (string != null && !string.isEmpty()) {
            string = string.trim();
            if(isLetterOrDigit(string)){
                string = "magnet:?xt=urn:btih:" + string;
                copy(string);
                Toast.makeText(this,string, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, R.string.error_string, Toast.LENGTH_LONG).show();
            }
        }
        finish();
    }

    private static boolean isLetterOrDigit(String str){
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }
    private void copy(final String string) {
        runOnUiThread(() -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData cd = ClipData.newPlainText(getString(R.string.app_name), string);
            cm.setPrimaryClip(cd);
        });
    }
}
