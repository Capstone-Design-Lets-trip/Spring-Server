package com.example.letstrip.controller;

import com.example.letstrip.dto.CourseDto;
import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.RouteDto;
import com.example.letstrip.entity.Course;
import com.example.letstrip.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCourse(@RequestBody List<RouteDto> routeDtoList) {
        try {
            int span = routeDtoList.size();
            List<Course> courses = new ArrayList<>();
            for (int i = 0; i < span; i++) {
                Course course = courseService.saveCourse(routeDtoList.get(i).getNames(), routeDtoList.get(i).getArrive_times(),
                        routeDtoList.get(i).getDepart_times(), routeDtoList.get(i).getMove_times());
                courses.add(course);
            }

            List<CourseDto> courseDtos = new ArrayList<>();
            for (int i = 0; i < span; i++) {
                List<Integer> course = courseService.getCourse(courses.get(i));
                CourseDto dto = CourseDto.builder().course(course).build();
                courseDtos.add(dto);
            }

            return ResponseEntity.ok().body(courseDtos);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto
                    .builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
