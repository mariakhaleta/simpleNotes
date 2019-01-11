package sample;

public class Note {

    String titleNote;
    String fieldNote;
    String tag;

    public Note(String titleNote, String fieldNote, String tag) {
        this.titleNote = titleNote;
        this.fieldNote = fieldNote;
        this.tag = tag;
    }

    private static Note note;

    public static Note getInstance(String titleNote, String fieldNote, String tag){

        if(note == null)
            note = new Note(titleNote, fieldNote, tag);
        return note;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getFieldNote() {
        return fieldNote;
    }

    public void setFieldNote(String fieldNote) {
        this.fieldNote = fieldNote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "TitleNote='" + titleNote + '\'' +
                ", FieldNote='" + fieldNote + '\'' +
                ", Tag='" + tag + '\'' +
                '}';
    }

    public Note() {
    }
}
