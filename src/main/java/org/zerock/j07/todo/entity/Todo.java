package org.zerock.j07.todo.entity;

import lombok.*;
import org.hibernate.annotations.SQLInsert;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity {

    //pk설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(nullable = false, length = 300)
    private String content;

    private boolean del;

    public void changeTitle(String Content) {
        this.content = content;
    }
}
