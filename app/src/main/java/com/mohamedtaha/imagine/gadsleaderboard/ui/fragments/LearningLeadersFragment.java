package com.mohamedtaha.imagine.gadsleaderboard.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.checkConnection.NetworkConnection;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.FragmentLearningLeadersBinding;
import com.mohamedtaha.imagine.gadsleaderboard.model.learningleaders.LearningLeader;
import com.mohamedtaha.imagine.gadsleaderboard.rest.APIServices;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedtaha.imagine.gadsleaderboard.rest.RetrofitClient.getRetrofit;

public class LearningLeadersFragment extends Fragment {
    private static final String TAG = "LearningLeadersFragment";
    private FragmentLearningLeadersBinding learningLeadersBinding;
    private APIServices apiServices = null;
    private boolean isNetworkAvaliable = false;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        learningLeadersBinding = FragmentLearningLeadersBinding.inflate(inflater);
     //   View view =  inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        isNetworkAvaliable = NetworkConnection.networkConnectivity(getActivity());
        if (isNetworkAvaliable){
            getLearningLeader();
        }else {
            learningLeadersBinding.recyclerViewLearningLeadersFragment.setVisibility(View.GONE);
            learningLeadersBinding.progressBarLearningLeadersFragment.setVisibility(View.GONE);
            learningLeadersBinding.textViewNoInternetLearningLeadersFragment.setVisibility(View.VISIBLE);

        }


        return learningLeadersBinding.getRoot();
    }
    private void getLearningLeader(){
        apiServices = getRetrofit().create(APIServices.class);
        Call<List<LearningLeader>> learningLeaderCall = apiServices.getLearningLeader();
        learningLeaderCall.enqueue(new Callback<List<LearningLeader>>() {
            @Override
            public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                List<LearningLeader> learningLeader = response.body();
                try {
                    learningLeadersBinding.setLearningLeaders(learningLeader);
                    learningLeadersBinding.progressBarLearningLeadersFragment.setVisibility(View.GONE);
                    learningLeadersBinding.textViewNoInternetLearningLeadersFragment.setVisibility(View.GONE);
                }catch (Exception e){
                    Log.d(TAG,"error  :" +  e.getMessage());
                    learningLeadersBinding.progressBarLearningLeadersFragment.setVisibility(View.GONE);
                    learningLeadersBinding.textViewNoInternetLearningLeadersFragment.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                Log.d(TAG,"onFailure  :" +  t.getMessage());
                Toast.makeText(getActivity(),"Learning" + t.getMessage() ,Toast.LENGTH_LONG);
                learningLeadersBinding.progressBarLearningLeadersFragment.setVisibility(View.GONE);
                learningLeadersBinding.textViewNoInternetLearningLeadersFragment.setVisibility(View.GONE);
            }
        });
    }
}