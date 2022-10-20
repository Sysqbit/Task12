package com.example.postapiimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.CertPathBuilder;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.aa);





        AndroidNetworking.initialize(this);
        AndroidNetworking.post("https://reqres.in/api/register")
                .addBodyParameter("email","eve.holt@reqres.in")
                .addBodyParameter("password","pistol")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("res", jsonObject.toString());
                        try {
                            /*JSONObject objid = jsonObject.getJSONObject("createdAt");*/
                            String name = jsonObject.getString("token");
                            System.out.print(name);
                            txt.setText(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();

                    }
                });
    }


}