package com.wisoft.managementsystem.service;

import com.wisoft.managementsystem.dao.*;
import com.wisoft.managementsystem.dto.CourseDTO;
import com.wisoft.managementsystem.dto.EnrolledStudentDTO;
import com.wisoft.managementsystem.dto.SubjectDTO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.util.ArrayList;

public class ManagementSystem implements ManagementService {

    private static ManagementSystem uniqueInstance;

    private ManagementSystem() {
    }

    public static synchronized ManagementSystem getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ManagementSystem();
        }
        return uniqueInstance;
    }

    @Override
    public boolean addUser(UserDTO userDTO) throws IllegalAccessException {
        InsertUserDAO insertUserDAO = new InsertUserDAO();
        return insertUserDAO.access(userDTO).getIsSuccess();
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) throws IllegalAccessException {
        DeleteUserDAO deleteUserDAO = new DeleteUserDAO();
        return deleteUserDAO.access(userDTO).getIsSuccess();
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws IllegalAccessException {
        FindAllUsersDAO DAOFindAllUsersDAO = new FindAllUsersDAO();
        return DAOFindAllUsersDAO.access("").getDtos();
    }

    @Override
    public boolean addSubject(SubjectDTO subjectDTO) throws IllegalAccessException {
        InsertSubjectDAO insertSubjectDAO = new InsertSubjectDAO();
        return insertSubjectDAO.access(subjectDTO).getIsSuccess();
    }

    @Override
    public boolean deleteSubject(SubjectDTO subjectDTO) throws IllegalAccessException {
        DeleteSubjectDAO deleteSubjectDAO = new DeleteSubjectDAO();
        return deleteSubjectDAO.access(subjectDTO).getIsSuccess();
    }

    @Override
    public ArrayList<SubjectDTO> getMySubjects(UserDTO userDTO) throws IllegalAccessException {
        FindMySubjectsDAO findMySubjectsDAO = new FindMySubjectsDAO();
        return findMySubjectsDAO.access(userDTO).getDtos();
    }

    @Override
    public ArrayList<EnrolledStudentDTO> getMyStudents(UserDTO userDTO) throws IllegalAccessException {
        FindEnrolledStudentsDAO DAOFindEnrolledStudentsDAO = new FindEnrolledStudentsDAO();
        return DAOFindEnrolledStudentsDAO.access(userDTO).getDtos();
    }

    @Override
    public boolean giveGrade(CourseDTO courseDTO) throws IllegalAccessException {
        GiveGradeDAO giveGradeDAO = new GiveGradeDAO();
        return giveGradeDAO.access(courseDTO).getIsSuccess();
    }

    @Override
    public ArrayList<SubjectDTO> getAllSubjects() throws IllegalAccessException {
        FindAllSubjectsDAO DAOFindAllSubjectsDAO = new FindAllSubjectsDAO();
        return DAOFindAllSubjectsDAO.access("").getDtos();
    }

    @Override
    public ArrayList<CourseDTO> getMyCourse(UserDTO userDTO) throws IllegalAccessException {
        FindMyCoursesDAO findMyCoursesDAO = new FindMyCoursesDAO();
        return findMyCoursesDAO.access(userDTO).getDtos();
    }

    @Override
    public boolean registerCourse(CourseDTO courseDTO) throws IllegalAccessException {
        InsertCourseDAO DAOInsertCourseDAO = new InsertCourseDAO();
        return DAOInsertCourseDAO.access(courseDTO).getIsSuccess();
    }

    @Override
    public boolean cancelCourse(CourseDTO courseDTO) throws IllegalAccessException {
        DeleteCourseDAO deleteCourseDAO = new DeleteCourseDAO();
        return deleteCourseDAO.access(courseDTO).getIsSuccess();
    }

}
