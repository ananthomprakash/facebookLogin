package com.om.user.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class Capture extends ActionBarActivity {
    private File imageFile;
    private TextView tv2;
   private ImageView iv1;
    private Button btn3;
    Uri tempuri;
    //Bitmap photo;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        tv2=(TextView)findViewById(R.id.tv2);
       iv1=(ImageView)findViewById(R.id.iv1);
        btn3=(Button)findViewById(R.id.btnpic);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
                tempuri= Uri.fromFile(imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);

                //photo= BitmapFactory.decodeFile(String.valueOf(imageFile));
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                startActivityForResult(intent, 0);



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 0) {
       // iv1.setImageBitmap(photo);

           // Bitmap photo = (Bitmap) data.getExtras().get("data");
            //iv1.setImageBitmap(photo);
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(imageFile.exists()){
                        Toast.makeText(getBaseContext(),"The file was saved"+imageFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                        tv2.setText("path"+imageFile.getAbsolutePath());
                        iv1.setImageURI(tempuri);
                    }else{
                        Toast.makeText(getBaseContext(),"oops error!!",Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
