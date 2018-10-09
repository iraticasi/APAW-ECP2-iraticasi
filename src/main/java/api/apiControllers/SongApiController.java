package api.apiControllers;

import api.businessControllers.SongBusinessController;
import api.dtos.SongDto;
import api.exceptions.ArgumentNotValidException;

public class SongApiController {

    public static final String SONGS = "/songs";

    private SongBusinessController songBusinessController = new SongBusinessController();

    public String create(SongDto songDto) {
        this.validate(songDto, "songDto");
        this.validate(songDto.getName(), "songDto name");
        this.validateDate(songDto.getDate());
        return this.songBusinessController.create(songDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    private void validateDate(String date) {
        String regexp = "\\d{4}/\\d{2}/\\d{2}";
        if ((date != null) && !date.matches(regexp)) {
            throw new ArgumentNotValidException("songDto date wronw format");
        }
    }

    private void validateInteger(Integer integer) {
        if (Integer.signum(integer) == -1) {
            throw new ArgumentNotValidException("songDto seconds is negative");
        }
    }
}
