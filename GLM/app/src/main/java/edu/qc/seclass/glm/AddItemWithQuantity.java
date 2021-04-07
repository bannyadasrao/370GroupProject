package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemWithQuantity extends AppCompatActivity {

   // EditText inputItemName;
    TextView  inputQuantity, inputItemName;
    Button buttonIncrease, buttonDecrease, buttonCancel, buttonConfirm;
    private int amount = 1;
    String listName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_with_quantity);
        Intent x = getIntent();

        name = x.getStringExtra("itemClicked");
        listName = x.getStringExtra("listClicked");

        inputItemName = (TextView)findViewById(R.id.inputItemName);
        inputItemName.setText(name);
        inputQuantity = findViewById(R.id.inputQuantity);
        buttonIncrease = findViewById(R.id.button_increase);
        buttonDecrease = findViewById(R.id.button_decrease);
        buttonCancel = findViewById(R.id.cancel);
        buttonConfirm = findViewById(R.id.confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName =  name;
                String quantity = inputQuantity.getText().toString();

                if (stringName.length() <= 0 || quantity.length() <= 0) {
                    Toast.makeText(AddItemWithQuantity.this, "Enter name and quantity", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelpeForCheckboxAndQuantity dbHelpeForCheckboxAndQuantity = new DBHelpeForCheckboxAndQuantity(AddItemWithQuantity.this);
                    ItemsModal itemsModal = new ItemsModal(stringName, quantity, listName);
                    dbHelpeForCheckboxAndQuantity.addNameAndQuantity(itemsModal);

                    finish();
                    Toast.makeText(AddItemWithQuantity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(getIntent());

                    backToMyItemsPage();
                }
            }
        });

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelStatus();
            }
        });
    }

    private void backToMyItemsPage() {
        Intent backitempage = new Intent(AddItemWithQuantity.this, MyItems.class);
        startActivity(backitempage);
    }

    private void increase() {
        amount++;
        inputQuantity.setText(String.valueOf(amount));  //turn integer to string
    }

    private void decrease() {
        if(amount > 1) {
            amount--;
            inputQuantity.setText(String.valueOf(amount));
        }
    }

    private void cancelStatus() {
        Intent intent = new Intent(this,UserLists.class);
        startActivity(intent);
    }
}