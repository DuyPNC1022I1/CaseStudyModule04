package media.web.mediaweb.controller;

import media.web.mediaweb.model.Song;
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
    public ResponseEntity<Iterable<Song>> findAll() {
        return new ResponseEntity<>(songService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Song>> findAll(@PageableDefault(value = 3) Pageable pageable) {
        return new ResponseEntity<>(songService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Song>> findSongInPlaylist(@RequestParam("search") Optional<String> name) {
        Iterable<Song> songs;
        if (name.isPresent()) {
            songs = songService.findByNameContaining(name.get());
        } else {
            songs = songService.findByNameContaining("");
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(songService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody Song song) {
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        songService.remove(id);
        return new ResponseEntity<>("Delete done!", HttpStatus.OK);
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
