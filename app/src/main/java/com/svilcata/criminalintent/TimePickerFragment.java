package com.svilcata.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Svilcata on 21-Apr-16.
 */
public class TimePickerFragment extends DialogFragment {
    private static final String ARG_TIME = "date";
    public static final String EXTRA_TIME = "time";
    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.time_picker, null);
        final Date time = (Date) getArguments().getSerializable(ARG_TIME);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("Time of crime:").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date date = (Date) getArguments().getSerializable(ARG_TIME);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int hour = mTimePicker.getCurrentHour();
                int minute = mTimePicker.getCurrentMinute();
                date.setHours(hour);
                date.setMinutes(minute);

                sendResultTime(Activity.RESULT_OK, time);
            }
        }).show();
    }

    private void sendResultTime(int resultCode, Date time) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
