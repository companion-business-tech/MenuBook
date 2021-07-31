package companion.apps.menubook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import companion.apps.menubook.ItemDetailsActivity;
import companion.apps.menubook.R;
import companion.apps.menubook.databinding.MenuItemBinding;
import companion.apps.menubook.model.ItemModel;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.Holder>{

    private Context context;
    private ArrayList<ItemModel> list;

    public MenuItemAdapter(Context context, ArrayList<ItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MenuItemAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ItemModel model = list.get(position);

        holder.binding.itemImage.setImageResource(model.getItemImage());
        holder.binding.itemName.setText(model.getItemName());
        holder.binding.itemPrice.setText("Rs. "+model.getItemPrice()+".00");

        holder.binding.detailsBtn.setOnClickListener(v->{
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("itemList",list);
            intent.putExtra("position", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //filterList
    public void filterList(ArrayList<ItemModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder{
        MenuItemBinding binding;

        public Holder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
