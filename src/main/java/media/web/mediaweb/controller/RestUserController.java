package media.web.mediaweb.controller;


import media.web.mediaweb.model.Song;
import media.web.mediaweb.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@PropertySource("classpath:application.properties")
public class RestUserController {
    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> findAll() {
        List<Song> songList = songService.findAll();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}
