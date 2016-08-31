package me.didik.searchviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik on 31/08/16.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<Country> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, isocode;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.country_name);
            isocode = (TextView) itemView.findViewById(R.id.country_iso);
        }
    }

    public CountryAdapter(List<Country> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_country, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Country country = list.get(position);

        holder.name.setText(country.getName());
        holder.isocode.setText(country.getIsocode());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Country getItem(int position) {
        return list.get(position);
    }

    /**
     * Filter Logic
     **/
    public void animateTo(List<Country> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);

    }

    private void applyAndAnimateRemovals(List<Country> newModels) {

        for (int i = list.size() - 1; i >= 0; i--) {
            final Country model = list.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Country> newModels) {

        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Country model = newModels.get(i);
            if (!list.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Country> newModels) {

        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Country model = newModels.get(toPosition);
            final int fromPosition = list.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Country removeItem(int position) {
        final Country model = list.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Country model) {
        list.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Country model = list.remove(fromPosition);
        list.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
