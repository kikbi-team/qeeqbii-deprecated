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

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.shopping_cart.ClusterProductList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<ClusterType> cluster_types;
    private List<Float> m_opacities;
    private ClusterProductList m_cluster_product_list;
    private final View.OnClickListener mOnClickListener;

    public RecyclerViewAdapter(Activity activity, ClusterProductList cluster_product_list,
                               View.OnClickListener oncliklistener) {
        this.activity = activity;
        cluster_types = cluster_product_list.getItems();
        m_cluster_product_list = cluster_product_list;
        m_opacities = new ArrayList<>();
        mOnClickListener = oncliklistener;
        for (int i = 0; i < cluster_types.size(); ++i)
        {
            m_opacities.add(1f);
        }
    }

    public RecyclerViewAdapter(Activity activity, ClusterProductList cluster_product_list)
    {
        this.activity = activity;
        cluster_types = cluster_product_list.getItems();
        m_cluster_product_list = cluster_product_list;
        m_opacities = new ArrayList<>();
        mOnClickListener = null;
        for (int i = 0; i < cluster_types.size(); ++i)
        {
            m_opacities.add(1f);
        }
    }

    public void addItem(ClusterType cluster)
    {
        // It's added automatically !!!
        //cluster_types.add(cluster);
        m_opacities.add(1f);
        m_cluster_product_list.addItemToList(cluster);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_view, parent, false);
        if (mOnClickListener != null)
            view.setOnClickListener(mOnClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        final int position = viewHolder.getAdapterPosition();
        viewHolder.textView.setText(cluster_types.get(position).toString());
        viewHolder.imageView.setImageResource(cluster_types.get(position).getImageId());
        //viewHolder.isChecked.setChecked(products.get(position).getChecked());

        final ClusterType objIncome = m_cluster_product_list.getSpecificItemInList(position);
        //in some cases, it will prevent unwanted situations
        viewHolder.isChecked.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        viewHolder.isChecked.setChecked(m_cluster_product_list.isCheckedItem(objIncome));
        viewHolder.isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
                //set your object's last status
                m_cluster_product_list.isCheckedItem(objIncome);
                if(isChecked) {
                    m_opacities.set(position,0.5f);
                }
                else {
                    m_opacities.set(position,1f);
                }
            }
        });

        viewHolder.isChecked.setAlpha(m_opacities.get(position));
        viewHolder.textView.setAlpha(m_opacities.get(position));
        viewHolder.imageView.setAlpha(m_opacities.get(position));
    }

    @Override
    public int getItemCount() {
        return cluster_types.size();
    }

    public Activity getActivity() { return activity; }

    public List<ClusterType> getClusters() { return cluster_types; }

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