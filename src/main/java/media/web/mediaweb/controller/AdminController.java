package media.web.mediaweb.controller;

import media.web.mediaweb.service.AlbumService;
import media.web.mediaweb.service.SingerService;
import media.web.mediaweb.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SingerService singerService;

    @GetMapping
    public String findAll(@PageableDefault(value = 3) Pageable pageable, Model model) {
        model.addAttribute("songs", songService.findAll(pageable));
        model.addAttribute("singer", albumService.findAll());
        model.addAttribute("album", singerService.findAll());
        return "static/image/soundwave/admin";
    }
}
