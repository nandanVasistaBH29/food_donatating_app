package bd.edu.rifat.foodDonationApp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bd.edu.rifat.foodDonationApp.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


private FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater);

        binding.fooddonarBtn.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_secondFragment);
        });

        binding.foodRecipentBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_thirdFragment);
        });

        binding.howto.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_fourthFragment);
        });

        binding.contact.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_sixthFragment);
        });

        return binding.getRoot();
    }
}