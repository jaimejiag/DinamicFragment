package com.usuario.dinamicfragment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.usuario.dinamicfragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends Fragment {
    WebView mWeb;

    public CFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_c, container, false);

        mWeb = (WebView) rootView.findViewById(R.id.web);
        mWeb.loadData(getResources().getString(R.string.aboutus), "text/html", Xml.Encoding.UTF_8.toString());

        return rootView;
    }
}
