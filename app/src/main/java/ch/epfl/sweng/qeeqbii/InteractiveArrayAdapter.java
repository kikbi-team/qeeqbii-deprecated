package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 31.10.17.
 */
/*
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class InteractiveArrayAdapter extends ArrayAdapter<ModelForShoppingCart> {

        private final List<ModelForShoppingCart> list;
        private final Activity context;

        public InteractiveArrayAdapter(Activity context, List<ModelForShoppingCart> list) {
            super(context, R.layout.content_shopping_cart, list);
            this.context = context;
            this.list = list;
        }

        static class ViewHolder {
            protected TextView text;
            protected CheckBox checkbox;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.content_shopping_cart, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.text = (TextView) view.findViewById(R.id.label);
                viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
                viewHolder.checkbox
                        .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton buttonView,
                                                         boolean isChecked) {
                                ModelForShoppingCart element = (ModelForShoppingCart) viewHolder.checkbox
                                        .getTag();
                                element.setSelected(buttonView.isChecked());

                            }
                        });
                view.setTag(viewHolder);
                viewHolder.checkbox.setTag(list.get(position));
            } else {
                view = convertView;
                ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.text.setText(list.get(position).getName());
            holder.checkbox.setChecked(list.get(position).isSelected());
            return view;
        }
    }*/
