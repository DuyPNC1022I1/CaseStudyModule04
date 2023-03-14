package media.web.mediaweb.repository;

import media.web.mediaweb.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SingerRepository extends JpaRepository<Singer, Long> {
}
