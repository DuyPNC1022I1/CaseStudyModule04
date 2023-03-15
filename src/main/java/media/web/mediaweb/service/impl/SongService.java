package media.web.mediaweb.service.impl;

import media.web.mediaweb.model.Song;
import media.web.mediaweb.repository.ISongRepository;
import media.web.mediaweb.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository iSongRepository;
    @Override
    public List<Song> findAll() {
        return iSongRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return iSongRepository.findById(id).get();
    }

    @Override
    public void save(Song song) {
        iSongRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        iSongRepository.deleteById(id);
    }
}
