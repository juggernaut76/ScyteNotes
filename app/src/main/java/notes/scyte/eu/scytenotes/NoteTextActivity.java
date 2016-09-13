package notes.scyte.eu.scytenotes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;

public class NoteTextActivity extends ActionBarActivity {

    private File oldFile;
    private EditText editText_Subject;
    private EditText editText_Content;
    private Button buttonSpeichern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_text);
        buttonSpeichern = (Button) findViewById(R.id.button_speichern);
        editText_Subject=(EditText) findViewById(R.id.subject);
        editText_Content=(EditText) findViewById(R.id.content);
    }

    private void saveFile() {
        FileHelper fileHelper = new FileHelper();
        try {
            oldFile = fileHelper.saveTextFile(this
                    , editText_Subject.getText().toString()
                    , editText_Content.getText().toString()
                    , oldFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editText_Subject.setText(fileHelper.deleteFileExtension(
                oldFile.getName()));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_speichern:
                saveFile();
                break;
        }
    }

}
