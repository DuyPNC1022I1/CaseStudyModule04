package web.media.webmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.media.webmedia.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
}
