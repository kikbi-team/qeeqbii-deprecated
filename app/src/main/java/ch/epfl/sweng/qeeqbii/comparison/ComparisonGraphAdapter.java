package ch.epfl.sweng.qeeqbii.comparison;

/**
 * Created by sergei on 11/30/17.
 * This class allows to draw a pair of <Text, BarChart>
 * in the ListView
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.ProductComparisonActivity;

public class ComparisonGraphAdapter extends BaseAdapter {
    // contained product lines
    private List<ProductsLine> lines = null;

    // list of items
    private ArrayList<ViewHolder> holders = null;

    // context (see BaseAdapter docs)
    private Context mContext = null;

    // create empty list
    public ComparisonGraphAdapter() {
        lines = new ArrayList<ProductsLine>();
        holders = new ArrayList<ViewHolder>();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    // add a product line
    public void addLine(ProductsLine line) {
        lines.add(line.copy());
        holders.add(null);
    }

    // number of elements
    @Override
    public int getCount() {
        return lines.size();
    }

    @Override
    public Object getItem(int position) {
        return holders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // paints one element in the list
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (position >= getCount())
            return (convertView);

        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_product_comparison_graph, parent, false);
            holder = new ViewHolder();
            holder.criteria = (TextView) convertView.findViewById(R.id.criteria);
            holder.chart = (HorizontalBarChart) convertView.findViewById(R.id.chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // setting text
        holder.criteria.setText(lines.get(position).criteria);

        // adding data to the chart
        ProductComparisonActivity.setData(holder.chart, lines.get(position));

        // add current object to list of objects
        holders.set(position, holder);


        return convertView;
    }

    // this class represents a single line in the list
    private class ViewHolder {
        TextView criteria;
        HorizontalBarChart chart;
    }
}