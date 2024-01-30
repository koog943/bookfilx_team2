package mypage.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Data
public class Mypage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Embedded
    private Logined logined;
}
