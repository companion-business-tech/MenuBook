package companion.apps.menubook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.WindowManager;

import java.util.ArrayList;

import companion.apps.menubook.databinding.ActivityItemDetailsBinding;
import companion.apps.menubook.model.ItemModel;

public class ItemDetailsActivity extends AppCompatActivity {

    ActivityItemDetailsBinding binding;
    Context context = ItemDetailsActivity.this;

    ItemModel model;
    int countItem = 1;
    int totalPrice = 0;
    int extrasPrice = 0;
    int totalItems = 1;
    int sizePrice = 0;

    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(context,R.color.colorTransparent));
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            ArrayList<ItemModel> list = (ArrayList<ItemModel>) bundle.getSerializable("itemList");
            int position = bundle.getInt("position");
            model = list.get(position);
        }

        binding.itemImage.setImageResource(model.getItemImage());
        binding.itemName.setText(model.getItemName());
        binding.itemPrice.setText("Rs. "+model.getItemPrice()+".00");
        binding.itemCount.setText("1");

        sizePrice = model.getItemPrice();
        totalItems = Integer.parseInt(binding.itemCount.getText().toString());
        totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
        binding.totalPrice.setText("Rs. "+totalPrice+".00");

        binding.addItem.setOnClickListener(v->{
            countItem++;
            binding.itemCount.setText(countItem+"");

            totalItems = Integer.parseInt(binding.itemCount.getText().toString());
            totalPrice = (totalPrice + sizePrice + extrasPrice);
            binding.totalPrice.setText("Rs. "+totalPrice+".00");
        });

        binding.removeItem.setOnClickListener(v->{
            if(countItem>0) {
                countItem--;
                binding.itemCount.setText(countItem + "");

                if(totalPrice>0) {
                    totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                    totalPrice = (totalPrice - (sizePrice + extrasPrice));
                    binding.totalPrice.setText("Rs. "+totalPrice+".00");
                }
            }
        });

        binding.itemDescription.setText(model.getItemDescription());

        binding.mediumCard.setOnClickListener(v->{
            sizePrice = 0;
            sizePrice = sizePrice + 400;

            binding.mediumCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            binding.mediumText.setTextColor(ContextCompat.getColor(context,R.color.colorWhite));

            binding.largeCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.largeText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            binding.familyCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.familyText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            totalPrice = 0;
            totalItems = Integer.parseInt(binding.itemCount.getText().toString());
            totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
            binding.totalPrice.setText("Rs. "+totalPrice+".00");
        });

        binding.largeCard.setOnClickListener(v->{
            sizePrice = 0;
            sizePrice = sizePrice + 800;

            binding.mediumCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.mediumText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            binding.largeCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            binding.largeText.setTextColor(ContextCompat.getColor(context,R.color.colorWhite));

            binding.familyCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.familyText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            totalPrice = 0;
            totalItems = Integer.parseInt(binding.itemCount.getText().toString());
            totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
            binding.totalPrice.setText("Rs. "+totalPrice+".00");
        });

        binding.familyCard.setOnClickListener(v->{
            sizePrice = 0;
            sizePrice = sizePrice + 1200;

            binding.mediumCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.mediumText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            binding.largeCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            binding.largeText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

            binding.familyCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            binding.familyText.setTextColor(ContextCompat.getColor(context,R.color.colorWhite));

            totalPrice = 0;
            totalItems = Integer.parseInt(binding.itemCount.getText().toString());
            totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
            binding.totalPrice.setText("Rs. "+totalPrice+".00");
        });

        binding.extra1Check.setOnClickListener(v->{
            if(!check1) {
                binding.extra1Check.setImageResource(R.drawable.ic_checklist);
                extrasPrice = extrasPrice + 20;
                check1 = true;

                totalPrice = 0;
                totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
                binding.totalPrice.setText("Rs. "+totalPrice+".00");
            }
            else{
                binding.extra1Check.setImageResource(R.drawable.black_circle);
                check1 = false;
                if(extrasPrice>0) {
                    extrasPrice = extrasPrice - 20;

                    totalPrice = 0;
                    totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                    totalPrice = ((totalPrice + sizePrice) * totalItems) - extrasPrice;
                    binding.totalPrice.setText("Rs. "+totalPrice+".00");
                }
            }
        });

        binding.extra2Check.setOnClickListener(v->{
            if(!check2) {
                binding.extra2Check.setImageResource(R.drawable.ic_checklist);
                extrasPrice = extrasPrice + 25;
                check2 = true;

                totalPrice = 0;
                totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
                binding.totalPrice.setText("Rs. "+totalPrice+".00");
            }
            else{
                binding.extra2Check.setImageResource(R.drawable.black_circle);
                check2 = false;
                if(extrasPrice>0){
                    extrasPrice = extrasPrice - 25;

                    totalPrice = 0;
                    totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                    totalPrice = ((totalPrice + sizePrice) * totalItems) - extrasPrice;
                    binding.totalPrice.setText("Rs. "+totalPrice+".00");
                }
            }
        });

        binding.extra3Check.setOnClickListener(v->{
            if(!check3) {
                binding.extra3Check.setImageResource(R.drawable.ic_checklist);
                extrasPrice = extrasPrice + 15;
                check3 = true;

                totalPrice = 0;
                totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                totalPrice = (totalPrice + sizePrice + extrasPrice) * totalItems;
                binding.totalPrice.setText("Rs. "+totalPrice+".00");
            }
            else{
                binding.extra3Check.setImageResource(R.drawable.black_circle);
                check3 = false;
                if(extrasPrice>0) {
                    extrasPrice = extrasPrice - 15;

                    totalPrice = 0;
                    totalItems = Integer.parseInt(binding.itemCount.getText().toString());
                    totalPrice = ((totalPrice + sizePrice) * totalItems) - extrasPrice;
                    binding.totalPrice.setText("Rs. "+totalPrice+".00");
                }
            }
        });

        binding.placeOrderBtn.setOnClickListener(v->{
            Intent sender = new Intent("order_state");
            sender.putExtra("price", totalPrice);
            LocalBroadcastManager.getInstance(context).sendBroadcast(sender);
            finish();
        });

    }
}