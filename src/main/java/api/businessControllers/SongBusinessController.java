package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.SongDto;
import api.entities.Song;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SongBusinessController {

    public String create(SongDto songDto) {
        LocalDate localDate =
                (songDto.getDate() == null) ?
                        LocalDate.now() :
                        LocalDate.parse(songDto.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        int seconds = (songDto.getSeconds() == null) ? 0 : songDto.getSeconds().intValue();
        Song song = Song.builder(songDto.getName()).singer(songDto.getSinger()).seconds(seconds)
                .date(localDate).genre(songDto.getGenre()).build();
        DaoFactory.getFactory().getSongDao().save(song);
        return song.getId();
    }

}
