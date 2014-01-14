package de.spyrko.triptracker;

import java.util.ArrayList;

import de.spyrko.triptracker.adapter.TripAdapter;
import de.spyrko.triptracker.adapter.WayPointAdapter;
import de.spyrko.triptracker.model.Trip;
import de.spyrko.triptracker.model.WayPoint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TripTracker extends Activity implements OnItemClickListener
{

	private enum ContentType
	{
		TRIPLIST, TRIPSETTINGS, WAYPOINTLIST, WAYPOINTLISTSETTINGS, WAYPOINT, WAYPOINTSETTINGS, SETTINGS
	}

	private int actTrip;
	private int actWayPoint;
	private ContentType actContent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_tracker);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_tracker, menu);
		return true;
	}

	private ArrayList<Trip> initTrips()
	{
		ArrayList<Trip> trips = new ArrayList<Trip>();
		Trip trip1 = new Trip();
		Trip trip2 = new Trip();
		trip1.setH1("Trip1");
		trip1.setH2("Subtitle 1");
		trip2.setH1("Trip2");
		trip2.setH2("Subtitle 2");
		trips.add(trip1);
		trips.add(trip2);
		return trips;
	}

	private ArrayList<WayPoint> initWayPoints()
	{
		ArrayList<WayPoint> points = new ArrayList<WayPoint>();
		WayPoint point1 = new WayPoint();
		WayPoint point2 = new WayPoint();
		point1.setName("WayPoint1");
		point1.setDescription("Content 1");
		point1.setDate("Datum1");
		point2.setName("WayPoint2");
		point2.setDescription("Content 2");
		point2.setDate("Datum2");
		points.add(point1);
		points.add(point2);
		return points;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
	{
		// Toast.makeText(context, "es wurde eintrag " + pos + " gew�hlt: " +
		// getItem(pos).getH1(), Toast.LENGTH_LONG).show(); //TEST
		if (parent.getId() == R.id.tripList)
		{
			actTrip = pos;
			setContentView(R.layout.activity_way_point);
		} else if (parent.getId() == R.id.wayPointList)
		{
			actWayPoint = pos;
			setContentView(R.layout.waypoint_list_view);
		}
	}

	@Override
	public void onBackPressed()
	{
		switch (actContent)
		{
		case TRIPLIST:
			finish();
			break;
		case WAYPOINTLIST:
			setContentView(R.layout.activity_trip_tracker);
			break;
		case WAYPOINT:
			setContentView(R.layout.activity_way_point);
			break;
		default:
			finish();
			break;
		}
	}

	@Override
	public void setContentView(int layout)
	{
		super.setContentView(layout);
		switch (layout)
		{
		case R.layout.activity_trip_tracker:
			actContent = ContentType.TRIPLIST;
			ArrayList<Trip> trips = initTrips();
			if (trips.isEmpty())
			{
				findViewById(R.id.tripList).setVisibility(View.GONE);
			} else
			{
				findViewById(R.id.noTrip).setVisibility(View.GONE);
				ListView tripList = (ListView) findViewById(R.id.tripList);
				TripAdapter tripAdapter = new TripAdapter(this, trips);
				tripList.setAdapter(tripAdapter);
				tripList.setOnItemClickListener(this);
			}
			break;
		case R.layout.activity_way_point:
			actContent = ContentType.WAYPOINTLIST;
			ArrayList<WayPoint> points = initWayPoints();
			if (points.isEmpty())
			{
				findViewById(R.id.wayPointList).setVisibility(View.GONE);
			} else
			{
				findViewById(R.id.noWayPoint).setVisibility(View.GONE);
				ListView wayPointList = (ListView) findViewById(R.id.wayPointList);
				WayPointAdapter wayPointAdapter = new WayPointAdapter(this,
						points);
				wayPointList.setAdapter(wayPointAdapter);
				wayPointList.setOnItemClickListener(this);
			}
			break;
		case R.layout.waypoint_list_view:
			actContent = ContentType.WAYPOINT;
			switch (actWayPoint)
			{
			case 0:
				((TextView) findViewById(R.id.wp_content)).setText("Content 1");
				((TextView) findViewById(R.id.wp_name)).setText("Name 1");
				((TextView) findViewById(R.id.wp_date)).setText("Date 1");
				break;
			case 1:
				((TextView) findViewById(R.id.wp_content)).setText("Content 2");
				((TextView) findViewById(R.id.wp_name)).setText("Name 2");
				((TextView) findViewById(R.id.wp_date)).setText("Date 2");
				break;
			default:
				break;
			}
			break;
		default:
			actContent = null;
			break;
		}
	}
}
