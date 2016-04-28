package com.example.benben.animation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.benben.animation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/4/25.
 * LayoutAnimationsController可以用于实现多个控件按顺序一个一个的显示
 * 1）LayoutAnimationController用于为一个layout里面的控件，或者是一个ViewGroup里面的控件设置统一的动画效果
 * 2）每一个控件都有相同的动画效果
 * 3）控件的动画效果可以在不太的时间显示出来
 * 4）LayoutAnimationController可以在xml文件中设置，也可以在代码中进行
 * <p/>
 * android:delay---动画间隔时间
 * android：animationOrder--动画执行的顺序（normal；顺序，random；随机，reverse；反向显示）
 */
public class LayoutAnimationControllerActivity extends BaseActivity {

    public static void startLayoutAnimationControllerActivity(Activity activity) {
        Intent intent = new Intent(activity, LayoutAnimationControllerActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @InjectView(R.id.layout_list)
    ListView layoutList;
    @InjectView(R.id.layout_button)
    Button layoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layotanimationscontroller);
        ButterKnife.inject(this);
    }

    private ListAdapter createListAdapter() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> m1 = new HashMap<String, String>();
        m1.put("name", "bauble");
        m1.put("sex", "male");
        HashMap<String, String> m2 = new HashMap<String, String>();
        m2.put("name", "Allorry");
        m2.put("sex", "male");
        HashMap<String, String> m3 = new HashMap<String, String>();
        m3.put("name", "Allototy");
        m3.put("sex", "male");
        HashMap<String, String> m4 = new HashMap<String, String>();
        m4.put("name", "boolbe");
        m4.put("sex", "male");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"name", "sex"}, new int[]{R.id.name, R.id.item_sex});
        return simpleAdapter;
    }

    @OnClick({R.id.topLeft, R.id.layout_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topLeft:
                finish();
                break;
            case R.id.layout_button:
                layoutList.setAdapter(createListAdapter());

                break;
        }
    }

    /**在代码中去实现效果*/
    /**
     * 1.创建一个Animation对象：可以通过装载xml文件，或者是直接使用Animation的构造方法创建Animation对象
     * Animation animation= (Animation)AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.list_layout)
     *2.创建LayoutAnimationController对象
     * LayoutAnimationController controller=new LayoutAnimationController(animation)
     * 3.设置控件的显示顺序和延时时间
     * controller.setOrder（LayoutAnimationController.ORDER_NORMAL)
     * controller.setDelay(0.5f)
     * 4.为listView设置LayoutAnimationController属性
     * listView。setLayoutAnimation（controller）
     */
}
