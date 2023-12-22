package com.example.ontapandroid2_4.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ontapandroid2_4.Adapter.ColltectionAdapter;
import com.example.ontapandroid2_4.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class fragment_collection extends Fragment {

    ColltectionAdapter colltectionAdapter;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.collection_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);

        colltectionAdapter = new ColltectionAdapter(this);
        viewPager2.setAdapter(colltectionAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Dich Vu");
                        break;
                    case 1:
                        tab.setText("Thong Tin");
                        break;
                    default:
                        tab.setText("Dich Vu");
                        break;
                }
            }
        });
        mediator.attach();
    }
}
