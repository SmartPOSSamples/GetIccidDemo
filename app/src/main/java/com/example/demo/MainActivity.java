package com.example.demo;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.demo.util.TextViewUtil;

public class MainActivity extends Activity {

    private Button getIccid;
    private Button clearLog;
    private TextView textView;

    public Button.OnClickListener btnClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Button btn = (Button) v;
            if (btn.getId() == R.id.getIccid) {
                String iccid = getIccid();
                TextViewUtil.infoBlueTextView(textView, "\t" + "Get ICCID: " + iccid + "\n");
            } else if (btn.getId() == R.id.settings) {
                textView.setText("");
            }

        }
    };

    public String getIccid() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return telephonyManager.getSimSerialNumber();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        getIccid = (Button) findViewById(R.id.getIccid);
        clearLog = (Button) findViewById(R.id.settings);
        textView = (TextView) findViewById(R.id.text);
        getIccid.setOnClickListener(btnClickListener);
        clearLog.setOnClickListener(btnClickListener);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
