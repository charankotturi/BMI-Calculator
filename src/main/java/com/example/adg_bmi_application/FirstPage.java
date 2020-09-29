package com.example.adg_bmi_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.adg_bmi_application.databinding.ActivityFirstPageBinding;
import com.google.android.material.slider.Slider;

public class FirstPage extends AppCompatActivity {

    ActivityFirstPageBinding binding;
    private int age = 0;
    private float weight = 0;
    private float height = 0;
    private fistPageViewModel viewModel;
    String bmiText;
    private String warnings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        binding = ActivityFirstPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(fistPageViewModel.class);

        if( viewModel.BmiValue != 0 ) {
            bmiText = "BMI: "+ viewModel.BmiValue + " for the age " + age;
            binding.txtBmi.setText(bmiText);
        }

        getSupportActionBar().hide();

        binding.slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                age = (int) slider.getValue();
            }
        });

        binding.btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    height = Float.parseFloat(String.valueOf(binding.editHeight.getText()));
                    binding.txtHeightWarning.setVisibility(View.INVISIBLE);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                    binding.txtHeightWarning.setVisibility(View.VISIBLE);
                }

                try {
                    weight = Float.parseFloat(String.valueOf(binding.editWeight.getText()));
                    binding.txtWeightWarning.setVisibility(View.INVISIBLE);
                }catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    binding.txtWeightWarning.setVisibility(View.VISIBLE);
                }
                viewModel.bmiCalculator(weight,height);

                if(viewModel.BmiValue <= 19){
                    warnings = " !UNDERWEIGHT!";
                    bmiText = "BMI: "+ viewModel.BmiValue+ " for the age: " + age + warnings;
                   // binding.txtBmi.setTextColor(0xff0000);
                    binding.txtBmi.setText(bmiText);
                }
                else if(viewModel.BmiValue >= 25) {
                    warnings = " !OVERWEIGHT!";
                    bmiText = "BMI: "+ viewModel.BmiValue+ " for the age: " + age + warnings;
                    //binding.txtBmi.setTextColor(0xff0000);
                    binding.txtBmi.setText(bmiText);
                }else {
                    warnings = " !normal!";
                    bmiText = "BMI: "+ viewModel.BmiValue+ " for the age: " + age + warnings;
                    binding.txtBmi.setText(bmiText);

                }

            }
        });

    }

}