package com.xiaobai.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class EmotionKeyboardActivity extends Activity {
	Button mEmotionButton;
	GridView emotionView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emotion_keyboard);
		mEmotionButton = (Button) findViewById(R.id.button);
		emotionView =(GridView)findViewById(R.id.emotion_view);
		mEmotionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				emotionView.setVisibility(View.VISIBLE);
			}
		
		});
	}
}
