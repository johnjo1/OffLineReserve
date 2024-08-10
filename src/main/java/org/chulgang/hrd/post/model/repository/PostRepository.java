package org.chulgang.hrd.post.model.repository;

import org.chulgang.hrd.post.domain.Post;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

import java.util.ArrayList;

interface PostRepository {
    ArrayList<UsersLoginResponse> list_postsS();
    void deletePost(long postId);
    Post getSubjectAndContent(long postId);
    void update_posts(Post post);
}
