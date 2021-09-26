package com.lailathenovell.meerath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lailathenovell.meerath.R;

import java.text.DecimalFormat;

public class result extends AppCompatActivity {
    TextView txtBroShare, txtSisShare, txtSpouseShare, txtFatherShare, txtMotherShare, txtSonShare, txtDaughterShare;
    TextView txtDaughterCount, txtSonCount, txtBroCount, txtSisCount;

    String broShare, sisShare, spouseShare, fatherShare, motherShare, sonShare, daughterShare;
    String sCount, dCount, broCount, sisCount;

    LinearLayout linRemaining, linSpouseShare, linBroShare, linSisShare, linMotherShare, linFatherShare, linSonShare, linDaughterShare;
    LinearLayout linKalala, linNoType;

    ImageView imgInfo;

    View viewBro, viewSis, viewSpouse, viewFather, viewMother, viewSon, viewDaughter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initiateValues();
        setValues();
        setText();
        setRemaining();
        setKalala();
        setNoType();
        hideZeroShares();
        openInfoOnClick();
    }

    private void setNoType() {
        if(merath.isNoType)
        {
            linNoType.setVisibility(View.VISIBLE);
            linKalala.setVisibility(View.GONE);
            linRemaining.setVisibility(View.GONE);
        }
    }

    private void openInfoOnClick() {
        imgInfo.setOnClickListener(v -> {
            Intent intent = new Intent(result.this, info.class);
            startActivity(intent);
        });
    }

    private void setKalala() {
        if ((merath.broCount > 0 && merath.broShare == 0) || (merath.sisShare == 0 && merath.sisCount > 0))
            linKalala.setVisibility(View.VISIBLE);
        else
            linKalala.setVisibility(View.GONE);
    }

    private void hideZeroShares() {
        if (txtSpouseShare.getText().equals("%.00")) {
            linSpouseShare.setVisibility(View.GONE);
            viewSpouse.setVisibility(View.GONE);
        }
        if (txtBroShare.getText().equals("%.00")) {
            linBroShare.setVisibility(View.GONE);
            viewBro.setVisibility(View.GONE);
        }
        if (txtSisShare.getText().equals("%.00")) {
            linSisShare.setVisibility(View.GONE);
            viewSis.setVisibility(View.GONE);
        }
        if (txtMotherShare.getText().equals("%.00")) {
            linMotherShare.setVisibility(View.GONE);
            viewMother.setVisibility(View.GONE);
        }
        if (txtFatherShare.getText().equals("%.00")) {
            linFatherShare.setVisibility(View.GONE);
            viewFather.setVisibility(View.GONE);
        }
        if (txtSonShare.getText().equals("%.00")) {
            linSonShare.setVisibility(View.GONE);
            viewSon.setVisibility(View.GONE);
        }
        if (txtDaughterShare.getText().equals("%.00")){
            linDaughterShare.setVisibility(View.GONE);
            viewDaughter.setVisibility(View.GONE);
        }


    }

    private void setRemaining() {
        if (countShares() < 0.99)
            linRemaining.setVisibility(View.VISIBLE);
        else
            linRemaining.setVisibility(View.GONE);
    }

    private double countShares() {
        double sumShares = 0;
        sumShares += (merath.broShare * merath.broCount) +
                (merath.sisShare * merath.sisCount) +
                merath.motherShare +
                merath.fatherShare +
                merath.spouseShare +
                (merath.sonShare * merath.sonCount) +
                (merath.daughterShare * merath.daughterCount);

        return sumShares;
    }

    private void setValues() {

        broShare = String.valueOf(merath.broShare);
        sisShare = String.valueOf(merath.sisShare);
        spouseShare = String.valueOf(merath.spouseShare);
        fatherShare = String.valueOf(merath.fatherShare);
        motherShare = String.valueOf(merath.motherShare);
        sonShare = String.valueOf(merath.sonShare);
        daughterShare = String.valueOf(merath.daughterShare);

        sCount = String.valueOf(merath.sonCount);
        dCount = String.valueOf(merath.daughterCount);
        broCount = String.valueOf(merath.broCount);
        sisCount = String.valueOf(merath.sisCount);
    }

    private void initiateValues() {
        txtBroShare = findViewById(R.id.txtBroShare);
        txtSisShare = findViewById(R.id.txtSisShare);
        txtSpouseShare = findViewById(R.id.txtSpouseShare);
        txtFatherShare = findViewById(R.id.txtFatherShare);
        txtMotherShare = findViewById(R.id.txtMotherShare);
        txtSonShare = findViewById(R.id.txtSonShare);
        txtDaughterShare = findViewById(R.id.txtDaughterShare);

        txtSonCount = findViewById(R.id.txtSonCount);
        txtDaughterCount = findViewById(R.id.txtDaughterCount);
        txtBroCount = findViewById(R.id.txtBroCount);
        txtSisCount = findViewById(R.id.txtSisCount);

        linRemaining = findViewById(R.id.linRemaining);
        linSpouseShare = findViewById(R.id.linSpouseShare);
        linBroShare = findViewById(R.id.linBroShare);
        linSisShare = findViewById(R.id.linSisShare);
        linMotherShare = findViewById(R.id.linMotherShare);
        linFatherShare = findViewById(R.id.linFatherShare);
        linSonShare = findViewById(R.id.linSonShare);
        linDaughterShare = findViewById(R.id.linDaughterShare);

        linKalala = findViewById(R.id.linKalala);
        linNoType = findViewById(R.id.linNoType);

        imgInfo = findViewById(R.id.imgInfo);

        viewBro = findViewById(R.id.viewBro);
        viewSis = findViewById(R.id.viewSis);
        viewSpouse = findViewById(R.id.viewSpouse);
        viewFather = findViewById(R.id.viewFather);
        viewMother = findViewById(R.id.viewMother);
        viewSon = findViewById(R.id.viewSon);
        viewDaughter = findViewById(R.id.viewDaughter);
    }

    private String setPercent(String share) {
        DecimalFormat df = new DecimalFormat("#.00");
        return "%" + df.format(Double.parseDouble(share) * 100);
    }

    private void setText() {
        txtBroShare.setText(setPercent(broShare));
        txtSisShare.setText(setPercent(sisShare));
        txtSpouseShare.setText(setPercent(spouseShare));
        txtFatherShare.setText(setPercent(fatherShare));
        txtMotherShare.setText(setPercent(motherShare));
        txtSonShare.setText(setPercent(sonShare));
        txtDaughterShare.setText(setPercent(daughterShare));

        txtSonCount.setText(String.format("%s %s", sCount, String.format("%s", txtSonCount.getText().toString())));
        txtDaughterCount.setText(String.format("%s %s", dCount, String.format("%s", txtDaughterCount.getText().toString())));
        txtBroCount.setText(String.format("%s %s", broCount, String.format("%s", txtBroCount.getText().toString())));
        txtSisCount.setText(String.format("%s %s", sisCount, String.format("%s", txtSisCount.getText().toString())));
    }
}