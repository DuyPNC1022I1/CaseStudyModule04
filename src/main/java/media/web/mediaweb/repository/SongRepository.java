package media.web.mediaweb.repository;

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
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAll(Pageable pageable);
    @Query(value = "select * from  song s where s.name like %:name%", nativeQuery = true)
    Iterable<Song> findAllByNameContaining(@Param("name") String name);
    @Query(value = "select * from song s join singer sg on  s.singer_id= sg.id where sg.name LIKE %:singer%", nativeQuery = true)
    Iterable<Song> findAllBySingerContaining(@Param("singer") String singer);
    @Query(value = "select * from song s join album sg on  s.album_id= sg.id where sg.name LIKE %:album%", nativeQuery = true)
    Iterable<Song> findAllByAlbumContaining(@Param("album") String album);
    @Query(nativeQuery = true,value = "select * from song  where `song`.`singer_id` = :id  ;")
    Iterable<Song> getSongBySinger(@Param("id") Long id);
    @Query(value = "select * from song order by date_create_song desc", nativeQuery = true)
    Iterable<Song> getSongNewest();
}
