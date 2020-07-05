package com.louay.model.dao.feedback.impl;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;
import com.louay.model.dao.feedback.CreateFeedbackDAO;
import com.louay.model.dao.feedback.DeleteFeedbackDAO;
import com.louay.model.dao.feedback.UpdateFeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class FeedbackCUD implements CreateFeedbackDAO, UpdateFeedbackDAO, DeleteFeedbackDAO {
    private final NamedParameterJdbcTemplate jdbcNamedTemplate;
    private final ApplicationContext context;

    @Autowired
    public FeedbackCUD(DataSource dataSource, @Qualifier("buildAnnotationContextModel") ApplicationContext context) {
        this.jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.context = context;
    }

    @Override
    public Long createCoursesFeedback(Feedback feedback) {
        SqlParameterSource param = buildCourseFeedbackParameter(feedback);
        KeyHolder keyHolder = (KeyHolder) this.context.getBean("buildKeyHolder");

        final String query = "INSERT INTO `course_feedback`(`course_id`) VALUE (:course_id);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);
        if (keyHolder.getKey() != null && result > 0) {
            return keyHolder.getKey().longValue();
        } else {
            return (long) -1;
        }
    }

    @Override
    public int createFeedbackFiles(FileFeedback fileFeedback) {
        SqlParameterSource param = buildFeedbackFileParameter(fileFeedback);

        final String query = "INSERT INTO `feedback_file`(`feedback_id`, `user_id`, `file`, `file_extension`, `upload_date`) " +
                "VALUES (:feedback_id, :user_id, :file, :file_extension, :upload_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createFeedbackMessages(MessageFeedback messageFeedback) {
        SqlParameterSource param = buildFeedbackMessageParameter(messageFeedback);

        final String query = "INSERT INTO `feedback_message`(`feedback_id`, `user_id`, `post_message`, `feedback_date`) " +
                "VALUES (:feedback_id, :user_id, :post_message, :feedback_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public Long createFeedbackComments(Comment comment) {
        SqlParameterSource param = buildFeedbackCommentsParameter(comment);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        final String query = "INSERT INTO `feedback_comments`(`feedback_id`, `user_id`, `comment_message`, `comment_date`) " +
                "VALUES (:feedback_id, :user_id, :comment_message, :comment_date);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);

        if (keyHolder.getKey() != null && result > 0) {
            return keyHolder.getKey().longValue();
        } else {
            return (long) -1;
        }
    }

    private SqlParameterSource buildCourseFeedbackParameter(Feedback feedback) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("feedback_id", feedback.getFeedbackID(), Types.BIGINT);
        param.addValue("course_id", feedback.getCourse().getCourseID(), Types.BIGINT);

        return param;
    }

    private SqlParameterSource buildFeedbackFileParameter(FileFeedback fileFeedback) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("feedback_id", fileFeedback.getFeedbackID(), Types.BIGINT);
        param.addValue("user_id", fileFeedback.getUser().getEmail(), Types.VARCHAR);
        param.addValue("file", new SqlLobValue(fileFeedback.getFile()), Types.BLOB);
        param.addValue("file_extension", fileFeedback.getFileExtension(), Types.VARCHAR);
        param.addValue("upload_date", fileFeedback.getFeedbackDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildFeedbackMessageParameter(MessageFeedback messageFeedback) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("feedback_id", messageFeedback.getFeedbackID(), Types.BIGINT);
        param.addValue("user_id", messageFeedback.getUser().getEmail(), Types.VARCHAR);
        param.addValue("post_message", messageFeedback.getPostMessage().toString(), Types.VARCHAR);
        param.addValue("feedback_date", messageFeedback.getFeedbackDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildFeedbackCommentsParameter(Comment comment) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("comment_id", comment.getCommentID(), Types.BIGINT);
        param.addValue("feedback_id", comment.getFeedback().getFeedbackID(), Types.BIGINT);
        param.addValue("user_id", comment.getUser().getEmail(), Types.VARCHAR);
        param.addValue("comment_message", comment.getCommentMessage().toString(), Types.VARCHAR);
        param.addValue("comment_date", comment.getCommentDate(), Types.TIMESTAMP);

        return param;
    }

    @Override
    public int updateCoursesFeedbackByFeedbackID(Feedback feedback) {
        SqlParameterSource param = buildCourseFeedbackParameter(feedback);

        final String query = "UPDATE `course_feedback` SET `course_id` = :course_id WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateFeedbackFilesByFeedbackID(FileFeedback fileFeedback) {
        SqlParameterSource param = buildFeedbackFileParameter(fileFeedback);

        final String query = "UPDATE `feedback_file` SET `user_id` = :user_id, `file` = :file, `file_extension` = :file_extension, " +
                "`upload_date` = :upload_date WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateFeedbackMessagesByFeedbackID(MessageFeedback messageFeedback) {
        SqlParameterSource param = buildFeedbackMessageParameter(messageFeedback);

        final String query = "UPDATE `feedback_message` SET `user_id` = :user_id, `post_message` = :post_message, " +
                "`feedback_date` = :feedback_date WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateFeedbackCommentsByCommentID(Comment comment) {
        SqlParameterSource param = buildFeedbackCommentsParameter(comment);

        final String query = "UPDATE `feedback_comments` SET `feedback_id` = :feedback_id, `user_id` = :user_id, " +
                "`comment_message` = :comment_message, `comment_date` = :comment_date WHERE `comment_id` = :comment_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteCoursesFeedbackByFeedbackID(Feedback feedback) {
        SqlParameterSource param = buildCourseFeedbackParameter(feedback);

        final String query = "DELETE FROM `course_feedback` WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteFeedbackFilesByFeedbackID(FileFeedback fileFeedback) {
        SqlParameterSource param = buildFeedbackFileParameter(fileFeedback);

        final String query = "DELETE FROM `feedback_file` WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteFeedbackMessagesByFeedbackID(MessageFeedback messageFeedback) {
        SqlParameterSource param = buildFeedbackMessageParameter(messageFeedback);

        final String query = "DELETE FROM `feedback_message` WHERE `feedback_id` = :feedback_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteFeedbackCommentsByCommentID(Comment comment) {
        SqlParameterSource param = buildFeedbackCommentsParameter(comment);

        final String query = "DELETE FROM `feedback_comments` WHERE `comment_id` = :comment_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }
}
