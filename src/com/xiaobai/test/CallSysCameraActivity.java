package com.xiaobai.test;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CallSysCameraActivity extends Activity {
	public static final String DIR_ROOT_SD = android.os.Environment
			.getExternalStorageDirectory().getAbsolutePath();

	Button mEmotionButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
	}

	private void initViews() {
		mEmotionButton = (Button) findViewById(R.id.button);

		mEmotionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String fileName = System.currentTimeMillis() + ".jpg";
				if (sdCardExist()) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(DIR_ROOT_SD + "/", fileName)));
					startActivityForResult(intent, 1);
				}
			}
		});

	}

	
	private boolean sdCardExist() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return true;
		} else {
			return false;
		}
	}
	
	
}