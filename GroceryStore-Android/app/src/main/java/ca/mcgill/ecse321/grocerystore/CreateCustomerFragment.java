package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import ca.mcgill.ecse321.grocerystore.databinding.FragmentCreatecustomerBinding;
import cz.msebera.android.httpclient.entity.mime.Header;

public class CreateCustomerFragment extends Fragment {

    String error = null;
    private FragmentCreatecustomerBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCreatecustomerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonCreatecustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }

//    public void createCustomer(View v) {
//        error="";
//        final TextView email = (EditText) getView().findViewById(R.id.email_field);
//        final TextView username = (EditText) getView().findViewById(R.id.username_field);
//        final TextView address = (EditText) getView().findViewById(R.id.address_field);
//        final TextView phone = (EditText) getView().findViewById(R.id.phone_field);
//        final TextView password = (EditText) getView().findViewById(R.id.password_field);
//
//
//        HttpUtils.post("customers/" + email.getText() + '/' + username.getText() + '/' + password.getText() + '/' + phone.getText() + '/' + address.getText() , new RequestParams(), new JsonHttpResponseHandler() {
////            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                refreshErrorMessage(v);
//                email.setText("");
//                username.setText("");
//                password.setText("");
//                phone.setText("");
//                address.setText("");
//            }
////            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage(v);
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void refreshErrorMessage(View v) {
        // set the error message
        TextView tvError = (TextView) v.findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

}