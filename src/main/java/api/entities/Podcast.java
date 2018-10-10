package api.entities;

public class Podcast {

    private String id;

    private String name;

    private String descpription;

    public Podcast(String name, String description) {
        assert name != null;
        this.name = name;
        this.descpription = description;
    }

    public Podcast(String name) {
        this(name, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescpription() {
        return descpription;
    }

    public void setDescpription(String descpription) {
        this.descpription = descpription;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", descpription='" + descpription + '\'' +
                '}';
    }
}
