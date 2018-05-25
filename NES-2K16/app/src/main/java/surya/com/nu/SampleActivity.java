package surya.com.nu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import su.levenetc.android.textsurface.TextSurface;
import surya.com.nu.checks.CookieThumperSample;

/**
 * Created by Eugene Levenetc.
 */
public class SampleActivity extends Activity {

	private TextSurface textSurface;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activity);


		textSurface = (TextSurface) findViewById(R.id.text_surface);

		textSurface.postDelayed(new Runnable() {
			@Override public void run() {
				show();
			}
		}, 1000);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SampleActivity.this, MainActivity.class);
				startActivity(intent);
			}
		},12000);
	}

	private void show() {
		textSurface.reset();
		CookieThumperSample.play(textSurface, getAssets());
	}

	/*@Override
	protected void onPause() {
		super.onPause();
		finish();
	}*/
}