package media.web.mediaweb.service;

import media.web.mediaweb.model.Song;
import media.web.mediaweb.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }
    @Transactional
    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void remove(Long id) {
        songRepository.deleteById(id);
    }

    public Iterable<Song> findByNameContaining(String name) {
        return songRepository.findAllByNameContaining(name);
    }

    //Hien thi list bai hat theo album
    public Iterable<Song> findAllByAlbumContaining(String album) {
        return songRepository.findAllByAlbumContaining(album);
    }


    //Hien thi list bai hay theo ca si
    public Iterable<Song> findAllBySingerContaining(String singer) {
        return songRepository.findAllBySingerContaining(singer);
    }

    //Hien thi list bai hat sap xep theo thu tu date_create moi nhat
    public Iterable<Song> getSongNewest() {
        return songRepository.getSongNewest();
    }

}
