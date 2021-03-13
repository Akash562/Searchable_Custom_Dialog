package com.ak.searchable_custom_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button popup_dialog = findViewById(R.id.open);

        popup_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupEventTest();
            }
        });
    }

    private void showPopupEventTest() {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_event, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialog);
        builder.setTitle("Select Account");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        SearchView SearchET = dialog.findViewById(R.id.SearchET);

        LinearLayout layoutLinearLayout = dialog.findViewById(R.id.layoutLinearLayout);
        ListView lv = dialog.findViewById(R.id.Contacts_list_view);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Chandigarh");
        arrayList.add("Goa");
        arrayList.add("Lakshadweep");
        arrayList.add("Delhi");
        arrayList.add("Andaman and Nicobar Islands");
        arrayList.add("Puducherry");
        arrayList.add("Himachal Pradesh");
        arrayList.add("Punjab");
        arrayList.add("Sikkim");
        arrayList.add("Daman and Diu");
        arrayList.add("Haryana");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(myAdapter);

        final AlertDialog alert = builder.create();

        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        alert.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        Window window = alert.getWindow();
        window.setGravity(Gravity.CENTER);
        alert.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);

                Toast.makeText(MainActivity.this, "click --) "+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });

        SearchET.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!(myAdapter == null)) {
                    myAdapter.getFilter().filter(s);
                }
                return false;
            }
        });

    }


}