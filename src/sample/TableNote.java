package sample;

public class TableNote extends AbstractNote {

    Integer size;
    String type;
    String tag;
    String name;

    @Override
    public void show() {

    }

    public TableNote(Integer size, String type, String tag, String name) {
        this.size = size;
        this.type = type;
        this.tag = tag;
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
