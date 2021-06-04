package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Buttons
    Button note;
    Button camera;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note = findViewById(R.id.note_intent);
        camera = findViewById(R.id.camera_intent);
        share = findViewById(R.id.share_intent);

        // 1. Set up an explicit intent to launch an activity within our own app.
        //    Define this activity and intent in the manifest, to allow other apps to use it as well.
        note.setOnClickListener(v -> {
            Intent intent = new Intent(this, NoteActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT,"We pass some text info");
            startActivity(intent);
        });

        // 2. Set up an implicit intent to launch an activity that can set take a photo.
        //    If we can find such an activity, launch it.
        camera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                System.out.println("No camera app pressent");
            }
        });

        // 3. Set up an implicit intent to launch an activity that can send a message.
        //    Force open a chooser, allowing the user to pick an app.
        share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "share data");
            intent.putExtra(Intent.EXTRA_TEXT, "hello hello");
            startActivity(Intent.createChooser(intent, "Share message"));
        });

    }
}