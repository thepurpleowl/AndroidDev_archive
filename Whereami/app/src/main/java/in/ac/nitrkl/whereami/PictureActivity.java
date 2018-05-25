package in.ac.nitrkl.whereami;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by surya on 18/1/17.
 */

public class PictureActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity);
        ZoomableImageView view = new ZoomableImageView(this);
        view = (ZoomableImageView) findViewById(R.id.pic_act);
        view.setImageResource(R.drawable.wai_default);
//
//        String image_path = getIntent().getStringExtra("byteArray"); //getIntent().getExtras().getString() not working
//        image_path = image_path.replace(" ", "");
//        Log.d("path", image_path + "");
//        Glide.with(PictureActivity.this)
//                .load(image_path)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.dummy_image)
//                .into(view);
    }
}