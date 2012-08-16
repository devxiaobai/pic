package com.xiaobai.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class EmotionActivity extends Activity {
	private static final int[] mIconIds = new int[] { R.drawable.face1,
			R.drawable.face2 };
	Button mEmotionButton;
	ViewGroup rootView;
	EditText mEditMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mEmotionButton = (Button) findViewById(R.id.button);
		mEditMessage = (EditText) findViewById(R.id.message_edit);
		mEmotionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PopupWindow popWin = new PopupWindow(createEmotionView(),
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						true);
				popWin.setBackgroundDrawable(new BitmapDrawable());
				// popWin.showAsDropDown(findViewById(R.id.button));
				popWin.showAtLocation(findViewById(R.id.button), Gravity.LEFT
						| Gravity.BOTTOM, 0, 0);
			}
		});
	}

	View createEmotionView() {
		rootView = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		rootView.setLayoutParams(params);
		rootView.setBackgroundResource(R.drawable.pop);

		LayoutParams emotionParas = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < mIconIds.length; i++) {
			EmotionButton emotion = new EmotionButton(this);
			emotion.setBackgroundResource(R.drawable.face_bg);
			emotion.setImageResource(mIconIds[i]);
			emotion.setIndex(i);
			emotion.setOnClickListener(clickListener);
			rootView.addView(emotion, i, emotionParas);
		}
		return rootView;
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int index = ((EmotionButton) v).getIndex();
//			ImageGetter imageGetter = new ImageGetter() {
//				@Override
//				public Drawable getDrawable(String source) {
//					int id = Integer.parseInt(source);
//
//					Drawable d = getResources().getDrawable(id);
//
//					d.setBounds(0, 0, d.getIntrinsicWidth(),
//							d.getIntrinsicHeight());
//
//					return d;
//
//				}
//			};
//			CharSequence cs = Html.fromHtml("<img src='" + mIconIds[index]
//					+ "'/>", imageGetter, null);
			ImageSpan span = new ImageSpan(getResources().getDrawable(mIconIds[index]));
			SpannableString spanString = new SpannableString("face");
			spanString.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			mEditMessage.getText().append(spanString);

		}
	};

	class EmotionButton extends ImageButton {
		private int mIndex;

		public EmotionButton(Context context) {
			super(context);
		}

		public EmotionButton(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public EmotionButton(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}

		public void setIndex(int index) {
			mIndex = index;
		}

		public int getIndex() {
			return mIndex;
		}
	}
}
