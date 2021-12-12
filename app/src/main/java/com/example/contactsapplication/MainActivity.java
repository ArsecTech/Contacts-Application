package com.example.contactsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemEventListener {
    private ContactAdapter adapter;
    private int editingItemPosition = -1;
    private EditText fullNameEt;
    private ImageView addNewContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactAdapter(this);
        recyclerView.setAdapter(adapter);

        fullNameEt = findViewById(R.id.et_main_contactFullname);
        addNewContactBtn = findViewById(R.id.iv_main_addNewContact);
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullNameEt.length() > 0) {
                    if (editingItemPosition>-1){
                        adapter.updateContact(fullNameEt.getText().toString(),editingItemPosition);
                        editingItemPosition = -1;
                        addNewContactBtn.setImageResource(R.drawable.ic_add_white_24);
                    }else {
                        adapter.addNewContact(fullNameEt.getText().toString());
                        recyclerView.scrollToPosition(0);
                    }

                    fullNameEt.setText("");

                }
            }
        });

    }

    @Override
    public void onItemClick(String fullName, int position) {
        editingItemPosition = position;
        fullNameEt.setText(fullName);
        addNewContactBtn.setImageResource(R.drawable.ic_done_white_24);
    }
}