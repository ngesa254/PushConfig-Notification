package io.droidmarvin.pushconfignotification;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    private FirebaseRemoteConfig firebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading the Config Parameters or instance variables
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_params);

        firebaseRemoteConfig.setConfigSettings(
                new FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(true)
                        .build() );
    }

    private void applyConfig() {
        //Get the widget from XML layout
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);
        TextView textview = (TextView) findViewById(R.id.textView);

        //Get the values form Firebase remote configuration
        String welcomeText = firebaseRemoteConfig.getString("welcome_text");
        String welcomeTextColor = firebaseRemoteConfig.getString("welcome_text_color");
        String layoutColor = firebaseRemoteConfig.getString("bg_color");

        // Set the properties from firebase remote configuration
        // If any value not set in firebase remote configuration then it gets from default set
        layout.setBackgroundColor(Color.parseColor(layoutColor));
        textview.setText(welcomeText);
        textview.setTextColor(Color.parseColor(welcomeTextColor));

    }
}
