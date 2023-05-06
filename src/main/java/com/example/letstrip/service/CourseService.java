package com.example.letstrip.service;

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
    public Course saveCourse(List<String> names, List<String> arriveTimes, List<String> departTimes, List<Integer> moveTimes) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            sb1.append(tourSpotRepository.findByName(names.get(i)).getId() + " ");
            sb2.append(arriveTimes.get(i) + " ");
            sb3.append(departTimes.get(i) + " ");
            sb4.append(moveTimes.get(i) + " ");
        }

        DetailCourse detailCourse = DetailCourse.createDetailCourse(sb1.toString(), sb2.toString(), sb3.toString(), sb4.toString());

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
