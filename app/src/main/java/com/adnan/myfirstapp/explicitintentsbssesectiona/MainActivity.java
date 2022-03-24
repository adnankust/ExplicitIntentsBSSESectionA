package com.adnan.myfirstapp.explicitintentsbssesectiona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Class name for log data
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE = "com.adnan.myfirstapp.explcitintentsbsseectiona";
    //Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;

    //EditText view for the message
    private EditText mMessageEditText;
    //TextView for the reply header
    private TextView mReplyHeadTextView;
    //TextView for the body
    private TextView mReplayTextView;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRetart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplayTextView.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplayTextView = findViewById(R.id.text_message_reply);

        //Restore the state.
        if(savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if(isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplayTextView.setText(savedInstanceState.getString("reply_text"));
                mReplayTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchSecondActivity(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);

    }



      @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //test for the right intent reply
        if(requestCode == TEXT_REQUEST) {
            if(resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplayTextView.setText(reply);
                mReplayTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}