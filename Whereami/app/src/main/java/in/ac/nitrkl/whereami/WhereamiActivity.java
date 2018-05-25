package in.ac.nitrkl.whereami;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import static in.ac.nitrkl.whereami.MainActivity.FILE_NAME;

public class WhereamiActivity extends AppCompatActivity implements View.OnTouchListener {

    double dX[]= new double[22];
    double dY[]= new double[22];

    boolean touched[]=new boolean[22];
    boolean touches[]=new boolean[12];

    final String label[]={"Sun","Earth","Owl","Monkey","Ant","Caterpillar","Butterfly wing",
            "Cat","Slipper", "Flower", "Book" ,"Kite"};

    MediaPlayer player;
    private PopupWindow pwindo;
    Button btnClosePopup;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    int i=0;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.whereami);

        settings = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();

        player = MediaPlayer.create(this, R.raw.loventime);
        player.setLooping(true); // Set looping
        player.setVolume(1.0f,1.0f);

        for(int j=0;j<22;j++){
            dX[j]=-1;
            dY[j]=-1;
        }

        touches[0]=settings.getBoolean("sunT",false);
        touches[1]=settings.getBoolean("earthT",false);
        touches[2]=settings.getBoolean("owlT",false);
        touches[3]=settings.getBoolean("monkeyT",false);
        touches[4]=settings.getBoolean("antT",false);
        touches[5]=settings.getBoolean("caterpillarT",false);
        touches[6]=settings.getBoolean("bwingT",false);
        touches[7]=settings.getBoolean("catT",false);
        touches[8]=settings.getBoolean("slipperT",false);
        touches[9]=settings.getBoolean("flowerT",false);
        touches[10]=settings.getBoolean("bookT",false);
        touches[11]=settings.getBoolean("kiteT",false);

        ZoomableImageView iv = new ZoomableImageView(this);
        iv = (ZoomableImageView) findViewById(R.id.image);
        if (iv != null) {
           iv.setOnTouchListener (this);
        }

        toast ("Touch the objects.");
    }


