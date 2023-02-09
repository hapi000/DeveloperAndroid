package com.example.droidcafeoptionsup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.droidcafeoptionsup.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

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
            String message = getString(R.string.donut_order_message);

            Bundle result = new Bundle();
            result.putString("order", message);

            getActivity().getSupportFragmentManager().setFragmentResult("desert_order", result);

            displayToast(message);
        });

        binding.iceCream.setOnClickListener(v -> {
            String message = getString(R.string.ice_cream_order_message);

            Bundle result = new Bundle();
            result.putString("order", message);

            getActivity().getSupportFragmentManager().setFragmentResult("desert_order", result);

            displayToast(getString(R.string.ice_cream_order_message));
        });

        binding.froyo.setOnClickListener(v -> {
            String message = getString(R.string.froyo_order_message);

            Bundle result = new Bundle();
            result.putString("order", message);

            getActivity().getSupportFragmentManager().setFragmentResult("desert_order", result);

            displayToast(getString(R.string.froyo_order_message));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}