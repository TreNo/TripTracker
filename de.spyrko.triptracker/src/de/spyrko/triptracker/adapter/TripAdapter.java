package de.spyrko.triptracker.adapter;

import java.util.ArrayList;
import de.spyrko.triptracker.R;
import de.spyrko.triptracker.model.Trip;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TripAdapter extends BaseAdapter
{
	private final LayoutInflater inflater;
	private ArrayList<Trip> values;

	public TripAdapter(Context context, ArrayList<Trip> values)
	{
		this.values = values;
		inflater = (LayoutInflater) context.getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		return values.size();
	}

	@Override
	public Trip getItem(int pos)
	{
		return values.get(pos);
	}

	@Override
	public long getItemId(int pos)
	{
		return (long) pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent)
	{
		LinearLayout itemView = (LinearLayout) inflater.inflate(
				R.layout.trip_list_view, parent, false);
		bindView(itemView, pos);
		return itemView;
	}

	private void bindView(LinearLayout view, int pos)
	{
		Trip item = getItem(pos);
		view.setId(pos);
		((TextView) view.findViewById(R.id.h1)).setText(item.getH1());
		((TextView) view.findViewById(R.id.h2)).setText(item.getH2());
	}

}
