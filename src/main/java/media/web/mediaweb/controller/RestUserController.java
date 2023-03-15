package media.web.mediaweb.controller;


import media.web.mediaweb.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@PropertySource("classpath:application.properties")
public class RestUserController {
    @Autowired
    private SongService songService;
}
