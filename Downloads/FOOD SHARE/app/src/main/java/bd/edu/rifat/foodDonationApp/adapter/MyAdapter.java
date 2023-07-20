package bd.edu.rifat.foodDonationApp.adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bd.edu.rifat.foodDonationApp.databinding.RowFoodBinding;
import bd.edu.rifat.foodDonationApp.entities.Food;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FoodViewHolder> {


    private List<Food> foodList =new ArrayList<>();

    public MyAdapter(Fragment fragment) {

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowFoodBinding binding = RowFoodBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


    public void  submitNewList(List<Food> foodList){
        this.foodList = foodList;
        notifyDataSetChanged();
    }
    class FoodViewHolder extends RecyclerView.ViewHolder {
            private RowFoodBinding binding;


            public void bind(Food food){
                binding.setFood(food);
            }
            public FoodViewHolder(@NonNull RowFoodBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

                binding.callBtn.setOnClickListener(v -> {
                    final int pos= getAdapterPosition();
                    Toast.makeText(v.getContext(),foodList.get(pos).getPhone(),Toast.LENGTH_LONG).show();
                    final Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+foodList.get(pos).getPhone()));
                    Intent chooser=Intent.createChooser(intent,"Which app do you want open");
                    try {
                        v.getContext().startActivity(chooser);
                    }catch (ActivityNotFoundException exception){
                        Toast.makeText(v.getContext(),exception.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });






            }

    }
}
