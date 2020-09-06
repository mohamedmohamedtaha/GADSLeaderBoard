package com.mohamedtaha.imagine.gadsleaderboard.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mohamedtaha.imagine.gadsleaderboard.ClickSubmit;
import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.checkConnection.NetworkConnection;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.ActivitySubmitBinding;
import com.mohamedtaha.imagine.gadsleaderboard.rest.APIServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedtaha.imagine.gadsleaderboard.rest.RetrofitClient.getRetrofitGitHub;

public class SubmitActivity extends AppCompatActivity implements ClickSubmit {
    private static final String TAG = "SubmitActivity";
    private ActivitySubmitBinding activitySubmitBinding;
    private Dialog dialog;
    private APIServices apiServices;
    private boolean isNetworkAvaliable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySubmitBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        activitySubmitBinding.setClickSubmit(this::submit);

        setToolbar();

    }

    private void uploadProject(String email_address,String first_name, String last_name,  String project_link) {
        activitySubmitBinding.ProgressBarSubmitFragment.setVisibility(View.VISIBLE);
        apiServices = getRetrofitGitHub().create(APIServices.class);
        apiServices.uploadProject( email_address,first_name, last_name, project_link).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                    showDialogSubmission(R.string.submit_successful, R.drawable.ic_right);
                    activitySubmitBinding.ProgressBarSubmitFragment.setVisibility(View.GONE);

                } else {
                    showDialogSubmission(R.string.submit_not_successful, R.drawable.ic_error);
                    activitySubmitBinding.ProgressBarSubmitFragment.setVisibility(View.GONE);
                    Log.d(TAG, String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                showDialogSubmission(R.string.submit_not_successful, R.drawable.ic_error);
                activitySubmitBinding.ProgressBarSubmitFragment.setVisibility(View.GONE);

            }
        });

    }

    public void showDialogForRetrieveReadingSora(String email_address,String first_name, String last_name,  String project_link) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_sure_xml);
        Button yesButton = dialog.findViewById(R.id.submit_custom_sure);

        ImageView image_view_close = dialog.findViewById(R.id.image_view_close);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProject(email_address,first_name, last_name,  project_link);
                dialog.dismiss();
            }
        });
        image_view_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void showDialogSubmission(int title, int id_image) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_submission);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textView = dialog.findViewById(R.id.text_view_submission);
        ImageView imageView = dialog.findViewById(R.id.image_view_right);
        textView.setText(title);
        imageView.setImageResource(id_image);

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void submit() {
        isNetworkAvaliable = NetworkConnection.networkConnectivity(getApplicationContext());
        String first_name = activitySubmitBinding.firstNameSubmitFragment.getText().toString().trim();
        String last_name = activitySubmitBinding.secondNameSubmitFragment.getText().toString().trim();
        String email_address = activitySubmitBinding.emailSubmitFragment.getText().toString().trim();
        String project_link = activitySubmitBinding.linkProjectSubmitFragment.getText().toString().trim();
        if (isNetworkAvaliable) {
            if (first_name.isEmpty() || last_name.isEmpty() || email_address.isEmpty() || project_link.isEmpty()) {
                Toast.makeText(SubmitActivity.this, getString(R.string.must_fill_all_fields), Toast.LENGTH_SHORT).show();
                return;
            }else{
                showDialogForRetrieveReadingSora( email_address,first_name, last_name, project_link);
            }
        } else {
            Toast.makeText(SubmitActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            return;
        }



    }

    private void setToolbar() {
        setSupportActionBar(activitySubmitBinding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //for delete label for Activity
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return false;
    }
}