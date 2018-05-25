package surya.com.nu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Abt extends ActionBarActivity {
    ImageButton imb;

    ImageView  iv1,iv2,iv3,iv4,iv5,iv6;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abt);

        Typeface customfont=Typeface.createFromAsset(this.getAssets(),"fonts/Brandon_med.otf");

        tv1=(TextView)findViewById(R.id.tV1);
        tv2=(TextView)findViewById(R.id.tV2);
        tv3=(TextView)findViewById(R.id.tV3);
        tv4=(TextView)findViewById(R.id.tV4);
        tv5=(TextView)findViewById(R.id.tV5);
        tv6=(TextView)findViewById(R.id.tV6);

        tv1.setTypeface(customfont);
        tv2.setTypeface(customfont);
        tv3.setTypeface(customfont);
        tv4.setTypeface(customfont);
        tv5.setTypeface(customfont);
        tv6.setTypeface(customfont);


        iv1=(ImageView)findViewById(R.id.iv1);
        iv2=(ImageView)findViewById(R.id.iv2);
        iv3=(ImageView)findViewById(R.id.iv3);
        iv4=(ImageView)findViewById(R.id.iv4);
        iv5=(ImageView)findViewById(R.id.iv5);
        iv6=(ImageView)findViewById(R.id.iv6);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.a);
        Bitmap icon2 = BitmapFactory.decodeResource(getResources(),R.drawable.b);
        Bitmap icon3 = BitmapFactory.decodeResource(getResources(),R.drawable.c);
        Bitmap icon4 = BitmapFactory.decodeResource(getResources(),R.drawable.d);
        Bitmap icon5 = BitmapFactory.decodeResource(getResources(),R.drawable.surya);
        Bitmap icon6 = BitmapFactory.decodeResource(getResources(),R.drawable.g);;

        iv1.setImageBitmap(icon);
        iv2.setImageBitmap(icon2);
        iv3.setImageBitmap(icon3);
        iv4.setImageBitmap(icon4);
        iv5.setImageBitmap(icon5);
        iv6.setImageBitmap(icon6);
    }


}

/*    @Override
    public void onDestroy() {

        mPlayer.stop();
        super.onDestroy();
*/
