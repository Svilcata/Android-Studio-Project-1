package com.svilcata.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Svilcata on 11-Apr-16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
