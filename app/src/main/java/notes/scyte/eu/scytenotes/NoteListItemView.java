package notes.scyte.eu.scytenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by gogic on 08.09.16.
 */
public class NoteListItemView extends RelativeLayout {

    TextView subject;
    TextView modifyDate;
    TextView reminder;

    public NoteListItemView(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View listItemView=inflater.inflate(R.layout.list_item_note,null);
        subject=(TextView) listItemView.findViewById(R.id.listItem_textView_subject);
        subject.setText("");
        modifyDate=(TextView) listItemView.findViewById(R.id.listItem_textView_modifydate);
        reminder=(TextView) listItemView.findViewById(R.id.listItem_textView_reminder);
        addView(listItemView);
    }

    public void setNoteItem(Note note){
        subject.setText(note.getSubject());
    }
}
