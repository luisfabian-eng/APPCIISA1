package com.example.AppCiisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AppCiisa.controllers.AuthController;
import com.example.AppCiisa.controllers.BmiController;
import com.example.AppCiisa.lib.TilValidator;
import com.example.AppCiisa.models.Bmi;
import com.example.AppCiisa.models.User;
import com.example.AppCiisa.ui.BmiAdapter;
import com.example.AppCiisa.ui.DatePickerFragment;
import com.example.AppCiisa.utils.DateUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAllBmi;
    //private List<Bmi> bmiList = new ArrayList<>();
    private TextInputLayout tilStartDate, tilEndDate;
    private Button btnFilter, btnNewBmi, btnSignOut;
    private AuthController authController;
    private BmiController bmiController;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);
        bmiController = new BmiController(this);

        lvAllBmi = findViewById(R.id.activity_main_lv_bmi);
        tilStartDate = findViewById(R.id.activity_main_til_start_date);
        tilEndDate = findViewById(R.id.activity_main_til_end_date);
        btnFilter = findViewById(R.id.activity_main_btn_filter);
        btnNewBmi = findViewById(R.id.activity_main_btn_new_bmi);
        btnSignOut = findViewById(R.id.activity_main_btn_sign_out);
        tvTitle = findViewById(R.id.activity_main_txt_title);

        User user = authController.getUserSession();

        //tvTitle.setText(String.format(R.string.activity_main_txt_toast_filter, user.getFirstName()));
        tvTitle.setText(String.format("Evaluaciones de %s", user.getFirstName()));

        List<Bmi> bmiList = bmiController.getAll();
        this.setAllBmiAdapter(bmiList);

        //for (int i = 0; i <= 10; i++) {
        //    //Bmi newBmi = new Bmi(String.format("Date %d", i),String.format("Weight %d",i),String.format("Calculated BMI: %d",i));
        //    String randomDate = Integer.toString(new Random().nextInt(29) + 1) + "/" + Integer.toString(new Random().nextInt(11) + 1) + "/2021";
        //    Date randomDateFormatted = null;
        //    try {
        //        randomDateFormatted = new SimpleDateFormat("dd/MM/yyyy").parse(randomDate);
        //    } catch (ParseException e) {
        //        e.printStackTrace();
        //    }
        //    //String randomWeight = Integer.toString(new Random().nextInt(6) + 79);
        //    double randomWeight = new Random().nextInt(6) + 79;
        //    //String calculatedBmi = Double.toString(Double.parseDouble(randomWeight) / 1.72);
        //    //String calculatedBmi = Double.toString(randomWeight / 1.72);
        //    double calculatedBmi = randomWeight / 1.72;
        //    //Bmi newBmi = new Bmi(getResources().getString(R.string.activity_main_txt_item_date) + " " + randomDate,
        //    //        getResources().getString(R.string.activity_main_txt_item_weight) + " " + randomWeight + getResources().getString(R.string.activity_main_txt_item_weight_kg),
        //    //       getResources().getString(R.string.activity_main_txt_item_bmi) + " " + calculatedBmi);
        //    Bmi newBmi = new Bmi(randomDateFormatted, randomWeight, calculatedBmi, 5);
        //    newBmi.setId(i);
        //    bmiList.add(newBmi);
        //}

        BmiAdapter adapter = new BmiAdapter(this, bmiList);

        lvAllBmi.setAdapter(adapter);

        lvAllBmi.setOnItemClickListener(((adapterView, view, index, id) -> {
            Bmi bmi = bmiList.get(index);
            Intent i = new Intent(view.getContext(), BmiDetailActivity.class);
            i.putExtra("bmi",bmi);
            view.getContext().startActivity(i);
        }));

        tilStartDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilStartDate, new Date());
        });

        tilEndDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilEndDate, new Date());
        });

        btnFilter.setOnClickListener(view -> {
            String fromStr = tilStartDate.getEditText().getText().toString();
            String toStr = tilEndDate.getEditText().getText().toString();

            boolean validFrom = new TilValidator(tilStartDate)
                    .required()
                    .date()
                    .dateBefore(DateUtils.unsafeParse(toStr))
                    .isValid();
            boolean validTo = new TilValidator(tilEndDate)
                    .required()
                    .date()
                    .dateAfter(DateUtils.unsafeParse(fromStr))
                    .isValid();

            if (validFrom && validTo) {
                Date from = DateUtils.unsafeParse(fromStr);
                Date to = DateUtils.unsafeParse(toStr);

                List<Bmi> bmiRangeList = bmiController.getRange(from, to);
                this.setAllBmiAdapter(bmiRangeList);
            }

            Toast.makeText(view.getContext(), R.string.activity_main_txt_toast_filter, Toast.LENGTH_SHORT).show();
        });

        btnNewBmi.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), BmiCreateActivity.class);
            startActivity(i);
            finish();
        });

        btnSignOut.setOnClickListener(view -> {
            authController.logout();
            //Intent i = new Intent(view.getContext(), LoginActivity.class);
            //startActivity(i);
            //finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Bmi> bmiList = bmiController.getAll();
        this.setAllBmiAdapter(bmiList);
    }

    private void setAllBmiAdapter(List<Bmi> bmiList) {
        BmiAdapter adapter = new BmiAdapter(this, bmiList);
        lvAllBmi.setAdapter(adapter);

        lvAllBmi.setOnItemClickListener(((adapterView, view, index, id) -> {
            Bmi bmi = bmiList.get(index);

            Intent i = new Intent(view.getContext(), BmiDetailActivity.class);
            i.putExtra("bmi", bmi);
            view.getContext().startActivity(i);
        }));
    }
}