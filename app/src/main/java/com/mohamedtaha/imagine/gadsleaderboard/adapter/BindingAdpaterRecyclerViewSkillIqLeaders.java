package com.mohamedtaha.imagine.gadsleaderboard.adapter;

import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.gadsleaderboard.model.skilliqleaders.SkillIQLeaders;

import java.util.List;

public class BindingAdpaterRecyclerViewSkillIqLeaders {
    @BindingAdapter("skillIqLearners")
    public static void setSkillIqLearners(RecyclerView recyclerView, List<SkillIQLeaders> skillIQLeaders){
        if (skillIQLeaders == null){
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null){
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        AdapterSkillIqLearders  adapterSkillIqLearders = new AdapterSkillIqLearders(recyclerView.getContext(),skillIQLeaders);
        recyclerView.setAdapter(adapterSkillIqLearders);

    }
}
