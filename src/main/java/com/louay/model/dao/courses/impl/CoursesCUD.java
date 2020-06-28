package com.louay.model.dao.courses.impl;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.courses.members.CourseMembers;
import com.louay.model.chains.courses.members.UsersAttendance;
import com.louay.model.dao.courses.CreateCoursesDAO;
import com.louay.model.dao.courses.DeleteCoursesDAO;
import com.louay.model.dao.courses.UpdateCoursesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class CoursesCUD implements CreateCoursesDAO, UpdateCoursesDAO, DeleteCoursesDAO {
    private final NamedParameterJdbcTemplate jdbcNamedTemplate;
    private final ApplicationContext context;

    @Autowired
    public CoursesCUD(DataSource dataSource, @Qualifier("buildAnnotationContextModel") ApplicationContext context) {
        this.jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.context = context;
    }

    @Override
    public Long createCourses(Courses course) {
        SqlParameterSource param = buildCoursesParameter(course);
        KeyHolder keyHolder = (KeyHolder) this.context.getBean("buildKeyHolder");

        String query = "INSERT INTO `courses`(`course_name`, `start_date`, `end_date`, `instructor_id`) VALUES " +
                "(:course_name, :start_date, :end_date, :instructor_id);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);
        if (keyHolder.getKey() != null && result > 0){
            return keyHolder.getKey().longValue();
        }else {
            return (long)-1;
        }
    }

    @Override
    public int createCourseMembers(CourseMembers courseMembers) {
        SqlParameterSource param = buildCourseMembersParameter(courseMembers);

        String query = "INSERT INTO `course_members`(`user_id`, `course_id`, `register_date`) VALUES " +
                "(:user_id, :course_id, :register_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createStudentsAttendances(UsersAttendance usersAttendance) {
        SqlParameterSource param = buildStudentsAttendancesParameter(usersAttendance);

        String query = "INSERT INTO `students_attendances`(`student_id`, `course_id`, `attendance_date`) VALUES " +
                "(:student_id, :course_id, :attendance_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    private SqlParameterSource buildCoursesParameter(Courses courses){
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("course_id", courses.getCourseID(), Types.BIGINT);
        param.addValue("course_name", courses.getCourseName(), Types.VARCHAR);
        param.addValue("start_date", courses.getStartDate(), Types.TIMESTAMP);
        param.addValue("end_date", courses.getEndDate(), Types.TIMESTAMP);
        param.addValue("instructor_id", courses.getInstructor().getEmail(), Types.VARCHAR);

        return param;
    }

    private SqlParameterSource buildCourseMembersParameter(CourseMembers courseMembers){
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_id", courseMembers.getUser().getEmail(), Types.VARCHAR);
        param.addValue("course_id", courseMembers.getCourse().getCourseID(), Types.BIGINT);
        param.addValue("register_date", courseMembers.getRegisterDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildStudentsAttendancesParameter(UsersAttendance usersAttendance){
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("student_id", usersAttendance.getUser().getEmail(), Types.VARCHAR);
        param.addValue("course_id", usersAttendance.getCourse().getCourseID(), Types.BIGINT);
        param.addValue("attendance_date", usersAttendance.getAttendanceDate(), Types.TIMESTAMP);

        return param;
    }

    @Override
    public int updateCoursesByCourseID(Courses course) {
        SqlParameterSource param = buildCoursesParameter(course);

        String query = "UPDATE `courses` SET `course_name` = :course_name, `start_date` = :start_date, " +
                "`end_date` = :end_date, `instructor_id` = :instructor_id WHERE `course_id` = :course_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateCourseMembersByCourseID(CourseMembers courseMembers) {
        SqlParameterSource param = buildCourseMembersParameter(courseMembers);

        String query = "UPDATE `course_members` SET `user_id` = :user_id, `register_date` = :register_date " +
                "WHERE `course_id` = :course_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateStudentsAttendancesByUserID(UsersAttendance usersAttendance) {
        SqlParameterSource param = buildStudentsAttendancesParameter(usersAttendance);

        String query = "UPDATE `students_attendances` SET `course_id` = :course_id, `attendance_date` = :attendance_date " +
                "WHERE `student_id` = :student_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteCoursesByCourseID(Courses course) {
        SqlParameterSource param = buildCoursesParameter(course);

        String query = "DELETE FROM `courses` WHERE `course_id` = :course_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteCourseMembersByCourseID(CourseMembers courseMembers) {
        SqlParameterSource param = buildCourseMembersParameter(courseMembers);

        String query = "DELETE FROM `course_members` WHERE `course_id` = :course_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteStudentsAttendancesByUserID(UsersAttendance usersAttendance) {
        SqlParameterSource param = buildStudentsAttendancesParameter(usersAttendance);

        String query = "DELETE FROM `students_attendances` WHERE `student_id` = :student_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }
}
