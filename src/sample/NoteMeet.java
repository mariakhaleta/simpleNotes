package sample;

public class NoteMeet extends Note {

    String data;

    public NoteMeet(String titleNote, String fieldNote, String tag, String data) {
        super(titleNote, fieldNote, tag);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
