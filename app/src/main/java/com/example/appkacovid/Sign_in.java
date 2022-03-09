package com.example.appkacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_in extends AppCompatActivity {


    private EditText editLogin,editHaslo;

    UserDBAdapter userDBAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editLogin = findViewById(R.id.editLogin);
        editHaslo = findViewById(R.id.editHaslo);
        button = findViewById(R.id.button2);





        userDBAdapter = new UserDBAdapter(Sign_in.this,"user.db",null,1);
        User user = new User("admin","admin");
        userDBAdapter.addUser(user);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDBAdapter.getData(editLogin.getText().toString(), editHaslo.getText().toString())){
                    Intent intent = new Intent(Sign_in.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Sign_in.this, "Podaj prawidłowe dane", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (editLogin.getText().toString().equals(user.getLogin()) && editHaslo.getText().toString().equals(user.getHaslo())){
                    Intent intent = new Intent(Sign_in.this,MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });


    }
}