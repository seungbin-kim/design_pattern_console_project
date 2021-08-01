package com.wisoft.managementsystem.controller;

import com.wisoft.managementsystem.dto.CourseDTO;
import com.wisoft.managementsystem.dto.EnrolledStudentDTO;
import com.wisoft.managementsystem.dto.SubjectDTO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.util.ArrayList;

public interface ManagementService {
  boolean addUser(UserDTO userDTO) throws IllegalAccessException;

  boolean deleteUser(UserDTO userDTO) throws IllegalAccessException;

  ArrayList<UserDTO> getAllUsers() throws IllegalAccessException;

  boolean addSubject(SubjectDTO subjectDTO) throws IllegalAccessException;

  boolean deleteSubject(SubjectDTO subjectDTO) throws IllegalAccessException;

  ArrayList<SubjectDTO> getMySubjects(UserDTO userDTO) throws IllegalAccessException;

  ArrayList<EnrolledStudentDTO> getMyStudents(UserDTO userDTO) throws IllegalAccessException;

  boolean giveGrade(CourseDTO courseDTO) throws IllegalAccessException;

  ArrayList<SubjectDTO> getAllSubjects() throws IllegalAccessException;

  ArrayList<CourseDTO> getMyCourse(UserDTO userDTO) throws IllegalAccessException;

  boolean registerCourse(CourseDTO courseDTO) throws IllegalAccessException;

  boolean cancelCourse(CourseDTO courseDTO) throws IllegalAccessException;

}
