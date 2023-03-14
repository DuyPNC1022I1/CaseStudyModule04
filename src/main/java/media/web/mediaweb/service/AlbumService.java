package media.web.mediaweb.service;

import media.web.mediaweb.model.Album;
import media.web.mediaweb.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }
    @Transactional
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void remove(Long id) {
        albumRepository.deleteById(id);
    }

    //Hien thi album by Account
    public Iterable<Album> getPlayListByAccount(Long id) {
        return albumRepository.getPlayListByAccount(id);
    }

    //Hien thi album theo ten
    public Iterable<Album> findByNamePlaylistContaining(String name) {
        return albumRepository.findByNameAlbumContaining(name);
    }
}
