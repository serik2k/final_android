Collaborators:
Serik Kudaibergenov, ID: 21B030855
Alisher Seitkali, ID: 21B030918

----------------------------------------------------------------------------------------------------------
Description
Our project is the Weather app, where you can see the forecast for different cities and choose your city
There will be 3 pages, where:
1) all the cities
2) your city
3) settings & customization

----------------------------------------------------------------------------------------------------------

The API we will be using: [http://api.weatherstack.com/current](https://api.open-meteo.com/v1/forecast?latitude=52.52,51.0222,48.8534,51.5085,47.1167,50.2797,50.4267,59.9386,25.0772,52.5244,50.088,42.87,35.6895,39.9075&longitude=13.41,71.4669,2.3488,-0.1257,51.8833,57.2072,80.2667,30.3141,55.3093,13.4105,14.4208,74.59,139.6917,116.3972&current=temperature_2m,is_day,rain,wind_speed_10m&hourly=temperature_2m&timezone=GMT&forecast_hours=12)

The information that we actually took: 
1) Temperature
2) is day or not
3) rain or not
4) wind speed
5) current weather

----------------------------------------------------------------------------------------------------------
Here is the list of the implementations: 
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.glide)
    implementation(libs.okhttp)
    implementation(libs.logging)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    annotationProcessor(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    ksp(libs.room.compiler)
    
----------------------------------------------------------------------------------------------------------

1) tThe list of cities
![photo_2024-12-20_22-17-59](https://github.com/user-attachments/assets/f2fab218-e4aa-43d0-b46c-0cbcb8762474)
2) The chosen city to follow
![photo_2024-12-20_22-18-01](https://github.com/user-attachments/assets/53ab73b6-9a71-4099-8385-da202b9ace9f)
3) The settings, where we can choose the city
![photo_2024-12-20_22-18-02](https://github.com/user-attachments/assets/9198da38-3491-4b27-8fbd-2fc207ff6182)

