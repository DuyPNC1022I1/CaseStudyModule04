package web.media.webmedia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Icrud<T>{
    Page<T> findALl(Pageable pageable);
    Optional<T> finById(Long id);
    void save(T t);
    void delete(Long id);
    List<T> findByName(String name);
}