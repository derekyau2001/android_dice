package com.example.imageswitcher;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private ImageSwitcher imgSwi;
		private Button previous, next;
		private int curIndex;
		/*
		 * private int imgRes[] = new int[] { R.drawable.img01,
		 * R.drawable.img02, R.drawable.img03, R.drawable.img04,
		 * R.drawable.img05, R.drawable.img06, R.drawable.img08 };
		 */
		private int imgRes[] = new int[] { R.drawable.img01th, R.drawable.img02th,
				R.drawable.img03th, R.drawable.img04th, R.drawable.img05th};

		Animation slide_in_left, slide_out_right;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			imgSwi = (ImageSwitcher) rootView.findViewById(R.id.imageSwitcher1);
			previous = (Button) rootView.findViewById(R.id.previous);
			next = (Button) rootView.findViewById(R.id.next);

			slide_in_left = AnimationUtils.loadAnimation(getActivity(),
					android.R.anim.fade_in);
			slide_out_right = AnimationUtils.loadAnimation(getActivity(),
					android.R.anim.fade_in);

			imgSwi.setInAnimation(slide_in_left);
			imgSwi.setOutAnimation(slide_out_right);

			imgSwi.setFactory(new ViewFactory() {
				@Override
				public View makeView() {
					// TODO Auto-generated method stub

					ImageView imageView = new ImageView(getActivity());

					imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

					LayoutParams params = new ImageSwitcher.LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT);

					imageView.setLayoutParams(params);
					return imageView;
				}
			});

			curIndex = 0;
			imgSwi.setImageResource(imgRes[curIndex]);

			next.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if (curIndex == imgRes.length - 1) {
						curIndex = 0;
						imgSwi.setImageResource(imgRes[curIndex]);
					} else {
						imgSwi.setImageResource(imgRes[++curIndex]);
					}
				}
			});

			previous.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (curIndex == 0) {
						curIndex = imgRes.length - 1;
						imgSwi.setImageResource(imgRes[curIndex]);
					} else {
						imgSwi.setImageResource(imgRes[--curIndex]);

					}

				}
			});

			return rootView;
		}

	}

}
