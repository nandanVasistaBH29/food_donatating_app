package bd.edu.rifat.foodDonationApp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

import bd.edu.rifat.foodDonationApp.data.Datagenerate;
import bd.edu.rifat.foodDonationApp.databinding.FragmentSecondBinding;
import bd.edu.rifat.foodDonationApp.entities.Food;
import bd.edu.rifat.foodDonationApp.viewmodels.FoodViewModel;



public class SecondFragment extends Fragment {

    private String gender = Food.MALE;
    private List<String> foodGrup = Datagenerate.foodGroup;
    private String foodGrupName;
    private FragmentSecondBinding binding;
    private FoodViewModel viewModel;
    public static final  String REQUEST_KEY="DATE_TIME_KEY";
    public static final  String DATE_KEY="DATE_KEY";
    public static final  String TIME_KEY="TIME_KEY";

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater);
        viewModel=new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

        initAdapter();

        //SpinnerMethod
        binding.foodSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                foodGrupName = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getActivity(), courseTitle, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //RadioGroup Method
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                final RadioButton rb = radioGroup.findViewById(i);
                gender = rb.getText().toString();

            }
        });





        getChildFragmentManager().setFragmentResultListener(REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                if (result.containsKey(DATE_KEY)){
                    final String date=result.getString(DATE_KEY);
                    binding.dateBtn.setText(date);
                }

            }
        });





        binding.dateBtn.setOnClickListener(view -> {
            new DatePickerDialogFragment().show(getChildFragmentManager(),null);

        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = binding.nameEt.getText().toString();
                final double age = Double.parseDouble(binding.ageEt.getText().toString());
                final String phone = binding.phoneEt.getText().toString();
                final Food student = new Food(Name,age,phone,gender,foodGrupName);


                boolean check = validate(Name, age, phone);
                if(check == true)
                {
                    Toast.makeText(getActivity(), "DONATION SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                    viewModel.addFoodDonar(student);
                    Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_fifthFragment);
                }
                else
                {
                    Toast.makeText(getActivity(), "Invalid Data", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getActivity(), studentName, Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    private void initAdapter() {

        final ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,foodGrup);

        binding.foodSp.setAdapter(adapter);
    }
    private Boolean validate(String Name, double age, String phone) {
        if (Name.length() == 0) {
            binding.nameEt.requestFocus();
            binding.nameEt.setError("FIELD SHOULD NOT BE EMPTY");
            return false;
        } else if (!Name.matches("[A-Za-z]+")) {
            binding.nameEt.requestFocus();
            binding.nameEt.setError("INVALID NAME");
            return false;
        } else if (age < 5 || age > 2000) {
            binding.ageEt.requestFocus();
            binding.ageEt.setError("MAX: 2000 PLATES AND MIN: 5 PLATES CAN BE DONATED");
            return false;
        } else if (!phone.matches("[6-9]{1}[0-9]{9}")) {
            binding.phoneEt.requestFocus();
            binding.phoneEt.setError("INVALID PHONE NUMBER");
            return false;
        }
        else
        {
            return true;
        }
    }
}