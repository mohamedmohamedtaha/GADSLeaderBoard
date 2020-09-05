package com.mohamedtaha.imagine.gadsleaderboard.rest;

import com.mohamedtaha.imagine.gadsleaderboard.model.learningleaders.LearningLeader;
import com.mohamedtaha.imagine.gadsleaderboard.model.skilliqleaders.SkillIQLeaders;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {

    @GET("hours")
    Call<List<LearningLeader>> getLearningLeader();

    @GET("skilliq")
    Call<List<SkillIQLeaders>> getSkillIqLeaders();

    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> uploadProject(@Field("entry.1824927963") String email,
                                     @Field("entry.1877115667") String first_name,
                                     @Field("entry.2006916086") String last_name,
                                     @Field("entry.284483984") String link_github);

}
