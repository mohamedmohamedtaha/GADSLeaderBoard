package com.mohamedtaha.imagine.gadsleaderboard.adapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.gadsleaderboard.model.learningleaders.LearningLeader;

import java.util.List;

public class BindingAdapterRecyclerViewLearningLeaders {
    @BindingAdapter("learningLeadersList")
    public static void setLearningLeadersList(RecyclerView recyclerView, List<LearningLeader> learningLeaders){
        if (learningLeaders == null){
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null){
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        AdapterLearningLeaders adapterLearningLeaders = (AdapterLearningLeaders)recyclerView.getAdapter();
        if (adapterLearningLeaders == null){
            adapterLearningLeaders = new AdapterLearningLeaders(recyclerView.getContext(),learningLeaders);
            recyclerView.setAdapter(adapterLearningLeaders);
        }
    }
}
