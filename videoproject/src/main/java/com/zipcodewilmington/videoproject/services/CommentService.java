package com.zipcodewilmington.videoproject.services;

import com.zipcodewilmington.videoproject.models.Comment;
import com.zipcodewilmington.videoproject.models.UserComment;
import com.zipcodewilmington.videoproject.models.VideoComments;
import com.zipcodewilmington.videoproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public VideoComments getVideoComments(int videoId){
        Collection<Comment> comments = repository.findCommentByVideoId(videoId);
        List<UserComment> userComments = new ArrayList<>();
        for(Comment comment : comments) {
            UserComment userComment = new UserComment();
            userComment.setUserId(comment.getUserId());
            userComment.setText(comment.getCommentText());
            userComments.add(userComment);
        }
        VideoComments videoComments = new VideoComments();
        videoComments.setVideoId(videoId);
        videoComments.setComments(userComments);
        return videoComments;
    }

}
