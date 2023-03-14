package media.web.mediaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    private Double price;
    @DateTimeFormat
    private LocalDate dateCreateSong;
    @Value("0")
    private Long likeSong;
    @ManyToMany
    @JoinColumn(name = "album_id")
    private Set<Album> albums;
    @ManyToMany
    @JoinColumn(name = "singer_id")
    private Set<Singer> singer;

    public Long getLinkSong() {
        return likeSong;
    }

    public void setLikeSong(Long viewSong) {
        this.likeSong = viewSong;
    }

    public int increment(){
        return Math.toIntExact(likeSong++);
    }

}
