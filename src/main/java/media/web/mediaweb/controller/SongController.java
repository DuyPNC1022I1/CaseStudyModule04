package media.web.mediaweb.controller;

import media.web.mediaweb.model.Album;
import media.web.mediaweb.model.Song;
import media.web.mediaweb.service.AlbumService;
import media.web.mediaweb.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Value("${upload.path}")
    private String linkSave;

    @Value("${display.path}")
    private String displayLink;

    @Autowired
    private SongService songService;


    @GetMapping
    public ResponseEntity<Iterable<Song>> findAllSong(@PageableDefault(value = 4) Pageable pageable, @RequestParam("search") Optional<String> name) {
        Page<Song> songs;
        if (name.isPresent()) {
            songs = songService.findAll(name.get(), pageable);
        } else {
            songs = songService.findAll("", pageable);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<Iterable<Song>> findSongInPlaylist(@RequestParam String name) {
        Iterable<Song> songs = songService.findByNameContaining(name);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(songService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Song song) {
        songService.save(song);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        songService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Song> createUpload(@RequestPart(value = "avatar", required = false) MultipartFile avatar,
                                             @RequestPart(value = "fileMp3", required = false) MultipartFile fileMp3,
                                             @RequestPart("song") Song song) throws IOException {
        if (avatar != null && fileMp3 != null) {
            //Avatar
            String avatarPath = avatar.getOriginalFilename();
            FileCopyUtils.copy(avatar.getBytes(), new File(linkSave + avatarPath));
            song.setAvatar(displayLink + avatarPath);
            //FileMp3
            String fileMp3Path = fileMp3.getOriginalFilename();
            FileCopyUtils.copy(avatar.getBytes(), new File(linkSave + fileMp3Path));
            song.setFileMp3(displayLink + fileMp3Path);
        } else {
            song.setAvatar(displayLink + "default.png");
            song.setFileMp3(displayLink + "default.png");
        }
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView pageFail() {
        return new ModelAndView("err");
    }
}
