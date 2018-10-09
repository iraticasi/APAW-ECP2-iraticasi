package api.dtos;

import api.entities.Genre;

public class SongDto {

    private String name;

    private String singer;

    private Integer seconds;

    private String date;

    private Genre genre;

    public SongDto(String name, String singer, Integer seconds, String date, Genre genre) {
        this.name = name;
        this.singer = singer;
        this.seconds = seconds;
        this.date = date;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", seconds=" + seconds +
                ", date='" + date + '\'' +
                ", genre=" + genre +
                '}';
    }
}
