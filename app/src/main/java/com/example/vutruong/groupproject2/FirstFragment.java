package com.example.vutruong.groupproject2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener, HttpGetDemo.OnDataSendToActivity {

    private Button btnCall, btnAbout;
    private TextView textDisplay;
    private String textContent = "";

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            textContent = savedInstanceState.getString("textDisplay");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
    }

    public void setView(View view) {
        textDisplay = (TextView) view.findViewById(R.id.text);
        btnAbout = (Button) view.findViewById(R.id.btnAbout);
        btnCall = (Button) view.findViewById(R.id.btnCall);

        textDisplay.setText(textContent);

        btnCall.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAbout:
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new AboutFragment()).addToBackStack(null).commit();
                break;
            case R.id.btnCall:
                HttpGetDemo async = new HttpGetDemo();
                async.setData(this);
                async.execute("https://api.myjson.com/bins/44fql");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textDisplay", textContent);
    }

    @Override
    public void sendData(String str) {
        textContent = str;
        textDisplay.setText(textContent);
    }
}
