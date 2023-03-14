package media.web.mediaweb.controller;


import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@PropertySource("classpath:application.properties")
public class RestUserController {

}
