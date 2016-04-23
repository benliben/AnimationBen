package com.example.benben.animation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.benben.animation.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/4/23.
 */
public class FrescoActivity extends AppCompatActivity {

    private SimpleDraweeView mImgView;

    public static void startFrescoActivity(Activity activity) {

        Log.i("JAM", "--");
        Intent intent = new Intent(activity, FrescoActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        mImgView = (SimpleDraweeView) this.findViewById(R.id.fresco_img);

        Uri uri = Uri.parse("http://i3.hoopchina.com.cn/blogfile/201212/21/135605895528067.gif");
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        mImgView.setController(controller);
    }
}
