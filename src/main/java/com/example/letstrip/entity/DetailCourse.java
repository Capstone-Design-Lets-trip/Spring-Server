package com.example.letstrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DETAIL_COURSE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCourse {

    @Id
    @GeneratedValue
    @Column(name = "DETAIL_COURSE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    private String spot;

    // == 연관관계 메서드 == //
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    // == 생성 메서드 == //
    public static DetailCourse createDetailCourse(String spot) {
        DetailCourse detailCourse = new DetailCourse();
        detailCourse.setSpot(spot);

        return detailCourse;
    }
}
