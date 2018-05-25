package surya.com.nu;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Basic extends ActionBarActivity {
    ImageButton imb;
    ImageView iv1;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic);
        Typeface customfont=Typeface.createFromAsset(this.getAssets(),"fonts/Brandon_med.otf");

        iv1 = (TouchImageView) findViewById(R.id.imageView);

        tv1 = (TextView) findViewById(R.id.tV1);
        tv2 = (TextView) findViewById(R.id.tV2);

        tv1.setTypeface(customfont);
        tv2.setTypeface(customfont);

        tv1.setText("HOW TO REACH\n" +
                "\n"  +
                "NIT Rourkela is situated in sector 2 of Rourkela, Odisha.\n" +
                "Being situated at the centre of east zone of India, Rourkela is accessible both by rail and road. By rail, it is around 14 hrs journeys from Patna, 4 hours distant from Ranchi, nearly 6-7 hrs from Calcutta and 9 hrs from Cuttack and  Bhubaneswar, the capital of Odisha.\n"
                +
                "If you are arriving by train at the Rourkela station, then you can arrive at NIT boarding an auto rickshaw of cost around 120 INR. Also you can avail the OLA facility using code 'nes100'.\n" +
                "For those who will be turning up by bus, get off at sector-2 bus stop and hire auto-rickshaw  with a payment of around 80 INR or book OLA cab to reach Nit.");

        imb=(ImageButton)findViewById(R.id.imageButton);
        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.co.in/maps/@22.251329,84.9049419,16.75z");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.event) {
            Intent intent = new Intent(Basic.this,Abt.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.team) {
            Intent intent = new Intent(Basic.this, Abt.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
