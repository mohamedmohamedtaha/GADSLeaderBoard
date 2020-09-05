package com.mohamedtaha.imagine.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.CustomSkillIqLeadersBinding;
import com.mohamedtaha.imagine.gadsleaderboard.model.skilliqleaders.SkillIQLeaders;

import java.util.List;

public class AdapterSkillIqLearders extends RecyclerView.Adapter<AdapterSkillIqLearders.SkillIqLearders> {
    private Context context;
    private List<SkillIQLeaders> skillIQLeaders;
    private CustomSkillIqLeadersBinding customSkillIqLeadersBinding;

    public AdapterSkillIqLearders(Context context, List<SkillIQLeaders> skillIQLeaders) {
        this.context = context;
        this.skillIQLeaders = skillIQLeaders;
    }

    @NonNull
    @Override
    public SkillIqLearders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        customSkillIqLeadersBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_skill_iq_leaders, parent, false);

        return new SkillIqLearders(customSkillIqLeadersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqLearders holder, int position) {
        SkillIQLeaders skillIQLeader = skillIQLeaders.get(position);
        holder.bind(skillIQLeader);

    }

    @Override
    public int getItemCount() {
        if (skillIQLeaders != null) {
            return skillIQLeaders.size();
        } else {
            return 0;
        }
    }

    class SkillIqLearders extends RecyclerView.ViewHolder {
        public SkillIqLearders(@NonNull CustomSkillIqLeadersBinding customSkillIqLeadersBinding) {
            super(customSkillIqLeadersBinding.getRoot());
        }

        public void bind(SkillIQLeaders skillIQLeaders) {
            customSkillIqLeadersBinding.setSkillIqLeader(skillIQLeaders);
            customSkillIqLeadersBinding.executePendingBindings();
        }
    }
}
