package api.entities;

import java.time.LocalDate;

public class Song {

    private String id;

    private String name;

    private String singer;

    private int seconds;

    private LocalDate date;

    private Genre genre;

    public Song(String name) {
        assert name != null;
        this.name = name;
    }

    public static Builder builder(String name) {
        return new Builder(name);
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public static class Builder {

        private Song song;

        public Builder(String name) {
            this.song = new Song(name);
        }

        public Builder id(String id) {
            this.song.id = id;
            return this;
        }

        public Builder singer(String singer) {
            this.song.singer = singer;
            return this;
        }

        public Builder seconds(int seconds) {
            this.song.seconds = seconds;
            return this;
        }

        public Builder date(LocalDate date) {
            this.song.date = date;
            return this;
        }

        public Builder genre(Genre genre) {
            this.song.genre = genre;
            return this;
        }

        public Song build() {
            return this.song;
        }
    }
}
