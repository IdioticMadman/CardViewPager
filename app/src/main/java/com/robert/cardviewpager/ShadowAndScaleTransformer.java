package com.robert.cardviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * @author: robert
 * @date: 2017/8/22
 * @time: 15:29
 * @说明:
 */
public class ShadowAndScaleTransformer implements ViewPager.PageTransformer {
    private DemoCardPager mAdapter;

    public ShadowAndScaleTransformer(DemoCardPager adapter) {
        mAdapter = adapter;
    }

    @Override
    public void transformPage(View page, float position) {
        if (page instanceof CardView) {
            final CardView cardView = (CardView) page;
            final float baseElevation = mAdapter.getBaseElevation();
            if (position < -1) {
                cardView.setScaleX(1f);
                cardView.setScaleY(1f);
                cardView.setCardElevation(baseElevation);
            } else if (position <= 1) {
                // [-1,1]
                if (position < 0) {
                    //[-1，0]
                    cardView.setScaleX((float) (1 + 0.2 * (1 + position)));
                    cardView.setScaleY((float) (1 + 0.2 * (1 + position)));
                    cardView.setCardElevation((baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 + position)));
                } else {
                    //[0，1]
                    cardView.setScaleX((float) (1 + 0.2 * (1 - position)));
                    cardView.setScaleY((float) (1 + 0.2 * (1 - position)));
                    cardView.setCardElevation((baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - position)));
                }
            } else { // (1,+Infinity]
                cardView.setScaleX(1f);
                cardView.setScaleY(1f);
                cardView.setCardElevation(baseElevation);
            }
        } else {
            throw new RuntimeException("当前页不是CardView组成不能使用此transformer");
        }

    }
}
