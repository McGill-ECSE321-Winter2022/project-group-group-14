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

import org.json.JSONObject;
import org.w3c.dom.Text;

import ca.mcgill.ecse321.grocerystore.databinding.FragmentLoginBinding;
import cz.msebera.android.httpclient.Header;

public class LoginFragment extends Fragment{

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView email = (EditText) getView().findViewById(R.id.employee_email);
                final TextView password = (EditText) getView().findViewById(R.id.employee_password);
                final TextView status = (TextView) getView().findViewById(R.id.status);
                HttpUtils.get("employees/login/" + email.getText() + '/' + password.getText(), new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println(response.toString());
                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_LoginFragment_to_mainFragment);
//                Intent intent = new Intent(MainActivity.this, MainFragment.class);
//                startActivity(intent);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String errormsg, Throwable throwable) {
                        status.setText(errormsg);
                        System.out.println(errormsg);
                    }
                });
//                MainActivity.login(view);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
