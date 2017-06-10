package me.omidh.rvelib.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.omidh.ripplevalidatoredittext.RVEValidatorFactory;
import me.omidh.ripplevalidatoredittext.RVEValidatorType;
import me.omidh.ripplevalidatoredittext.RippleValidatorEditText;
import me.omidh.rvelib.sample.R;

public class DifferentShapeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_different_shapes,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
