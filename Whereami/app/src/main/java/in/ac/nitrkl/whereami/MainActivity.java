package in.ac.nitrkl.whereami;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by surya on 5/4/17.
 */

public class MainActivity extends AppCompatActivity {

    Button enter;
    TextView o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o12;
    Toolbar toolbar;
    public static final String FILE_NAME = "datafile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();

        editor.putInt("sun",1);
        editor.putInt("earth",1);
        editor.putInt("owl",2);
        editor.putInt("monkey",1);
        editor.putInt("ant",5);
        editor.putInt("caterpillar",1);
        editor.putInt("bwing",1);
        editor.putInt("cat",2);
        editor.putInt("slipper",1);
        editor.putInt("flower",4);
        editor.putInt("book",1);
        editor.putInt("kite",1);

        editor.putBoolean("sunT",false);
        editor.putBoolean("earthT",false);
        editor.putBoolean("owlT",false);
        editor.putBoolean("monkeyT",false);
        editor.putBoolean("antT",false);
        editor.putBoolean("caterpillarT",false);
        editor.putBoolean("bwingT",false);
        editor.putBoolean("catT",false);
        editor.putBoolean("slipperT",false);
        editor.putBoolean("flowerT",false);
        editor.putBoolean("bookT",false);
        editor.putBoolean("kiteT",false);

        editor.commit();

        enter=(Button)findViewById(R.id.button);
        o1=(TextView)findViewById(R.id.obj1);
        o2=(TextView)findViewById(R.id.obj2);
        o3=(TextView)findViewById(R.id.obj3);
        o4=(TextView)findViewById(R.id.obj4);
        o5=(TextView)findViewById(R.id.obj5);
        o6=(TextView)findViewById(R.id.obj6);
        o7=(TextView)findViewById(R.id.obj7);
        o8=(TextView)findViewById(R.id.obj8);
        o9=(TextView)findViewById(R.id.obj9);
        o10=(TextView)findViewById(R.id.obj10);
        o11=(TextView)findViewById(R.id.obj11);
        o12=(TextView)findViewById(R.id.obj12);

        //sun
        if (settings.getInt("sun",1)>0){
            o1.setTextColor(Color.RED);
            o1.setText(""+settings.getInt("sun",1)+ " Sun"+"\n");
        }else{
            o1.setTextColor(Color.BLUE);
            o1.setText(""+0+ " Sun"+"\n");
        }
        //earth
        if (settings.getInt("earth",1)>0){
            o2.setTextColor(Color.RED);
            o2.setText(""+settings.getInt("earth",1)+ " Earth"+"\n");
        }else{
            o2.setTextColor(Color.BLUE);
            o2.setText(""+0+ " Earth"+"\n");
        }

        //owl
        if (settings.getInt("owl",2)>0){
            o3.setTextColor(Color.RED);
            o3.setText(""+settings.getInt("owl",2)+ " Owl"+"\n");
        }else{
            o3.setTextColor(Color.BLUE);
            o3.setText(""+0+ " Owl"+"\n");
        }

        //monkey
        if (settings.getInt("monkey",1)>0){
            o4.setTextColor(Color.RED);
            o4.setText(""+settings.getInt("monkey",1)+ " Monkey"+"\n");
        }else{
            o4.setTextColor(Color.BLUE);
            o4.setText(""+0+ " Monkey"+"\n");
        }

        //ant
        if (settings.getInt("ant",5)>0){
            o5.setTextColor(Color.RED);
            o5.setText(""+settings.getInt("ant",5)+" Ant"+"\n");
        }else{
            o5.setTextColor(Color.BLUE);
            o5.setText(""+0+" Ant"+"\n");
        }

        //caterpillar
        if (settings.getInt("caterpillar",1)>0){
            o6.setTextColor(Color.RED);
            o6.setText(""+settings.getInt("caterpillar",1)+ " Caterpillar"+"\n");
        }else{
            o6.setTextColor(Color.BLUE);
            o6.setText(""+0+ " Caterpillar"+"\n");
        }

        //butterflywing
        if (settings.getInt("bwing",1)>0){
            o7.setTextColor(Color.RED);
            o7.setText(""+settings.getInt("bwing",1)+ " Butterfly Wing"+"\n");
        }else{
            o7.setTextColor(Color.BLUE);
            o7.setText(""+0+ " Butterfly Wing"+"\n");
        }

        //cat
        if (settings.getInt("cat",2)>0){
            o8.setTextColor(Color.RED);
            o8.setText(""+settings.getInt("cat",2)+ " Cat"+"\n");
        }else{
            o8.setTextColor(Color.BLUE);
            o8.setText(""+0+ " Cat"+"\n");
        }

