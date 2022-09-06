package com.exe.board;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name ="jpaBoard")
public class JpaBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 20)
    private String writer;

    @Column(length = 200)
    private String content;

    @CreationTimestamp
    private Timestamp regdate;

    @UpdateTimestamp
    private Timestamp updateDate;


}
