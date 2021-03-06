package com.sliit.wishwapharmacy.PharmacyManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sliit.wishwapharmacy.R;
import com.sliit.wishwapharmacy.UserManagement.Patient.PatientLogin;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminPharmacy extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminPharmacyAdapter adminPharmacyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pharmacy);

        recyclerView = (RecyclerView)findViewById(R.id.rv);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
       // recyclerView.setLayoutManager(gridLayoutManager);

        //LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(AdminPharmacy.this, LinearLayoutManager.HORIZONTAL, false);
       // recyclerView.setLayoutManager(horizontalLayoutManagaer);


        FirebaseRecyclerOptions<AdminPharmacyModel> options =
                new FirebaseRecyclerOptions.Builder<AdminPharmacyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), AdminPharmacyModel.class)
                        .build();

        adminPharmacyAdapter = new  AdminPharmacyAdapter(options);
        recyclerView.setAdapter(adminPharmacyAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        adminPharmacyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminPharmacyAdapter.stopListening();
    }

    public void Adminaddnewproduct(View view) {
        Intent intent = new Intent(this, AdminAddNewProductActivity.class);
               startActivity(intent);
    }


    public boolean logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), PatientLogin.class));
        return true;
    }
}