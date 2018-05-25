package surya.com.nu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private Boolean isFabOpen = false;
        private FloatingActionButton fab4,fab1,fab2,fab3,fab5;
        private Animation fab_open,fab_close,rotate_forward,rotate_backward;
        private ImageButton img;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            fab1 = (FloatingActionButton)findViewById(R.id.fab1);
            fab2 = (FloatingActionButton)findViewById(R.id.fab2);
            fab3 = (FloatingActionButton)findViewById(R.id.fab3);
            fab4 = (FloatingActionButton)findViewById(R.id.fab4);
            fab5 = (FloatingActionButton)findViewById(R.id.fab5);
            img=(ImageButton)findViewById(R.id.imageButton2);

            fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
            fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
            rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
            rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Ma.class);
                    startActivity(intent);
                }
            });

            fab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     boolean installed = appInstalledOrNot("Ola cabs-Book taxi in india");
                if(installed) {
                    //This intent will help you to launch if the package is already installed
                    Intent LaunchIntent = getPackageManager()
                            .getLaunchIntentForPackage("Ola cabs-Book taxi in india");
                    startActivity(LaunchIntent);

                    //Toast.makeText(getApplicationContext(), "installed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "OLA app not installed", Toast.LENGTH_SHORT).show();
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW)
                            .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.olacabs.customer&hl=en"));
                    startActivity(goToMarket);
                }
            }
        });

            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //mindfire
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mindfiresolutions.com"));
                    startActivity(i);

                }
            });
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10000startups.com"));
                    startActivity(i);

                }
            });
            fab4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //teqip
                }
            });
            fab5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //alumni
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nitraa.org"));
                    startActivity(i);
                }
            });

        }

    private boolean appInstalledOrNot(String uri) {

        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

       /* public void animateFAB(){

            if(isFabOpen){

                fab.startAnimation(rotate_backward);
                fab1.startAnimation(fab_close);
                fab2.startAnimation(fab_close);
                fab3.startAnimation(fab_close);
                fab1.setClickable(false);
                fab2.setClickable(false);
                fab3.setClickable(false);
                isFabOpen = false;
                Log.d("Raj", "close");

            } else {

                fab.startAnimation(rotate_forward);
                fab1.startAnimation(fab_open);
                fab2.startAnimation(fab_open);
                fab3.startAnimation(fab_open);
                fab1.setClickable(true);
                fab2.setClickable(true);
                fab3.setClickable(true);
                isFabOpen = true;
                Log.d("Raj","open");

            }
        }*/

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}