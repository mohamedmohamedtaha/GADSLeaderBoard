package com.mohamedtaha.imagine.gadsleaderboard.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mohamedtaha.imagine.gadsleaderboard.checkConnection.NetworkConnection;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.FragmentSkillIQLeadersBinding;
import com.mohamedtaha.imagine.gadsleaderboard.model.skilliqleaders.SkillIQLeaders;
import com.mohamedtaha.imagine.gadsleaderboard.rest.APIServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedtaha.imagine.gadsleaderboard.rest.RetrofitClient.getRetrofit;

public class SkillIQLeadersFragment extends Fragment {
    private FragmentSkillIQLeadersBinding skillIQLeadersBinding;
    private APIServices apiServices;
    private static final String TAG = "SkillIQLeadersFragment";
    private boolean isNetworkAvaliable  = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        skillIQLeadersBinding = FragmentSkillIQLeadersBinding.inflate(inflater);
        isNetworkAvaliable = NetworkConnection.networkConnectivity(getActivity());
        if (isNetworkAvaliable){
            getSkillIqLeaders();
        }else {
            skillIQLeadersBinding.recyclerViewSkillIQLeadersFragment.setVisibility(View.GONE);
            skillIQLeadersBinding.progressBarSkillIQLeadersFragment.setVisibility(View.GONE);
            skillIQLeadersBinding.textViewNoInternetSkillIQLeadersFragment.setVisibility(View.VISIBLE);
        }

        return skillIQLeadersBinding.getRoot();

    }

    private void getSkillIqLeaders() {
        apiServices = getRetrofit().create(APIServices.class);
        Call<List<SkillIQLeaders>> listCall = apiServices.getSkillIqLeaders();
        listCall.enqueue(new Callback<List<SkillIQLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeaders>> call, Response<List<SkillIQLeaders>> response) {
                List<SkillIQLeaders> skillIQLeaders = response.body();
                try {
                    skillIQLeadersBinding.setSkillIqLearners(skillIQLeaders);
                    skillIQLeadersBinding.progressBarSkillIQLeadersFragment.setVisibility(View.GONE);
                    skillIQLeadersBinding.textViewNoInternetSkillIQLeadersFragment.setVisibility(View.GONE);
                } catch (Exception e) {
                    skillIQLeadersBinding.progressBarSkillIQLeadersFragment.setVisibility(View.GONE);
                    skillIQLeadersBinding.textViewNoInternetSkillIQLeadersFragment.setVisibility(View.GONE);
                    Log.d(TAG,"learning :" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<SkillIQLeaders>> call, Throwable t) {
                skillIQLeadersBinding.progressBarSkillIQLeadersFragment.setVisibility(View.GONE);
                skillIQLeadersBinding.textViewNoInternetSkillIQLeadersFragment.setVisibility(View.GONE);
                Log.d(TAG,"learning :" + t.getMessage());
            }
        });

    }
}