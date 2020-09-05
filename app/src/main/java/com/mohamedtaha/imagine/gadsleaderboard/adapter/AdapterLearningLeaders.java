package com.mohamedtaha.imagine.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.CustomLearningLeadersBinding;
import com.mohamedtaha.imagine.gadsleaderboard.model.learningleaders.LearningLeader;

import java.util.List;

public class AdapterLearningLeaders extends RecyclerView.Adapter<AdapterLearningLeaders.LearningLeadersViewHolder> {
    private Context context;
    private List<LearningLeader> learningLeaders;
    private CustomLearningLeadersBinding customLearningLeadersBinding;

    public AdapterLearningLeaders(Context context, List<LearningLeader> learningLeaders) {
        this.context = context;
        this.learningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        customLearningLeadersBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_learning_leaders, parent, false);
        return new LearningLeadersViewHolder(customLearningLeadersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder holder, int position) {
        LearningLeader learningLeader = learningLeaders.get(position);
        holder.bind(learningLeader);
    }

    @Override
    public int getItemCount() {
        if (learningLeaders != null){
           return learningLeaders.size();
        }else {
            return 0;
        }
    }

    class LearningLeadersViewHolder extends RecyclerView.ViewHolder {

        public LearningLeadersViewHolder(@NonNull CustomLearningLeadersBinding customLearningLeadersBinding) {
            super(customLearningLeadersBinding.getRoot());
        }

        public void bind(LearningLeader learningLeader) {
            customLearningLeadersBinding.setLearningLeader(learningLeader);
            customLearningLeadersBinding.executePendingBindings();
        }
    }
}
