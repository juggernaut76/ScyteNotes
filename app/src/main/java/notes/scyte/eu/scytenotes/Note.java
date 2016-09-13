package notes.scyte.eu.scytenotes;

/**
 * Created by gogic on 07.09.16.
 */
public class Note {

    private String subject;
    private String filePath;
    private Long fileDate;
    private NoteType noteType;
    private ReminderType reminderType;

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public ReminderType getReminderType() {
        return reminderType;
    }

    public void setReminderType(ReminderType reminderType) {
        this.reminderType = reminderType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileDate() {
        return fileDate;
    }

    public void setFileDate(Long fileDate) {
        this.fileDate = fileDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
