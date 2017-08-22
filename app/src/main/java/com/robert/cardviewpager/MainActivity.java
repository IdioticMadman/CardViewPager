package com.robert.cardviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author: robert
 * @date: 2017/8/22
 * @time: 15:29
 * @说明:
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        List<String> data = Arrays.asList("0", "1", "2", "3", "4");
        DemoCardPager demoCardPager = new DemoCardPager(data, this);
        ShadowAndScaleTransformer shadowAndScaleTransformer = new ShadowAndScaleTransformer(demoCardPager);
        mViewPager.setAdapter(demoCardPager);
        mViewPager.setCurrentItem(demoCardPager.getStartPageIndex());
        mViewPager.setPageTransformer(true, shadowAndScaleTransformer);
    }
}
