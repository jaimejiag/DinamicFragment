package com.usuario.dinamicfragment.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usuario.dinamicfragment.R;


public class BFragment extends Fragment {
    public static final String TEXT_KEY = "text";
    public static final String SIZE_KEY = "size";
    public static final String TAG_FRAGMENTB = "fragmentb";

    private TextView txvResultado;


    public BFragment() {

    }

    public static BFragment newInstance(Bundle bundle) {
        BFragment bFragment = new BFragment();

        if (bundle != null) {
            bFragment.setArguments(bundle);
        }
        return bFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_b, container, false);
        setRetainInstance(true);

        if (rootView != null) {
            txvResultado = (TextView)rootView.findViewById(R.id.txv_resultado);
            Bundle bundle = getArguments();

            if (bundle != null) {
                txvResultado.setText(bundle.getString(TEXT_KEY));
                txvResultado.setTextSize(bundle.getInt(SIZE_KEY));
            }
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", txvResultado.getText().toString());
        outState.putInt("size", (int)txvResultado.getTextSize());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            txvResultado.setText(savedInstanceState.getString("text"));
            DisplayMetrics metrics = getResources().getDisplayMetrics();    //Es necesario para que el tamaño se mantenga, porque va aumentando.
            txvResultado.setTextSize(savedInstanceState.getInt("size") / metrics.density);
        }
    }
}
