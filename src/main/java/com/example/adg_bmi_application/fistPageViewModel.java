package com.example.adg_bmi_application;


import androidx.lifecycle.ViewModel;

public class fistPageViewModel extends ViewModel {

    float BmiValue = 0;

    public void bmiCalculator(float weight, float height) {
        BmiValue = weight / (height*height);//weight in kg and height in m.
    }

}
