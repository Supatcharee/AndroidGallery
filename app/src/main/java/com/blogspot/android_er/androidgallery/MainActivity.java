package com.blogspot.android_er.androidgallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements MyRecyclerViewAdapter.OnItemClickListener{

    private RecyclerView myRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    TextView textInfo;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInfo = (TextView)findViewById(R.id.info);
        textInfo.setMovementMethod(new ScrollingMovementMethod());

        myRecyclerView = (RecyclerView)findViewById(R.id.myrecyclerview);
        linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        prepareGallery();
    }

    private void prepareGallery(){
        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/Collections/";

        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();
        for (File file : files){
            Uri uri = Uri.fromFile(file);
            myRecyclerViewAdapter.add(
                    myRecyclerViewAdapter.getItemCount(),
                    uri);
        }
    }

    @Override
    public void onItemClick(MyRecyclerViewAdapter.ItemHolder item, int position) {

        String stringitemUri = item.getItemUri();
        Toast.makeText(MainActivity.this, stringitemUri, Toast.LENGTH_SHORT).show();
        imageView =(ImageView)findViewById(R.id.info1);
        Bitmap bmp = BitmapFactory.decodeFile(item.getItemUri());
        bmp = Bitmap.createScaledBitmap(bmp,130,500,false);
        imageView.setImageBitmap(bmp);

    }


}
