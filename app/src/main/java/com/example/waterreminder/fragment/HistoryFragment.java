package com.example.waterreminder.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.waterreminder.R;

public class HistoryFragment extends Fragment {
    TextView textView;

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        textView = view.findViewById(R.id.tv_numberWater);

        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES , MODE_PRIVATE);
        String restoredText = prefs.getString("TAG_NAME", null);
        textView.setText(restoredText);

        return view;
    }
}
