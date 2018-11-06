package com.example.heegi.uls_cafesystem.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heegi.uls_cafesystem.R;
import com.example.heegi.uls_cafesystem.activities.DefaultMenuActivity;
import com.example.heegi.uls_cafesystem.global.NetworkConnector;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class DefaultFragment extends Fragment {
    private AutoScrollViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    Button menu;
    Button membership;
    Button store;
    Button event;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.default_fragment, container, false);

        viewPager = rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setInterval(5000);
        viewPager.startAutoScroll();

        rootView.setBackgroundColor(getResources().getColor(R.color.colorGray));

        menu = (Button)rootView.findViewById(R.id.menu);
        membership = (Button)rootView.findViewById(R.id.membership);
        store = (Button)rootView.findViewById(R.id.store);
        event = (Button)rootView.findViewById(R.id.event);
        ClickListener CL = new ClickListener();
        menu.setOnClickListener(CL);
        membership.setOnClickListener(CL);
        store.setOnClickListener(CL);
        event.setOnClickListener(CL);
        return rootView;
    }

    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu:
                    Intent intent = new Intent(getActivity(), DefaultMenuActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.membership:
                    break;
                case R.id.lev1_order_list:
                    break;
                case R.id.event:
                    break;
            }
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.one, R.drawable.two, R.drawable.three};
        private ArrayList<String> data;


        public ViewPagerAdapter(Context context) {
            this.context = context;
            data = new ArrayList<>();
            data.add(NetworkConnector.getInstance().getDefaultUrl()+"src/one.png");
            data.add(NetworkConnector.getInstance().getDefaultUrl()+"src/two.png");
            data.add(NetworkConnector.getInstance().getDefaultUrl()+"src/three.png");
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //뷰페이지 슬라이딩 할 레이아웃 인플레이션
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.slider,null);
            ImageView image_container = (ImageView) v.findViewById(R.id.slider);
            Glide.with(context).load(data.get(position)).into(image_container);
            container.addView(v);
            return v;
        }
    }
}
