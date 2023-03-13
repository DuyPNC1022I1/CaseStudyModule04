package web.media.webmedia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.media.webmedia.model.Song;

import java.util.List;
import java.util.Optional;

public class SongService implements Icrud<Song>{
    @Override
    public Page<Song> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Song> finById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Song song) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Song> findByName(String name) {
        return null;
    }
}
