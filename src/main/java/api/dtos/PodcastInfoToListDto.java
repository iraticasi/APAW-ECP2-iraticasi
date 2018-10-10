package api.dtos;

import api.entities.Podcast;

public class PodcastInfoToListDto {

    private String id;

    private String name;

    private String description;

    public PodcastInfoToListDto(Podcast podcast) {
        this.id = podcast.getId();
        this.name = podcast.getName();
        this.description = podcast.getDescpription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PodcastWithIdDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
