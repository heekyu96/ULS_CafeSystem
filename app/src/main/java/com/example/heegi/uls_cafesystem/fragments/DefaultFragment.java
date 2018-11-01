package com.example.heegi.uls_cafesystem.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.heegi.uls_cafesystem.R;

public class DefaultFragment extends Fragment {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.default_fragment, container, false);

        viewPager = rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());


        viewPager.setAdapter(viewPagerAdapter);
        return rootView;
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.one, R.drawable.two, R.drawable.three};


        public ViewPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            ViewPager viewPager = (ViewPager) container;
            View view = (View) object;
            viewPager.removeView(view);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.slider, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.slider);
            imageView.setImageResource(images[position]);

            ViewPager viewPager = (ViewPager) container;
            viewPager.addView(view, position);
            return view;
        }
    }
}
