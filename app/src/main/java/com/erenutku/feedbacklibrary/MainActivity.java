package com.erenutku.feedbacklibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.erenutku.easyfeedback.easyfeedback.EasyFeedback;
import com.erenutku.easyfeedback.easyfeedback.OnImageSelectedListener;

public class MainActivity extends AppCompatActivity implements OnImageSelectedListener {

    private EasyFeedback mEasyFeedback;
    private Button btSend,btAddImage;
    private ImageView imageView;
    private EditText etSubject,etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEasyFeedback = EasyFeedback.getInstance(MainActivity.this);
        mEasyFeedback.setDeveloperEmailList(new String[]{"developer@yourdomain.com"});
        init();
    }

    private void init() {
        etSubject = (EditText) findViewById(R.id.etSubject);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);
        btSend.setOnClickListener(btnClickListener);
        btAddImage = (Button) findViewById(R.id.btAddImage);
        btAddImage.setOnClickListener(btnClickListener);
        imageView = (ImageView) findViewById(R.id.ivImage);

    }
    private View.OnClickListener btnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btSend:
                    mEasyFeedback.setSubject(etSubject.getText().toString());
                    mEasyFeedback.setMessage(etMessage.getText().toString());
                    mEasyFeedback.openFilledEmailIntent();
                    break;
                case R.id.btAddImage:
                    mEasyFeedback.pickImageFromGallery();
                    break;
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mEasyFeedback.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageSelected(Bitmap bm) {

        imageView.setImageBitmap(bm);
    }
}
