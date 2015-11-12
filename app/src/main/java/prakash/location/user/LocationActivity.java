package prakash.location.user;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.om.user.project.R;

public class LocationActivity extends ActionBarActivity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView tvloc;
    EditText etnum;
    Button btnsendsms;
    //String lat;
    //String provider;
    //protected String latitude,longitude;
    //protected boolean gps_enabled,network_enabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tvloc=(TextView)findViewById(R.id.tvloc);
        etnum=(EditText)findViewById(R.id.etnum);
        btnsendsms=(Button)findViewById(R.id.btnsendsms);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }
    @Override
    public void onLocationChanged(final Location location) {
        tvloc= (TextView) findViewById(R.id.tvloc);
        tvloc.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        btnsendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr=" + location.getLatitude() + "," + location.getLongitude();
                String number = etnum.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                StringBuffer smsBody = new StringBuffer();
                smsBody.append(Uri.parse(uri));
                smsManager.sendTextMessage(number, null, smsBody.toString(), null, null);
            }
        });


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
