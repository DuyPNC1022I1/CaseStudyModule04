package media.web.mediaweb.service;

import media.web.mediaweb.model.Singer;
import media.web.mediaweb.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SingerService {
    @Autowired
    private SingerRepository singerRepository;

    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }
    @Transactional
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    public void remove(Long id) {
        singerRepository.deleteById(id);
    }
}
