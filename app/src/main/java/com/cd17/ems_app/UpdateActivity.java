package com.cd17.ems_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.ems_app.Fragments.UpdateFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity
{

    private EditText empid;
    private EditText fname;
    private EditText lname;
    private EditText empMail;
    private EditText phone;
    private EditText city;
    private EditText doj;
    private EditText gender;
    private EditText age;
    private EditText quali;
    private EditText domain;
    private EditText yoe;
    private EditText role;
    private EditText salary;
    private EditText leaves;
    private EditText dept;
    private EditText proj;
    private Button updateButton;

    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        /*FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        UpdateFragment m = new UpdateFragment();
        t.add(R.id.relativeLayoutUpdate , m);
        t.commit();*/

        empid = findViewById(R.id.empid);
        fname = findViewById(R.id.name_fname);
        lname = findViewById(R.id.name_lname);
        empMail = findViewById(R.id.emp_email);
        phone = findViewById(R.id.phone);
        city = findViewById(R.id.city);
        doj = findViewById(R.id.date);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        quali = findViewById(R.id.qualification);
        domain = findViewById(R.id.domain);
        yoe = findViewById(R.id.yoe);
        role = findViewById(R.id.role);
        salary = findViewById(R.id.salary);
        leaves = findViewById(R.id.leaves);
        dept = findViewById(R.id.dept);
        proj = findViewById(R.id.proj);
        updateButton = findViewById(R.id.updateButton);

        Intent intent = getIntent();
        String s = intent.getStringExtra("key");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");

        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot snap : dataSnapshot.getChildren())
                {
                    Employee emp = snap.getValue(Employee.class);
                    if(Integer.parseInt(s) == Integer.parseInt(emp.getId()) )
                    {
                        Toast.makeText(UpdateActivity.this, "EMPLOYEE FOUND", Toast.LENGTH_SHORT).show();
                        empid.setText(emp.getId());
                        fname.setText(emp.getFname());
                        lname.setText(emp.getLname());
                        empMail.setText(emp.getMail());
                        phone.setText(emp.getPhone());
                        city.setText(emp.getCity());
                        doj.setText(emp.getDoj());
                        gender.setText(emp.getGender());
                        age.setText(emp.getAge());
                        quali.setText(emp.getQualification());
                        domain.setText(emp.getDomain());
                        yoe.setText(emp.getYoe());
                        role.setText(emp.getRole());
                        salary.setText(emp.getSalary());
                        leaves.setText(emp.getLeaves());
                        dept.setText(emp.getDept());
                        proj.setText(emp.getProject());
                    }
                    /*else {
                        System.out.println("#######"+emp.getId());
                        Toast.makeText(UpdateActivity.this, "IN ELSE NOT FOUND", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txtId = empid.getText().toString();
                String txtFname = fname.getText().toString();
                String txtLname = lname.getText().toString();
                String txtEmail = empMail.getText().toString();
                String txtPhone = phone.getText().toString();
                String txtCity = city.getText().toString();
                String txtDoj = doj.getText().toString();
                String txtGender = gender.getText().toString();
                String txtAge = age.getText().toString();
                String txtQuali = quali.getText().toString();
                String txtDomain = domain.getText().toString();
                String txtYoe = yoe.getText().toString();
                String txtRole = role.getText().toString();
                String txtSalary = salary.getText().toString();
                String txtLeaves = leaves.getText().toString();
                String txtDept = dept.getText().toString();
                String txtProj = proj.getText().toString();

                if(TextUtils.isEmpty(txtId) || TextUtils.isEmpty(txtFname) || TextUtils.isEmpty(txtLname) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtCity) || TextUtils.isEmpty(txtDoj) || TextUtils.isEmpty(txtGender) || TextUtils.isEmpty(txtAge) || TextUtils.isEmpty(txtQuali) || TextUtils.isEmpty(txtDomain) || TextUtils.isEmpty(txtYoe) || TextUtils.isEmpty(txtRole) || TextUtils.isEmpty(txtSalary) || TextUtils.isEmpty(txtLeaves) || TextUtils.isEmpty(txtDept) || TextUtils.isEmpty(txtProj)){
                    Toast.makeText(UpdateActivity.this, "Fill All Credentials", Toast.LENGTH_SHORT).show();
                }else{

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id",txtId);
                    map.put("fname",txtFname);
                    map.put("lname",txtLname);
                    map.put("mail",txtEmail);
                    map.put("phone",txtPhone);
                    map.put("city",txtCity);
                    map.put("doj",txtDoj);
                    map.put("gender",txtGender);
                    map.put("age",txtAge);
                    map.put("qualification",txtQuali);
                    map.put("domain",txtDomain);
                    map.put("yoe",txtYoe);
                    map.put("role",txtRole);
                    map.put("salary",txtSalary);
                    map.put("leaves",txtLeaves);
                    map.put("dept",txtDept);
                    map.put("project",txtProj);

                    Toast.makeText(UpdateActivity.this, "Employee Info Updated", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("Employees").child("Emp" + txtId).updateChildren(map);

                    Intent intent = new Intent(UpdateActivity.this , MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        //startActivity(new Intent(UpdateActivity.this , UpdateFragment.class));
        //finish();
    }
}