package com.robert.cardviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: robert
 * @date: 2017/8/22
 * @time: 15:29
 * @说明:
 */
public class DemoCardPager extends PagerAdapter implements CardAdapter {

    private final List<String> data;
    private final Context context;
    private List<CardView> mViews;
    private float mBaseElevation;

    public DemoCardPager(List<String> data, Context context) {
        this.data = new ArrayList<>();
        mViews = new ArrayList<>(data.size());
        for (String str : data) {
            this.data.add(str);
            this.mViews.add(null);
        }
        this.context = context;
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    public int getStartPageIndex() {
        int index = getCount() / 2;
        int remainder = index % data.size();
        index = index - remainder;
        return index;
    }

    private int getIndexOfPosition(int position) {
        return position % data.size();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int realPosition = getIndexOfPosition(position);
        if (mViews.get(realPosition) == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_card_layout, container, false);
            CardView cardView = (CardView) view.findViewById(R.id.cardView);
            TextView textView = (TextView) view.findViewById(R.id.tv_content);
            if (mBaseElevation == 0) {
                mBaseElevation = cardView.getCardElevation();
            }
            cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
            textView.setText(data.get(realPosition));
            mViews.set(realPosition, cardView);
            container.addView(view);
            return view;
        } else {
            CardView view = mViews.get(realPosition);
            ViewParent parent = view.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(view);
            }
            container.addView(view);
            return view;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
