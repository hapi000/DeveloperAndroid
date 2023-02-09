package com.example.droidcafechallenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.droidcafechallenge.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private String mOrderMessage;

    public void displayToast(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.donut.setOnClickListener(v -> {
            mOrderMessage = getString(R.string.donut_order_message);
            displayToast(mOrderMessage);

            Bundle result = new Bundle();
            result.putString("order_message", mOrderMessage);
            getActivity().getSupportFragmentManager().setFragmentResult("order", result);
        });

        binding.iceCream.setOnClickListener(v -> {
            mOrderMessage = getString(R.string.ice_cream_order_message);
            displayToast(mOrderMessage);

            Bundle result = new Bundle();
            result.putString("order_message", mOrderMessage);
            getActivity().getSupportFragmentManager().setFragmentResult("order", result);
        });

        binding.froyo.setOnClickListener(v -> {
            mOrderMessage = getString(R.string.froyo_order_message);
            displayToast(mOrderMessage);

            Bundle result = new Bundle();
            result.putString("order_message", mOrderMessage);
            getActivity().getSupportFragmentManager().setFragmentResult("order", result);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}