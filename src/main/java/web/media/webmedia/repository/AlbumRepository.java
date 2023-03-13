package web.media.webmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.media.webmedia.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
