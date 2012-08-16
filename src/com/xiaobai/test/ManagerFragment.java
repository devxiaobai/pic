package com.xiaobai.test;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ManagerFragment extends Fragment {

	GridView grid;

	PopupWindow deletePop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.management_fra, null);
		grid = (GridView) view.findViewById(R.id.myGrid);
		grid.setAdapter(new ImageAdapter(getActivity()));
		initDeletePop();
		grid.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				deletePop.showAsDropDown(arg1);
				//deletePop.showAtLocation(arg1, Gravity.LEFT | Gravity.TOP, 0, 0);
				return false;
			}
		});
		return view;
	}

	private void initDeletePop() {
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(R.drawable.delete);
		deletePop = new PopupWindow(imageView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		deletePop.setFocusable(true);
		// 设置允许在外点击消失
		deletePop.setOutsideTouchable(true);
		//刷新状态（必须刷新否则无效）
		deletePop.update();
		deletePop.setBackgroundDrawable(new BitmapDrawable());
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public class ImageAdapter extends BaseAdapter {
		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.manage_grid_item, null);
			}
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.grid_item_image);
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setImageResource(mThumbIds[position]);

			TextView textView = (TextView) convertView
					.findViewById(R.id.grid_item_label);
			textView.setText("姓名");

			return convertView;
		}

		private Context mContext;

		private Integer[] mThumbIds = { R.drawable.head, R.drawable.head,
				R.drawable.head, R.drawable.head, R.drawable.head,
				R.drawable.add_friend };
	}

}
