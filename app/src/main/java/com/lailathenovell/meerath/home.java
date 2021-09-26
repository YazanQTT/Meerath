package com.lailathenovell.meerath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.lailathenovell.meerath.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

@SuppressWarnings("StatementWithEmptyBody")
public class home extends AppCompatActivity {
    Button btnCal;
    TextInputEditText editSons, editDaughters, editBrothers, editSisters;
    RadioGroup radGender, radFather, radMother, radSpouse;
    RadioButton radFemale, radNoSpouse, radMale, radFatherNo, radMotherNo;
    ImageView imgAddSon, imgRemoveSon, imgAddBro, imgAddDaughter, imgAddSis, imgRemoveDaughter, imgRemoveBro, imgRemoveSis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initValues();
        addCalClick();
        addCheckChanged();
        addPlusMinus();
    }

    private void addPlusMinus() {
        imgAddSon.setOnClickListener(v -> {
            if (Objects.requireNonNull(editSons.getText()).toString().isEmpty()) {
                editSons.setText("1");
            } else {
                if (editSons.getText().toString().equals("99"))
                    return;

                int count = Integer.parseInt(editSons.getText().toString());
                editSons.setText(String.valueOf(++count));
            }

        });
        imgRemoveSon.setOnClickListener(v -> {
            if (Objects.requireNonNull(editSons.getText()).toString().isEmpty()) {
            } else {
                if (editSons.getText().toString().equals("0"))
                    return;

                int count = Integer.parseInt(editSons.getText().toString());
                editSons.setText(String.valueOf(--count));
            }

        });

        imgAddDaughter.setOnClickListener(v -> {
            if (Objects.requireNonNull(editDaughters.getText()).toString().isEmpty()) {
                editDaughters.setText("1");
            } else {
                if (editDaughters.getText().toString().equals("99"))
                    return;

                int count = Integer.parseInt(editDaughters.getText().toString());
                editDaughters.setText(String.valueOf(++count));
            }

        });
        imgRemoveDaughter.setOnClickListener(v -> {
            if (Objects.requireNonNull(editDaughters.getText()).toString().isEmpty()) {
            } else {
                if (editDaughters.getText().toString().equals("0"))
                    return;

                int count = Integer.parseInt(editDaughters.getText().toString());
                editDaughters.setText(String.valueOf(--count));
            }

        });

        imgAddBro.setOnClickListener(v -> {
            if (Objects.requireNonNull(editBrothers.getText()).toString().isEmpty()) {
                editBrothers.setText("1");
            } else {
                if (editBrothers.getText().toString().equals("99"))
                    return;

                int count = Integer.parseInt(editBrothers.getText().toString());
                editBrothers.setText(String.valueOf(++count));
            }

        });
        imgRemoveBro.setOnClickListener(v -> {
            if (Objects.requireNonNull(editBrothers.getText()).toString().isEmpty()) {
            } else {
                if (editBrothers.getText().toString().equals("0"))
                    return;

                int count = Integer.parseInt(editBrothers.getText().toString());
                editBrothers.setText(String.valueOf(--count));
            }

        });

        imgAddSis.setOnClickListener(v -> {
            if (Objects.requireNonNull(editSisters.getText()).toString().isEmpty()) {
                editSisters.setText("1");
            } else {
                if (editSisters.getText().toString().equals("99"))
                    return;

                int count = Integer.parseInt(editSisters.getText().toString());
                editSisters.setText(String.valueOf(++count));
            }

        });
        imgRemoveSis.setOnClickListener(v -> {
            if (Objects.requireNonNull(editSisters.getText()).toString().isEmpty()) {
            } else {
                if (editSisters.getText().toString().equals("0"))
                    return;

                int count = Integer.parseInt(editSisters.getText().toString());
                editSisters.setText(String.valueOf(--count));
            }

        });
    }


    private void addCheckChanged() {
        radGender.setOnCheckedChangeListener((group, checkedId) -> radFemale.setError(null));

        radSpouse.setOnCheckedChangeListener((group, checkedId) -> radNoSpouse.setError(null));
    }

    private void addCalClick() {
        btnCal.setOnClickListener(v -> {

            boolean hasFather, hasMother, hasSpouse, isMale;
            int sonCount, daughterCount, broCount, sisCount;

            RadioButton xRadMale = findViewById(radGender.getCheckedRadioButtonId());
            isMale = xRadMale.getTag().toString().equals("1");
            RadioButton xRadSpouse = findViewById(radSpouse.getCheckedRadioButtonId());
            hasSpouse = xRadSpouse.getTag().toString().equals("1");
            RadioButton xRadFather = findViewById(radFather.getCheckedRadioButtonId());
            hasFather = xRadFather.getTag().toString().equals("1");
            RadioButton xRadMother = findViewById(radMother.getCheckedRadioButtonId());
            hasMother = xRadMother.getTag().toString().equals("1");

            if (Objects.requireNonNull(editSons.getText()).toString().isEmpty())
                sonCount = 0;
            else
                sonCount = Integer.parseInt(editSons.getText().toString());

            if (Objects.requireNonNull(editDaughters.getText()).toString().isEmpty())
                daughterCount = 0;
            else
                daughterCount = Integer.parseInt(editDaughters.getText().toString());

            if (Objects.requireNonNull(editBrothers.getText()).toString().isEmpty())
                broCount = 0;
            else
                broCount = Integer.parseInt(editBrothers.getText().toString());

            if (Objects.requireNonNull(editSisters.getText()).toString().isEmpty())
                sisCount = 0;
            else
                sisCount = Integer.parseInt(editSisters.getText().toString());


            merath.isNoType = sonCount == 0 &&
                    daughterCount == 0 &&
                    !hasFather &&
                    !hasMother &&
                    !hasSpouse &&
                    broCount == 0 &&
                    sisCount == 0;

            merath.initiate(isMale, sonCount, daughterCount, hasFather, hasMother, hasSpouse, broCount, sisCount);
            merath.calculateShares();

            Intent intent = new Intent(home.this, result.class);
            startActivity(intent);

        });
    }



    private void initValues() {
        btnCal = findViewById(R.id.btnCal);

        editSons = findViewById(R.id.editSons);
        editBrothers = findViewById(R.id.editBrothers);
        editSisters = findViewById(R.id.editSisters);
        editDaughters = findViewById(R.id.editDaughters);

        radGender = findViewById(R.id.radGender);
        radFather = findViewById(R.id.radFather);
        radMother = findViewById(R.id.radMother);
        radSpouse = findViewById(R.id.radSpouse);

        radFemale = findViewById(R.id.radFemale);
        radNoSpouse = findViewById(R.id.radNoSpouse);

        imgAddSon = findViewById(R.id.imgAddSon);
        imgRemoveSon = findViewById(R.id.imgRemoveSon);

        imgAddBro = findViewById(R.id.imgAddBro);
        imgRemoveBro = findViewById(R.id.imgRemoveBro);

        imgAddDaughter = findViewById(R.id.imgAddDaughter);
        imgRemoveDaughter = findViewById(R.id.imgRemoveDaughter);

        imgAddSis = findViewById(R.id.imgAddSis);
        imgRemoveSis = findViewById(R.id.imgRemoveSis);

        radMale = findViewById(R.id.radMale);
        radFatherNo = findViewById(R.id.radFatherNo);
        radMotherNo = findViewById(R.id.radMotherNo);

        radMale.setChecked(true);
        radFatherNo.setChecked(true);
        radMotherNo.setChecked(true);

    }
}