package com.example.crudoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nametxt,emailtxt,cnametxt;
    Functions fun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fun = new Functions(this);
    }

    public void addRecord(View view){
        nametxt =  findViewById(R.id.name);
        emailtxt =  findViewById(R.id.email);
        cnametxt =  findViewById(R.id.cname);

        String name = nametxt.getText().toString();
        String email = emailtxt.getText().toString();
        String cname = cnametxt.getText().toString();

        if (name.isEmpty() || email.isEmpty() || cname.isEmpty()){
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        }
        else {

            long retid = fun.insertdata(name, email, cname);

            if (retid > 0) {
                Toast.makeText(this, "Insertion Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Insertion Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewdata_new(View view){
        String data = fun.viewdata();
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

    public void updaterec(View view){
        EditText editId,edit_name;
        editId =  findViewById(R.id.editId);
        edit_name =  findViewById(R.id.edit_name);
        String id = editId.getText().toString();
        String name = edit_name.getText().toString();

        if (id.isEmpty() || name.isEmpty()){
            Toast.makeText(this, "Plese enter id and name", Toast.LENGTH_SHORT).show();
        }
        else{
            long retid = fun.edit(id,name);
            if (retid>=1){
                Toast.makeText(this, "Update Successful!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void delrec(View view){
        EditText del_id;
        del_id = findViewById(R.id.del_id);
        String id = del_id.getText().toString();

        if (id.isEmpty()){
            Toast.makeText(this, "Please enter id", Toast.LENGTH_SHORT).show();
        }
        else {
            int retid = fun.delete(id);
            if (retid >=1){
                Toast.makeText(this, "Record Deleted Sccessfully!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No such record Exists!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
