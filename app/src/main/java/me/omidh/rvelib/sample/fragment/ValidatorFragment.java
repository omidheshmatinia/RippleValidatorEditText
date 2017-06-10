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

public class ValidatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_validators,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RippleValidatorEditText userName= (RippleValidatorEditText) view.findViewById(R.id.editText_name);
        RippleValidatorEditText email= (RippleValidatorEditText) view.findViewById(R.id.editText_email);

        userName.addValidator(
                RVEValidatorFactory.getValidator(RVEValidatorType.MIN_LENGTH,"Minimum length is 5 words",5),
                RVEValidatorFactory.getValidator(RVEValidatorType.EMPTY,"Name can't be empty",null),
                RVEValidatorFactory.getValidator(RVEValidatorType.BEGIN,"Name should Begin with A","A"),
                RVEValidatorFactory.getValidator(RVEValidatorType.EQUAL,"Name should Be Angelina","Angelina")
        );
        email.addValidator(
                RVEValidatorFactory.getValidator(RVEValidatorType.EMAIL,"Email is not correct",null),
                RVEValidatorFactory.getValidator(RVEValidatorType.EMPTY,"Email Can't be empty",null),
                RVEValidatorFactory.getValidator(RVEValidatorType.END,"Email Should End with com","com")
        );
    }
}
