package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.grocerystore.databinding.ActivityMainBinding;
//import cz.msebera.android.httpclient.entity.mime.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private String error = null;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // INSERT TO END OF THE METHOD AFTER AUTO-GENERATED CODE
        // initialize error message text view
        refreshErrorMessage();
    }

//


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_home) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void createCustomer(View v) {
        error="";
        final TextView email = (EditText) findViewById(R.id.email_field);
        final TextView username = (EditText) findViewById(R.id.username_field);
        final TextView address = (EditText) findViewById(R.id.address_field);
        final TextView phone = (EditText) findViewById(R.id.phone_field);
        final TextView password = (EditText) findViewById(R.id.password_field);
        final TextView status = (TextView) findViewById(R.id.status);

        String addressURL = address.getText().toString().replaceAll(" ","%20");
        System.out.println("Sam was here");
        HttpUtils.post("customers/" + email.getText() + '/' + username.getText() + '/' + password.getText() + '/' + phone.getText() + '/' + addressURL , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());
                status.setText(username.getText() + " was created successfully!");
                refreshErrorMessage();
                email.setText("");
                username.setText("");
                password.setText("");
                phone.setText("");
                address.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {

                    error += errormsg;

                refreshErrorMessage();
            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
        });
    }


//    public static void login(View v){
//        error="";
//        final TextView email = (EditText) findViewById(R.id.employee_email);
//        final TextView password = (EditText) findViewById(R.id.employee_password);
//        HttpUtils.get("employees/login/" + email.getText() + '/' + password.getText(), new RequestParams(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                System.out.println(response.toString());
//                refreshErrorMessage();
//
////                Intent intent = new Intent(MainActivity.this, MainFragment.class);
////                startActivity(intent);
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {
//
//                error += errormsg;
//
//                refreshErrorMessage();
//            }
//        });
//    }


//    public void addCustomer(View v) {
//        error = "";
//        final TextView tv = (TextView) findViewById(R.id.newcustomer_name);
//        HttpUtils.post("customers/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
//
////            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                refreshErrorMessage();
//                tv.setText("");
//            }
//
////            @Override
//
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//    }

    public void updateOrder(View v) {
        error="";
        final TextView orderId = (EditText) findViewById(R.id.orderid_field);
        final TextView status1 = (TextView) findViewById(R.id.status1);
        final TextView idField = (TextView) findViewById(R.id.id_field);
        final TextView typeField = (TextView) findViewById(R.id.type_field);
        final TextView statusField = (TextView) findViewById(R.id.status_field);
        status1.setText("");
        idField.setText("");
        typeField.setText("");
        statusField.setText("");

        HttpUtils.post("orders/status/update/" + orderId.getText()  , new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());
                status1.setText("Order number "+ orderId.getText() + " was updated successfully!");
                refreshErrorMessage();
                orderId.setText("");

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {

                error += errormsg;

                refreshErrorMessage();
            }
        });
    }

    public void viewOrder(View v) {
        error="";
        final TextView orderId = (EditText) findViewById(R.id.orderid_field);
        final TextView idField = (TextView) findViewById(R.id.id_field);
        final TextView typeField = (TextView) findViewById(R.id.type_field);
        final TextView statusField = (TextView) findViewById(R.id.status_field);
        final TextView status1 = (TextView) findViewById(R.id.status1);
        status1.setText("");

        HttpUtils.get("orders/" + orderId.getText()  , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());
                refreshErrorMessage();
                idField.setText(orderId.getText());
                try{
                    typeField.setText(response.getString("orderType"));
                    statusField.setText(""+response.getString("orderStatus"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                orderId.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {

                error += errormsg;

                refreshErrorMessage();
            }
        });
    }

    private Bundle getTimeFromLabel(String text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText().toString());
        args.putInt("id", v.getId());

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

}