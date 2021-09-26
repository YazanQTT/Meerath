package com.lailathenovell.meerath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lailathenovell.meerath.R;

public class info extends AppCompatActivity {
    ImageView imgFacebook, imgYoutube, imgInstagram, imgWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initiate();
        addOnClick();
    }

    private void addOnClick() {
        imgFacebook.setOnClickListener(v -> {
            String facebookId = getString(R.string.lailaFacebookID);
            String urlPage = getString(R.string.lailaFacebookURL);

            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
            }catch (Exception e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
            }
        });

        imgYoutube.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW,   Uri.parse(getString(R.string.lailaYoutubeURL)))));

        imgInstagram.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW,   Uri.parse(getString(R.string.lailaInstagramURL)))));

        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,   Uri.parse(getString(R.string.websiteURL))));
            }
        });
    }

    private void initiate() {
        imgFacebook = findViewById(R.id.imgFacebook);
        imgYoutube = findViewById(R.id.imgYoutube);
        imgInstagram = findViewById(R.id.imgInstagram);
        imgWebsite = findViewById(R.id.imgWebsite);
    }
}