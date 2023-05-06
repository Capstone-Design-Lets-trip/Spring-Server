package com.example.letstrip.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DETAIL_COURSE")
@Getter
@Setter
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

    @Column(columnDefinition = "LONGTEXT")
    private String arriveTimes;

    @Column(columnDefinition = "LONGTEXT")
    private String departTimes;

    @Column(columnDefinition = "LONGTEXT")
    private String moveTimes;

    // == 생성 메서드 == //
    public static DetailCourse createDetailCourse(String spot, String arriveTimes, String departTimes, String moveTimes) {
        DetailCourse detailCourse = new DetailCourse();
        detailCourse.setSpot(spot);
        detailCourse.setArriveTimes(arriveTimes);
        detailCourse.setDepartTimes(departTimes);
        detailCourse.setMoveTimes(moveTimes);

        return detailCourse;
    }
}
