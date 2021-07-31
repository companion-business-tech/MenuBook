package companion.apps.menubook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import companion.apps.menubook.adapter.MenuItemAdapter;
import companion.apps.menubook.databinding.ActivityMenuScreenBinding;
import companion.apps.menubook.model.ItemModel;

public class MenuScreen extends AppCompatActivity {

    ActivityMenuScreenBinding binding;
    Context context = MenuScreen.this;

    ArrayList<ItemModel> list;
    MenuItemAdapter adapter;

    String tableNo;

    int orderCount = 0;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(context,R.color.colorTransparent));
        binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            tableNo = bundle.getString("tableNo");
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("order_state"));

        binding.tableNo.setText(tableNo);

        list = new ArrayList<>();
        binding.menuRecycler.setLayoutManager(new LinearLayoutManager(context));

        list.add(new ItemModel(1, "BBQ Pizza", "Pizzas", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero", 200, R.drawable.pizza_1));
        list.add(new ItemModel(2, "Olives Supreme", "Pizzas", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero", 200, R.drawable.pizza_2));
        list.add(new ItemModel(3, "Chilli Supreme", "Pizzas", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero", 200, R.drawable.pizza_3));

        adapter = new MenuItemAdapter(context,list);
        binding.menuRecycler.setAdapter(adapter);

        binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        filter("Pizzas");

        binding.pizzaCard.setOnClickListener(v->{
            binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            filter("Pizzas");
        });

        binding.burgerCard.setOnClickListener(v->{
            binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            filter("Burger");
        });

        binding.sandwichCard.setOnClickListener(v->{
            binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            filter("Sandwich");
        });

        binding.breakfastCard.setOnClickListener(v->{
            binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            filter("Breakfast");
        });

        binding.bbqCard.setOnClickListener(v->{
            binding.bbqCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            binding.bbqText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

            binding.pizzaCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.pizzaText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.burgerCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.burgerText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.sandwichCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.sandwichText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            binding.breakfastCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            binding.breakfastText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            filter("BBQ");
        });

        binding.placeOrder.setOnClickListener(v->{
            AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
            dialogBuilder.setCancelable(false);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.order_placed_dialog, null);

            MaterialButton yes = dialogView.findViewById(R.id.proceedBtn);

            yes.setOnClickListener(v1 -> {
                dialogBuilder.dismiss();
                finish();
            });

            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
        });
    }

    private void filter(String filteredText) {
        ArrayList<ItemModel> filteredList = new ArrayList<>();

        for(ItemModel item: list){
            if(item.getItemCategory().contains(filteredText)){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int price = intent.getIntExtra("price",0);

            orderCount = Integer.parseInt(binding.totalOrders.getText().toString()) + 1;
            binding.totalOrders.setText(orderCount+"");

            totalPrice = totalPrice + price;
            binding.orderPrice.setText("Rs. "+totalPrice+".00");

        }
    };
}