        //slipper
        if (settings.getInt("slipper",1)>0){
            o9.setTextColor(Color.RED);
            o9.setText(""+settings.getInt("slipper",1)+ " Slipper"+"\n" );
        }else{
            o9.setTextColor(Color.BLUE);
            o9.setText(""+0+ " Slipper"+"\n" );
        }

        //flower
        if (settings.getInt("flower",4)>0){
            o10.setTextColor(Color.RED);
            o10.setText(""+settings.getInt("flower",4)+ " Flower"+"\n");
        }else{
            o10.setTextColor(Color.BLUE);
            o10.setText(""+0+ " Flower"+"\n");
        }

        //book
        if (settings.getInt("book",1)>0){
            o11.setTextColor(Color.RED);
            o11.setText(""+settings.getInt("book",1)+ " Book"+"\n");
        }else{
            o11.setTextColor(Color.BLUE);
            o11.setText(""+0+ " Book"+"\n");
        }

        //kite
        if (settings.getInt("kite",1)>0){
            o12.setTextColor(Color.RED);
            o12.setText(""+settings.getInt("kite",1)+ " Kite"+"\n");
        }else{
            o12.setTextColor(Color.BLUE);
            o12.setText(""+0+ " Kite"+"\n");
        }


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, WhereamiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //sun
        if (settings.getInt("sun",1)>0){
            o1.setTextColor(Color.RED);
            o1.setText(""+settings.getInt("sun",1)+ " Sun"+"\n");
        }else{
            o1.setTextColor(Color.BLUE);
            o1.setText(""+0+ " Sun"+"\n");
        }
        //earth
        if (settings.getInt("earth",1)>0){
            o2.setTextColor(Color.RED);
            o2.setText(""+settings.getInt("earth",1)+ " Earth"+"\n");
        }else{
            o2.setTextColor(Color.BLUE);
            o2.setText(""+0+ " Earth"+"\n");
        }

        //owl
        if (settings.getInt("owl",2)>0){
            o3.setTextColor(Color.RED);
            o3.setText(""+settings.getInt("owl",2)+ " Owl"+"\n");
        }else{
            o3.setTextColor(Color.BLUE);
            o3.setText(""+0+ " Owl"+"\n");
        }

        //monkey
        if (settings.getInt("monkey",1)>0){
            o4.setTextColor(Color.RED);
            o4.setText(""+settings.getInt("monkey",1)+ " Monkey"+"\n");
        }else{
            o4.setTextColor(Color.BLUE);
            o4.setText(""+0+ " Monkey"+"\n");
        }

        //ant
        if (settings.getInt("ant",5)>0){
            o5.setTextColor(Color.RED);
            o5.setText(""+settings.getInt("ant",5)+" Ant"+"\n");
        }else{
            o5.setTextColor(Color.BLUE);
            o5.setText(""+0+" Ant"+"\n");
        }

        //caterpillar
        if (settings.getInt("caterpillar",1)>0){
            o6.setTextColor(Color.RED);
            o6.setText(""+settings.getInt("caterpillar",1)+ " Caterpillar"+"\n");
        }else{
            o6.setTextColor(Color.BLUE);
            o6.setText(""+0+ " Caterpillar"+"\n");
        }

        //butterflywing
        if (settings.getInt("bwing",1)>0){
            o7.setTextColor(Color.RED);
            o7.setText(""+settings.getInt("bwing",1)+ " Butterfly Wing"+"\n");
        }else{
            o7.setTextColor(Color.BLUE);
            o7.setText(""+0+ " Butterfly Wing"+"\n");
        }

        //cat
        if (settings.getInt("cat",2)>0){
            o8.setTextColor(Color.RED);
            o8.setText(""+settings.getInt("cat",2)+ " Cat"+"\n");
        }else{
            o8.setTextColor(Color.BLUE);
            o8.setText(""+0+ " Cat"+"\n");
        }

        //slipper
        if (settings.getInt("slipper",1)>0){
            o9.setTextColor(Color.RED);
            o9.setText(""+settings.getInt("slipper",1)+ " Slipper"+"\n" );
        }else{
            o9.setTextColor(Color.BLUE);
            o9.setText(""+0+ " Slipper"+"\n" );
        }

        //flower
        if (settings.getInt("flower",4)>0){
            o10.setTextColor(Color.RED);
            o10.setText(""+settings.getInt("flower",4)+ " Flower"+"\n");
        }else{
            o10.setTextColor(Color.BLUE);
            o10.setText(""+0+ " Flower"+"\n");
        }

