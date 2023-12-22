package com.example.emed_projecte_android.ui.theme;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emed_projecte_android.R;

public class MessageActivity extends AppCompatActivity {
    private EditText contentEditText, recipientIdEditText, viewRecipientIdEditText;
    private Spinner recipientTypeSpinner, viewRecipientTypeSpinner;
    private Button sendMessageButton, viewMessagesButton;
    private LinearLayout messagesContainer;
    private ProgressBar progressBar;
    private TextView sendMessagesHeader, viewMessagesHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        contentEditText = findViewById(R.id.content);
        recipientTypeSpinner = findViewById(R.id.recipientType);
        recipientIdEditText = findViewById(R.id.recipientId);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        viewMessagesButton = findViewById(R.id.viewMessagesButton);
        messagesContainer = findViewById(R.id.messagesContainer);
        progressBar = findViewById(R.id.progressBar);
        sendMessagesHeader = findViewById(R.id.sendMessagesHeader);
        viewMessagesHeader = findViewById(R.id.viewMessagesHeader);
        viewRecipientTypeSpinner = findViewById(R.id.viewRecipientType);
        viewRecipientIdEditText = findViewById(R.id.viewRecipientId);

        // En el res/values/strings.xml esta las 4 opcions del recipient_types :
        // User, DoctorI , DoctorSS, DoctorM
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.recipient_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recipientTypeSpinner.setAdapter(adapter);
        viewRecipientTypeSpinner.setAdapter(adapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        viewMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMessages();
            }
        });
    }

    private void sendMessage() {
        String content = contentEditText.getText().toString();
        String recipientType = recipientTypeSpinner.getSelectedItem().toString();
        String recipientId = recipientIdEditText.getText().toString();

        Toast.makeText(this, "Sending message: " + content, Toast.LENGTH_SHORT).show();
    }

    private void viewMessages() {
        String recipientType = viewRecipientTypeSpinner.getSelectedItem().toString();
        String recipientId = viewRecipientIdEditText.getText().toString();

        Toast.makeText(this, "Viewing messages for: " + recipientId, Toast.LENGTH_SHORT).show();
    }
}
