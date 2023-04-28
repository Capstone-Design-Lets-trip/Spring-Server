package com.example.letstrip.security;

import com.example.letstrip.entity.Course;
import com.example.letstrip.entity.DetailCourse;
import com.example.letstrip.repository.CourseRepository;
import com.example.letstrip.repository.TourSpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final TourSpotRepository tourSpotRepository;

    @Transactional
    public Course saveCourse(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(tourSpotRepository.findByName(name).getId());
            sb.append(" ");
        }
        DetailCourse detailCourse = DetailCourse.createDetailCourse(sb.toString());

        Course course = Course.createCourse(detailCourse);

        courseRepository.save(course);
        return course;
    }

    public List<Integer> getCourse(Course course) {
        String spots = course.getDetailCourses().get(0).getSpot();
        StringTokenizer st = new StringTokenizer(spots);
        List<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }
}
