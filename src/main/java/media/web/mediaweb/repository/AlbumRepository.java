package media.web.mediaweb.repository;

import media.web.mediaweb.model.Album;
import media.web.mediaweb.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(nativeQuery = true, value = "select * from album  where `album`.`account_id` = :id ;")
    Iterable<Album> getPlayListByAccount(@Param("id") Long id);

    @Query(value = "select * from  album a where a.name like %:name%", nativeQuery = true)
    Iterable<Album> findByNameAlbumContaining(@Param("name") String name);
}