public boolean onTouch (View v, MotionEvent ev) 
{
    final int action = ev.getAction();

    final int evX = (int) ev.getX();
    final int evY = (int) ev.getY();
    Vibrator vibe = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

    ImageView imageView = (ImageView) v.findViewById (R.id.image);
    if (imageView == null){
        return false;
    }

    switch (action) {
    case MotionEvent.ACTION_DOWN :
        Log.d("position", ""+evX+"   "+evY);


       break;

    case MotionEvent.ACTION_UP :

       int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
        ColorCompare ct = new ColorCompare();
       int tolerance = 25;

        //sun
       if (ct.closeMatch (Color.parseColor("#fcf09c"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[0]==false){
               touched[0]=true;
               editor.putBoolean("sunT",true);
               editor.commit();
               touches[0]=settings.getBoolean("sunT",false);
               i++;
               toast("Yeah!! found SUN");

               int a= settings.getInt("sun", 0);
               a-=1;
               editor.putInt("sun",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           };
       }
       //earth
       else if (ct.closeMatch (Color.parseColor("#0000ff"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[1]==false){
               touched[1]=true;
               editor.putBoolean("earthT",true);
               editor.commit();
               touches[1]=settings.getBoolean("earthT",false);
               i++;
               toast("Yeah!! found EARTH");

               int a= settings.getInt("earth", 0);
               a-=1;
               editor.putInt("earth",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }
       //monkey
       else if (ct.closeMatch (Color.parseColor("#960000"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[4]==false){
               touched[4]=true;
               editor.putBoolean("monkeyT",true);
               editor.commit();
               touches[3]=settings.getBoolean("monkeyT",false);
               i++;
               toast("Yeah!! found MONKEY");

               int a= settings.getInt("monkey", 0);
               a-=1;
               editor.putInt("monkey",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }
       //caterpillar
       else if (ct.closeMatch (Color.parseColor("#ccffcc"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[10]==false){
               touched[10]=true;
               editor.putBoolean("caterpillarT",true);
               editor.commit();
               touches[5]=settings.getBoolean("caterpillarT",false);
               i++;
               toast("Yeah!! found CATERPILLAR");

               int a= settings.getInt("caterpillar", 0);
               a-=1;
               editor.putInt("caterpillar",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }
       //butterflywing
       else if (ct.closeMatch (Color.parseColor("#fc00ff"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[11]==false){
               touched[11]=true;
               editor.putBoolean("bwingT",true);
               editor.commit();
               touches[6]=settings.getBoolean("bwingT",false);
               i++;
               toast("Yeah!! found BUTTERFLYWING");

               int a= settings.getInt("bwing", 0);
               a-=1;
               editor.putInt("bwing",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }
       //book
       else if (ct.closeMatch (Color.parseColor("#ffff00"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[20]==false){
               touched[20]=true;
               editor.putBoolean("bookT",true);
               editor.commit();
               touches[10]=settings.getBoolean("bookT",false);
               i++;
               toast("Yeah!! found BOOK");

               int a= settings.getInt("book",0);
               a-=1;
               editor.putInt("book",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }
       //kite
       else if (ct.closeMatch (Color.parseColor("#696969"), touchColor, tolerance)){
           vibe.vibrate(200);
           if(touched[21]==false){
               touched[11]=true;
               editor.putBoolean("kiteT",true);
               editor.commit();
               touches[11]=settings.getBoolean("kiteT",false);
               i++;
               toast("Yeah!! found KITE");

               int a= settings.getInt("kite", 0);
               a-=1;
               editor.putInt("kite",a);
               editor.commit();
           }
           else {
               toast("already Clicked");
           }
       }

       //2 owls
       else if (ct.closeMatch (Color.parseColor("#000000"), touchColor, tolerance)){
           vibe.vibrate(200);
           int offset=2;
           boolean touch=true;
           for (int j=0;j<offset;j++){
               touch=touch && touched[2+j];
           }
           if(touch==false){
               if(dX[2]==-1 && dY[2]==-1){
                   touched[2]=true;
                   dX[2]=evX;
                   dY[2]=evY;
                   toast("Yeah!! found OWL");

                   int a= settings.getInt("owl", 0);
                   a-=1;
                   editor.putInt("owl",a);
                   editor.commit();
               }
               else if(dX[2]!=-1 && dY[2]!=-1 && (Math.abs(dX[2]-evX)<50) && (Math.abs(dY[2]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[3]==-1 && dY[3]==-1){
                   touched[3]=true;
                   dX[3]=evX;
                   dY[3]=evY;
                   toast("Yeah!! found second OWL");
                   editor.putBoolean("owlT",true);
                   editor.commit();
                   touches[2]=settings.getBoolean("owlT",false);

                   int a= settings.getInt("owl", 0);
                   a-=1;
                   editor.putInt("owl",a);
                   editor.commit();
               }
           }
           else {
               toast("already Clicked");
           }
       }
       //2 cats
       else if (ct.closeMatch (Color.parseColor("#999999"), touchColor, tolerance)){
           vibe.vibrate(200);
           int offset=2;
           boolean touch=true;
           for (int j=0;j<offset;j++){
               touch=touch && touched[12+j];
           }
           if(touch==false){
               if(dX[12]==-1 && dY[12]==-1){
                   touched[12]=true;
                   dX[12]=evX;
                   dY[12]=evY;
                   toast("Yeah!! found CAT");

                   int a= settings.getInt("cat", 0);
                   a-=1;
                   editor.putInt("cat",a);
                   editor.commit();
               }
               else if(dX[12]!=-1 && dY[12]!=-1 && (Math.abs(dX[12]-evX)<50) && (Math.abs(dY[12]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[13]==-1 && dY[13]==-1){
                   touched[13]=true;
                   dX[13]=evX;
                   dY[13]=evY;
                   toast("Yeah!! found second CAT");
                   editor.putBoolean("catT",true);
                   editor.commit();
                   touches[7]=settings.getBoolean("catT",false);

                   int a= settings.getInt("cat", 0);
                   a-=1;
                   editor.putInt("cat",a);
                   editor.commit();
               }
           }
           else {
               toast("already Clicked");
           }
       }
       //2 slippers
       else if (ct.closeMatch (Color.parseColor("#1cc1ff"), touchColor, tolerance)){
           vibe.vibrate(200);
           int offset=2;
           boolean touch=true;
           for (int j=0;j<offset;j++){
               touch=touch && touched[14+j];
           }
           if(touch==false){
               if(dX[14]==-1 && dY[14]==-1){
                   touched[14]=true;
                   dX[14]=evX;
                   dY[14]=evY;
                   toast("Yeah!! found first SLIPPER");

                   int a= settings.getInt("slipper", 0);
                   a-=1;
                   editor.putInt("slipper",a);
                   editor.commit();
               }
               else if(dX[14]!=-1 && dY[14]!=-1 && (Math.abs(dX[14]-evX)<50) && (Math.abs(dY[14]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[15]==-1 && dY[15]==-1){
                   touched[15]=true;
                   dX[15]=evX;
                   dY[15]=evY;
                   toast("Yeah!! found second SLIPPER");
                   editor.putBoolean("slipperT",true);
                   editor.commit();
                   touches[8]=settings.getBoolean("slipperT",false);

                   int a= settings.getInt("slipper", 0);
                   a-=1;
                   editor.putInt("slipper",a);
                   editor.commit();
               }
           }
           else {
               toast("already Clicked");
           }
       }
       //4 flowers
       else if (ct.closeMatch (Color.parseColor("#00ff00"), touchColor, tolerance)){
           vibe.vibrate(200);
           int offset=4;
           boolean touch=true;
           for (int j=0;j<offset;j++){
               touch=touch && touched[16+j];
           }
           if(touch==false){
               if(dX[16]==-1 && dY[16]==-1){
                   touched[16]=true;
                   dX[16]=evX;
                   dY[16]=evY;
                   toast("Yeah!! found FLOWER");

                   int a= settings.getInt("flower", 0);
                   a-=1;
                   editor.putInt("flower",a);
                   editor.commit();
               }
               else if(dX[16]!=-1 && dY[16]!=-1 && (Math.abs(dX[16]-evX)<50) && (Math.abs(dY[16]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[17]==-1 && dY[17]==-1){
                   touched[17]=true;
                   dX[17]=evX;
                   dY[17]=evY;
                   toast("Yeah!! found second FLOWER");

                   int a= settings.getInt("flower", 0);
                   a-=1;
                   editor.putInt("flower",a);
                   editor.commit();
               }
               else if(dX[17]!=-1 && dY[17]!=-1 && (Math.abs(dX[17]-evX)<50) && (Math.abs(dY[17]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[18]==-1 && dY[18]==-1){
                   touched[18]=true;
                   dX[18]=evX;
                   dY[18]=evY;
                   toast("Yeah!! found third FLOWER");

                   int a= settings.getInt("flower", 0);
                   a-=1;
                   editor.putInt("flower",a);
                   editor.commit();
               }
               else if(dX[18]!=-1 && dY[18]!=-1 && (Math.abs(dX[18]-evX)<50) && (Math.abs(dY[18]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[19]==-1 && dY[19]==-1){
                   touched[19]=true;
                   dX[19]=evX;
                   dY[19]=evY;
                   toast("Yeah!! found forth FLOWER");
                   editor.putBoolean("slipperT",true);
                   editor.commit();
                   touches[9]=settings.getBoolean("flowerT",false);

                   int a= settings.getInt("flower", 0);
                   a-=1;
                   editor.putInt("flower",a);
                   editor.commit();
               }

           }
           else {
               toast("already Clicked");
           }
       }
       //5 ants
       else if (ct.closeMatch (Color.parseColor("#ff0000"), touchColor, tolerance)){
           vibe.vibrate(200);
           int offset=5;
           boolean touch=true;
           for (int j=0;j<offset;j++){
               touch=touch && touched[5+j];
           }
           if(touch==false){
               if(dX[5]==-1 && dY[5]==-1){
                   touched[5]=true;
                   dX[5]=evX;
                   dY[5]=evY;
                   toast("Yeah!! found ANT");

                   int a= settings.getInt("ant", 0);
                   a-=1;
                   editor.putInt("ant",a);
                   editor.commit();
               }
               else if(dX[5]!=-1 && dY[5]!=-1 && (Math.abs(dX[5]-evX)<50) && (Math.abs(dY[5]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[6]==-1 && dY[6]==-1){
                   touched[6]=true;
                   dX[6]=evX;
                   dY[6]=evY;
                   toast("Yeah!! found second ANT");

                   int a= settings.getInt("ant", 0);
                   a-=1;
                   editor.putInt("ant",a);
                   editor.commit();
               }
               else if(dX[6]!=-1 && dY[6]!=-1 && (Math.abs(dX[6]-evX)<50) && (Math.abs(dY[6]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[7]==-1 && dY[7]==-1){
                   touched[7]=true;
                   dX[7]=evX;
                   dY[7]=evY;
                   toast("Yeah!! found third ANT");

                   int a= settings.getInt("ant", 0);
                   a-=1;
                   editor.putInt("ant",a);
                   editor.commit();
               }
               else if(dX[7]!=-1 && dY[7]!=-1 && (Math.abs(dX[7]-evX)<50) && (Math.abs(dY[7]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[8]==-1 && dY[8]==-1){
                   touched[8]=true;
                   dX[8]=evX;
                   dY[8]=evY;
                   toast("Yeah!! found forth ANT");

                   int a= settings.getInt("ant", 0);
                   a-=1;
                   editor.putInt("ant",a);
                   editor.commit();
               }
               else if(dX[8]!=-1 && dY[8]!=-1 && (Math.abs(dX[8]-evX)<50) && (Math.abs(dY[8]-evY)<50)){
                   toast("already Clicked");
               }
               else if(dX[9]==-1 && dY[9]==-1){
                   touched[9]=true;
                   dX[9]=evX;
                   dY[9]=evY;
                   toast("Yeah!! found fifth ANT");
                   editor.putBoolean("antT",true);
                   editor.commit();
                   touches[4]=settings.getBoolean("antT",false);

                   int a= settings.getInt("ant", 0);
                   a-=1;
                   editor.putInt("ant",a);
                   editor.commit();
               }

           }
           else {
               toast("already Clicked");
           }
       }



       else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)){
           //nextImage = R.drawable.wai_default;
       }


//    default:
//       handledHere = false;
    }
    return true;
}

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d ("WhereamiActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("WhereamiActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }


    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast

    private void initiatePopupWindow() {
        LayoutInflater inflater = (LayoutInflater) WhereamiActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.screen_popup,
                (ViewGroup) findViewById(R.id.popup_element));

        ImageView iv=(ImageView) layout.findViewById(R.id.imageView);
        TextView tv=(TextView) layout.findViewById(R.id.textView);
        TypedArray imgs = getResources().obtainTypedArray(R.array.hint_imgs);
        // get resource ID by index
        int index=0;
        boolean hint=true;
        for(int g=0;g<12;g++){
            hint=touches[g]&&hint;
            if(hint==false){
                index=g;
                break;
            }
        }
        tv.setText("It's a "+label[index]);
        iv.setImageResource(imgs.getResourceId(index, -1));

        pwindo = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

        btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwindo.dismiss();
            }
        });

        // recycle the array
        imgs.recycle();

    }



@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_hint:
                initiatePopupWindow();
                return true;
            case R.id.menu_zoom:
                Intent intenti=new Intent(WhereamiActivity.this, PictureActivity.class);
                startActivity(intenti);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();

    }
} // end class