package bd.edu.rifat.foodDonationApp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog = new DatePickerDialog(getContext(), this, year, month, day);
        Field mDatePickerField;
        try {
            mDatePickerField = dialog.getClass().getDeclaredField("mDatePicker");
            mDatePickerField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        return dialog;


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        final  Calendar calendar=Calendar.getInstance();

        calendar.set(year, month, dayOfMonth);





        final String selectedDate=sdf.format(calendar.getTime());
        Toast.makeText(getActivity(), selectedDate, Toast.LENGTH_SHORT).show();

        Bundle bundle=new Bundle();
        bundle.putString(SecondFragment.DATE_KEY,selectedDate);

        getParentFragmentManager().setFragmentResult(SecondFragment.REQUEST_KEY,bundle);
    }
}


