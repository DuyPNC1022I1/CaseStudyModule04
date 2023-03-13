package media.web.mediaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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

    @Column(unique = true, nullable = false)
    @NotBlank
    @UniqueElements
    private String name;

    @Column( columnDefinition = "double default 0.0")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    @NotNull
    private Double price;

    @NotBlank(message = "Date path must not be blank")
    private LocalDate date;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Image path must not be blank")
    private String singer;

    @NotBlank(message = "Image path must not be blank")
    private String image;

    @NotBlank(message = "Song must not be blank")
    private String linkSong;

}
