package com.om.user.project;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class TestFbShareActivity extends ActionBarActivity {
    private static final int CAMERA_REQUEST = 1888;
     ImageView img;
    Uri uri=null;
    Intent chooser=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        img = (ImageView) findViewById(R.id.imageView);
         Button b1=(Button)findViewById(R.id.btnfbshare);
        Button b2 = (Button) findViewById(R.id.btntakephoto);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File pictures= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String[] listofPictures=pictures.list();

                ArrayList<Uri> arraylist=new ArrayList<Uri>();
                for(String picture: listofPictures)
                {
                    uri=Uri.parse("file://"+pictures.toString()+"/"+picture);
                    arraylist.add(uri);
                }
                Intent intent =new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,arraylist);
                chooser=Intent.createChooser(intent,"Send Multiple Images");
                startActivity(chooser);
              /*  //Intent intentshare=new Intent(getApplicationContext(),FbTestActivity.class);
                //startActivity(intentshare);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                //Uri screenshotUri = Uri.parse("android.resource://com.om.user.project*//*");
                Uri uri=Uri.parse(String.valueOf(img));


                try {
                    InputStream stream = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));*/
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);

        }
    }



    @Override
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

