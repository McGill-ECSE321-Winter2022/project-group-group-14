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

        });
    }

    /**
     * 
     * Method attached to update order button : to update the status of an order as an employee prepares it. 
     * @author: clarissabaciu 
     * xml file : fragment_update.xml
     */
    public void updateOrder(View v) {
        error="";   //clears the error message
        final TextView orderId = (EditText) findViewById(R.id.orderid_field);   //orderId contains the id entered into the search bar 
        final TextView status1 = (TextView) findViewById(R.id.status1);         //status1 is the sucess message shown if the order has been successfully updated
        final TextView idField = (TextView) findViewById(R.id.id_field);        //the id field is the id shown in the table upon a successful update
        final TextView typeField = (TextView) findViewById(R.id.type_field);    //typeField shows the type of the order upon a successful update
        final TextView statusField = (TextView) findViewById(R.id.status_field);    //statusField shows the status of the order upon a successful update
        status1.setText(""); //initiale all text views
        idField.setText("");
        typeField.setText("");
        statusField.setText("");

        HttpUtils.post("orders/status/update/" + orderId.getText()  , new RequestParams(), new JsonHttpResponseHandler() {  //http post request
            @Override   //override onSuccess method 
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());    
                status1.setText("Order number "+ orderId.getText() + " was updated successfully!"); //set success message for a successfull update
                refreshErrorMessage();
                idField.setText(orderId.getText());                         //sets all the text views for the order information
                try{
                    typeField.setText(response.getString("orderType"));
                    statusField.setText(""+response.getString("orderStatus"));
                } catch (JSONException e) {     //try catch in case of JSONException (required)
                    e.printStackTrace();
                }
                orderId.setText("");
            }
            @Override     //override onFailure method
            public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {

                error += errormsg;  //show error

                refreshErrorMessage();
            }
        });
    }

    /**
     * method to view the information relating to the order, should display an error message of the order id is invalid
     * @author: clarissabaciu
     * xml file : fragment_update.xml
     */
    public void viewOrder(View v) {
        error="";                //initialize error message
        final TextView orderId = (EditText) findViewById(R.id.orderid_field);       //get all text views
        final TextView idField = (TextView) findViewById(R.id.id_field);
        final TextView typeField = (TextView) findViewById(R.id.type_field);
        final TextView statusField = (TextView) findViewById(R.id.status_field);
        final TextView status1 = (TextView) findViewById(R.id.status1);
        status1.setText("");    //initialize success message
        HttpUtils.get("orders/" + orderId.getText()  , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString()); 
                refreshErrorMessage();
                idField.setText(orderId.getText());     //sets all text views to informaiton containted in the JSON object
                try{
                    typeField.setText(response.getString("orderType"));
                    statusField.setText(""+response.getString("orderStatus"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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