package com.cling.cling;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tier on 09.09.14.
 */

public class SimplePageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private ViewPager pager;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private LinearLayout itemContainer;
    private List<ImageView> items;
    private int highlightIndex = -1;

    private OnClickListener itemClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            pager.setCurrentItem(position);
        }
    };

    public void setHighlightIndex(int highlightIndex) {
        this.highlightIndex = highlightIndex;
    }

    public SimplePageIndicator(Context context) {

        super(context);
        this.context = context;
        setup();
    }

    public SimplePageIndicator(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
        setup();
    }

    public SimplePageIndicator(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        this.context = context;
        setup();
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    private void setup() {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {

            inflater.inflate(R.layout.view_pager_indicator, this);
            itemContainer = (LinearLayout) findViewById(R.id.pagerIndicatorContainer);
            items = new ArrayList<ImageView>();
        }
    }

    /**
     * Notifies the pager indicator that the data set has changed.
     * Be sure to notify the pager as well (though you may wish to place
     * that call in here yourself).
     */

    public void notifyDataSetChanged() {

        if (pager != null && pager.getAdapter() != null) {

            // remove the old items (if any exist)
            itemContainer.removeAllViews();

            // I'm sure this could be optimised a lot more, eg,
            // by reusing existing ImageViews, but it
            // does the job well enough for now.
            items.removeAll(items);

            // now create the new items.
            for (int i = 0; i < pager.getAdapter().getCount(); i++) {

                ImageView item = new ImageView(context);
                LayoutParams params = new LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );
                item.setPadding(10, 10, 10, 10);
                item.setLayoutParams(params);

                if (i == pager.getCurrentItem()) {
                    item.setImageResource(R.drawable.page_indicator_on);
                } else {

                    if (i == highlightIndex) {
                        item.setImageResource(R.drawable.page_indicator_highlight);
                    } else {
                        item.setImageResource(R.drawable.page_indicator_off);
                    }
                }

                item.setTag(i);
                item.setOnClickListener(itemClickListener);
                items.add(item);

                itemContainer.addView(item);
            }
        }
    }

    public ViewPager getViewPager() {
        return pager;
    }

    public void setViewPager(ViewPager pager) {

        this.pager = pager;
        this.pager.setOnPageChangeListener(this);
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {

        this.onPageChangeListener = onPageChangeListener;
    }

    private void setCurrentItem(int position) {

        if (pager != null && pager.getAdapter() != null) {

            int numberOfItems = pager.getAdapter().getCount();

            for (int i = 0; i < numberOfItems; i++) {
                ImageView item = items.get(i);
                if (item != null) {
                    if (i == position) {
                        item.setImageResource(R.drawable.page_indicator_on);
                    } else {
                        if (i == highlightIndex) {
                            item.setImageResource(R.drawable.page_indicator_highlight);
                        } else {
                            item.setImageResource(R.drawable.page_indicator_off);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setCurrentItem(position);
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageSelected(position);
        }
    }
}
