package notes.scyte.eu.scytenotes;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ImageButton imgButtonTextNote;
    private ImageButton imgButtonScetchNote;
    private ImageButton imgButtonVoiceNote;
    private ListView noteListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_main);

        imgButtonTextNote = (ImageButton) findViewById(R.id.mainBottomActionBar_imageButton_textNote);
        imgButtonScetchNote = (ImageButton) findViewById(R.id.mainBottomActionBar_imageButton_scetchNote);
        imgButtonVoiceNote = (ImageButton) findViewById(R.id.mainBottomActionBar_imageButton_voiceNote);

        noteListView = (ListView) findViewById(R.id.main_listView);

        ArrayList<Note> list = new ArrayList<Note>();

        Note note1 = new Note();
        note1.setSubject("Subject1");
        Note note2 = new Note();
        note2.setSubject("Subject2");
        Note note3 = new Note();
        note3.setSubject("Note3");

        list.add(note1);
        list.add(note2);
        list.add(note3);

        NoteListAdapter listAdapter = new NoteListAdapter(this, 0, list);
        noteListView.setAdapter(listAdapter);
        noteListView.setOnItemClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainBottomActionBar_imageButton_textNote:
                imgButtonTextNote.setBackgroundResource(R.drawable.image_button_checked);
                imgButtonVoiceNote.setBackgroundResource(R.drawable.image_button_default);
                imgButtonScetchNote.setBackgroundResource(R.drawable.image_button_default);
                break;
            case R.id.mainBottomActionBar_imageButton_voiceNote:
                imgButtonTextNote.setBackgroundResource(R.drawable.image_button_default);
                imgButtonVoiceNote.setBackgroundResource(R.drawable.image_button_checked);
                imgButtonScetchNote.setBackgroundResource(R.drawable.image_button_default);
                startActivity(new Intent(this, NoteVoiceActivity.class));
                break;
            case R.id.mainBottomActionBar_imageButton_scetchNote:
                imgButtonTextNote.setBackgroundResource(R.drawable.image_button_default);
                imgButtonVoiceNote.setBackgroundResource(R.drawable.image_button_default);
                imgButtonScetchNote.setBackgroundResource(R.drawable.image_button_checked);
                startActivity(new Intent(this, NoteSketchActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_add:
                startActivity(new Intent(this, NoteTextActivity.class));
                return true;
            case R.id.mainBottomActionBar_imageButton_scetchNote:
                return true;
            case R.id.mainBottomActionBar_imageButton_voiceNote:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = new Note();
        note = (Note) noteListView.getItemAtPosition(position);
        Log.d("haha","onItemClick: " + note.getSubject() + "|"+ String.valueOf(position));
    }

    private static class NoteListAdapter extends ArrayAdapter<Note> {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Note noteItem = getItem(position);
            NoteListItemView noteListItemView = null;
            if (convertView != null) {
                noteListItemView = (NoteListItemView) convertView;
            } else {
                noteListItemView = new NoteListItemView(getContext());
            }
            noteListItemView.setNoteItem(noteItem);
            return noteListItemView;
        }

        public NoteListAdapter(Context context, int textViewResourceId, List<Note> objects) {
            super(context, textViewResourceId, objects);
        }

    }
}
