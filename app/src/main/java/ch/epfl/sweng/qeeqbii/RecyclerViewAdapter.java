package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 31.10.17.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCart;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<Product> products;

    public RecyclerViewAdapter(Activity activity, List<Product> products) {
        this.activity = activity;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(products.get(position).getName());
        viewHolder.imageView.setImageResource(products.get(position).getImageId());
        //viewHolder.isChecked.setChecked(products.get(position).getChecked());

        final Product objIncome = ShoppingCart.m_items.get(position);
        //in some cases, it will prevent unwanted situations
        viewHolder.isChecked.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        viewHolder.isChecked.setChecked(objIncome.isChecked());
        viewHolder.isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
                //set your object's last status
                objIncome.setChecked(isChecked);
                if(isChecked) {
                    objIncome.setOpacity(0.5f);
                }
                else {
                    objIncome.setOpacity(1f);
                }
            }
        });

        viewHolder.isChecked.setAlpha(objIncome.getOpacity());
        viewHolder.textView.setAlpha(objIncome.getOpacity());
        viewHolder.imageView.setAlpha(objIncome.getOpacity());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public Activity getActivity() { return activity; }

    public List<Product> getProducts() { return products; }

    //NESTED CLASS
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private CheckBox isChecked;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
            imageView = (ImageView) view.findViewById(R.id.shoppingListImage);
            isChecked = (CheckBox) view.findViewById(R.id.shoppingCheckbox);

            isChecked.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (((CheckBox)v).isChecked()) {
                        v.setAlpha(0.50f);              //CHANGES THE OPACITY OF THE VIEW
                        imageView.setAlpha(0.50f);
                        textView.setAlpha(0.50f);
                    }
                    else {
                        v.setAlpha(1f);              //CHANGES THE OPACITY OF THE VIEW
                        imageView.setAlpha(1f);
                        textView.setAlpha(1f);
                    }
                }
            });
        }
    }
}