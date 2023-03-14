package media.web.mediaweb.service;

import media.web.mediaweb.model.Album;
import media.web.mediaweb.model.Singer;
import media.web.mediaweb.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SingerService {
    @Autowired
    private SingerService singerService;

    public Iterable<Singer> findAll() {
        return singerService.findAll();
    }
    @Transactional
    public Singer save(Singer singer) {
        return singerService.save(singer);
    }

    public void remove(Long id) {
        singerService.remove(id);
    }
}
