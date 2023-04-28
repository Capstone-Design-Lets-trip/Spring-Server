package com.example.letstrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "COURSE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID")
    private Long id;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<DetailCourse> detailCourses = new ArrayList<>();

    // == 연관관계 메서드 == //
    public void addDetailCourse(DetailCourse detailCourse) {
        detailCourses.add(detailCourse);
        detailCourse.setCourse(this);
    }

    // == 생성 메서드 == //
    public static Course createCourse(DetailCourse... detailCourses) {
        Course course = new Course();
        for (DetailCourse detail : detailCourses) {
            course.addDetailCourse(detail);
        }
        return course;
    }
}
