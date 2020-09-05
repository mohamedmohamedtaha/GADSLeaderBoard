package com.mohamedtaha.imagine.gadsleaderboard;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mohamedtaha.imagine.gadsleaderboard.model.learningleaders.LearningLeader;
import com.mohamedtaha.imagine.gadsleaderboard.ui.fragments.LearningLeadersFragment;
import com.mohamedtaha.imagine.gadsleaderboard.ui.fragments.SkillIQLeadersFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
 //   private static final int[] TAB_TITLES = new int[]{R.string.learning_leaders, R.string.skill_iq_leaders};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 1){
//            LearningLeadersFragment learningLeader = new LearningLeadersFragment();
//            return learningLeader;
//        }else {
//            SkillIQLeadersFragment skillIQLeadersFragment = new SkillIQLeadersFragment();
//            return skillIQLeadersFragment;
//        }
        switch (position){
            case 0:
                LearningLeadersFragment learningLeader = new LearningLeadersFragment();
                return learningLeader;
            default :
                SkillIQLeadersFragment skillIQLeadersFragment = new SkillIQLeadersFragment();
                return skillIQLeadersFragment;

        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.learning_leaders);
            default:
                return mContext.getString(R.string.skill_iq_leaders);

        }
       // return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}