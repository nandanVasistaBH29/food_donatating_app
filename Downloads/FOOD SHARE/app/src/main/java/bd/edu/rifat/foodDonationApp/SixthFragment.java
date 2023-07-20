package bd.edu.rifat.foodDonationApp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import bd.edu.rifat.foodDonationApp.databinding.FragmentSixthBinding;
import bd.edu.rifat.foodDonationApp.entities.Food;
import bd.edu.rifat.foodDonationApp.viewmodels.FoodViewModel;



public class SixthFragment extends Fragment {

    private String gender = Food.MALE;
    private List<String> foodGrup = Datagenerate.foodGroup;
    private String foodGrupName;
    private FragmentSixthBinding binding;
    private FoodViewModel viewModel;

    public SixthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSixthBinding.inflate(inflater);
        viewModel=new ViewModelProvider(requireActivity()).get(FoodViewModel.class);


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





        binding.ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = binding.name.getText().toString(); //NAME
                final String sub = binding.sub.getText().toString(); //SUBJECT
                final String phone = binding.msg.getText().toString(); //MESSAGE
                final String email = "thanujadayananda05@gmail.com";


                boolean check = validate(Name);
                if(check == true)
                {
                    String mail = "mailto:" + email +
                            "?&subject=" + Uri.encode(sub) +
                            "&body=" + Uri.encode(phone);
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse(mail));
                    try {
                        startActivity(Intent.createChooser(intent,"Send Email.."));
                    }
                    catch (Exception e)
                    {

                    }
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


    private Boolean validate(String Name) {
        if (Name.length() == 0 ) {
            binding.name.requestFocus();
            binding.name.setError("FIELD SHOULD NOT BE EMPTY");
            return false;
        } else if (!Name.matches("[A-Za-z]+")) {
            binding.name.requestFocus();
            binding.name.setError("INVALID NAME");
            return false;
        }
        else
        {
            return true;
        }
    }
}