        //book
        if (settings.getInt("book",1)>0){
            o11.setTextColor(Color.RED);
            o11.setText(""+settings.getInt("book",1)+ " Book"+"\n");
        }else{
            o11.setTextColor(Color.BLUE);
            o11.setText(""+0+ " Book"+"\n");
        }

        //kite
        if (settings.getInt("kite",1)>0){
            o12.setTextColor(Color.RED);
            o12.setText(""+settings.getInt("kite",1)+ " Kite"+"\n");
        }else{
            o12.setTextColor(Color.BLUE);
            o12.setText(""+0+ " Kite"+"\n");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //sun
        if (settings.getInt("sun",1)>0){
            o1.setTextColor(Color.RED);
            o1.setText(""+settings.getInt("sun",1)+ " Sun"+"\n");
        }else{
            o1.setTextColor(Color.BLUE);
            o1.setText(""+0+ " Sun"+"\n");
        }
        //earth
        if (settings.getInt("earth",1)>0){
            o2.setTextColor(Color.RED);
            o2.setText(""+settings.getInt("earth",1)+ " Earth"+"\n");
        }else{
            o2.setTextColor(Color.BLUE);
            o2.setText(""+0+ " Earth"+"\n");
        }

        //owl
        if (settings.getInt("owl",2)>0){
            o3.setTextColor(Color.RED);
            o3.setText(""+settings.getInt("owl",2)+ " Owl"+"\n");
        }else{
            o3.setTextColor(Color.BLUE);
            o3.setText(""+0+ " Owl"+"\n");
        }

        //monkey
        if (settings.getInt("monkey",1)>0){
            o4.setTextColor(Color.RED);
            o4.setText(""+settings.getInt("monkey",1)+ " Monkey"+"\n");
        }else{
            o4.setTextColor(Color.BLUE);
            o4.setText(""+0+ " Monkey"+"\n");
        }

        //ant
        if (settings.getInt("ant",5)>0){
            o5.setTextColor(Color.RED);
            o5.setText(""+settings.getInt("ant",5)+" Ant"+"\n");
        }else{
            o5.setTextColor(Color.BLUE);
            o5.setText(""+0+" Ant"+"\n");
        }

        //caterpillar
        if (settings.getInt("caterpillar",1)>0){
            o6.setTextColor(Color.RED);
            o6.setText(""+settings.getInt("caterpillar",1)+ " Caterpillar"+"\n");
        }else{
            o6.setTextColor(Color.BLUE);
            o6.setText(""+0+ " Caterpillar"+"\n");
        }

        //butterflywing
        if (settings.getInt("bwing",1)>0){
            o7.setTextColor(Color.RED);
            o7.setText(""+settings.getInt("bwing",1)+ " Butterfly Wing"+"\n");
        }else{
            o7.setTextColor(Color.BLUE);
            o7.setText(""+0+ " Butterfly Wing"+"\n");
        }

        //cat
        if (settings.getInt("cat",2)>0){
            o8.setTextColor(Color.RED);
            o8.setText(""+settings.getInt("cat",2)+ " Cat"+"\n");
        }else{
            o8.setTextColor(Color.BLUE);
            o8.setText(""+0+ " Cat"+"\n");
        }

        //slipper
        if (settings.getInt("slipper",1)>0){
            o9.setTextColor(Color.RED);
            o9.setText(""+settings.getInt("slipper",1)+ " Slipper"+"\n" );
        }else{
            o9.setTextColor(Color.BLUE);
            o9.setText(""+0+ " Slipper"+"\n" );
        }

        //flower
        if (settings.getInt("flower",4)>0){
            o10.setTextColor(Color.RED);
            o10.setText(""+settings.getInt("flower",4)+ " Flower"+"\n");
        }else{
            o10.setTextColor(Color.BLUE);
            o10.setText(""+0+ " Flower"+"\n");
        }

        //book
        if (settings.getInt("book",1)>0){
            o11.setTextColor(Color.RED);
            o11.setText(""+settings.getInt("book",1)+ " Book"+"\n");
        }else{
            o11.setTextColor(Color.BLUE);
            o11.setText(""+0+ " Book"+"\n");
        }

        //kite
        if (settings.getInt("kite",1)>0){
            o12.setTextColor(Color.RED);
            o12.setText(""+settings.getInt("kite",1)+ " Kite"+"\n");
        }else{
            o12.setTextColor(Color.BLUE);
            o12.setText(""+0+ " Kite"+"\n");
        }
    }
}
