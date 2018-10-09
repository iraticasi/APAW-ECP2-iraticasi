package api.dtos;

public class PlaylistDto {


    private String name;
    private String userId;

    public PlaylistDto(String name, String userId) {
        assert name != null;
        assert userId != null;
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PlaylistDto{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
