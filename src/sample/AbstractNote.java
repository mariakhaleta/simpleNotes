package sample;

public abstract class AbstractNote {
    Note mNote = null;
    public abstract void show();

    Note getNote() {
        return mNote;
    }
}
