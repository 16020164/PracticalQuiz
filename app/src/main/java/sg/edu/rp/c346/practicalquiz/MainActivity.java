package sg.edu.rp.c346.practicalquiz;

import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvAge;
    EditText etName;
    EditText etAge;
    Spinner spn;
    Button btnSave;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        tvAge = findViewById(R.id.textViewAge);
        etAge = findViewById(R.id.editTextAge);
        etName = findViewById(R.id.editTextName);
        spn = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.buttonSave);

        etAge.requestFocus();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor prefEdit = prefs.edit();

                pos = spn.getSelectedItemPosition();
                prefEdit.putInt("spinner",pos);

                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG);
                toast.show();


                prefEdit.clear().commit();
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Name", etName.getText().toString() );
        prefEdit.putInt("Age", Integer.parseInt(etAge.getText().toString()));

        //Step 1e: call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String Name = prefs.getString("Name","");
        int Age = prefs.getInt("Age", 0);

        etName.setText(Name);
        int spinner = prefs.getInt("spinner",0);
        if(Age == 0)
            etAge.setText("");
        else
            etAge.setText(Age + "");

        spn.setSelection(spinner);

    }

}
