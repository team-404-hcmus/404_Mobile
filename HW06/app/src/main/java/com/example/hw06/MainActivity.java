package com.example.hw06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ProgressBar myBarHorizontal, myBarCircular;
    TextView lblTopCaption;
    EditText txtDataBox;
    Button  btnDoItAgain;
    int globalVar = 0; // progressStep = 1
    long startingMills = System.currentTimeMillis(); 
    boolean isRunning = false; 
    int MAX_PROGRESS = 100; // int nWork = 1000;
    Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblTopCaption = (TextView) findViewById(R.id.lblTopCaption);
        myBarHorizontal = (ProgressBar) findViewById(R.id.myBarHor);
        txtDataBox = (EditText) findViewById(R.id.txtBox1);
        txtDataBox.setHint("Enter number");

        btnDoItAgain = (Button) findViewById(R.id.btnDoItAgain);
        btnDoItAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThread();
                btnDoItAgain.setText("Do it again!");
            }
        });

    }
    private void startThread(){
        globalVar = 0;
        btnDoItAgain.setEnabled(false);
        // reset and show progress bars
        try {
            int value = Integer.parseInt(txtDataBox.getText().toString());
            MAX_PROGRESS=value;
            Log.d("Debug",String.valueOf(MAX_PROGRESS));
            myBarHorizontal.setMax(value);
        } catch (Exception e) {
            myBarHorizontal.setMax(MAX_PROGRESS);
        }

        myBarHorizontal.setProgress(0);
        myBarHorizontal.setVisibility(View.VISIBLE);
        // create-start background thread were the busy work will be done
        Thread myBackgroundThread = new Thread( backgroundTask, "backAlias1");
        myBackgroundThread.start();
    }
    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (int n = 0; n < MAX_PROGRESS; n++) {

                    Thread.sleep(1);
                    globalVar++;
                    myHandler.post(foregroundRunnable);
                }
            }catch (InterruptedException e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };
    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                // update UI, observe globalVar is changed in back thread
                int globalVarTemp = globalVar*100/MAX_PROGRESS ;
                lblTopCaption.setText(globalVarTemp + "%" );
                // advance ProgressBar
                myBarHorizontal.setProgress(globalVar);
                if (globalVar >= myBarHorizontal.getMax()) {

                    lblTopCaption.setText("Done!");
                    myBarHorizontal.setVisibility(View.INVISIBLE);
                    btnDoItAgain.setEnabled(true);
                }

            }catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };
}