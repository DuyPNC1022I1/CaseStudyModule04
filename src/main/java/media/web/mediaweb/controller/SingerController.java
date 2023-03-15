package media.web.mediaweb.controller;

import media.web.mediaweb.model.Singer;
import media.web.mediaweb.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/singers")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping()
    @PreAuthorize("hasAuthority('SINGER')")
    public ResponseEntity<Iterable<Singer>> findAllSinger() {
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }




}
