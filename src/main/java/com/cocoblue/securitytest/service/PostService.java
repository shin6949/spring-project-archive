package com.cocoblue.securitytest.service;

import java.util.List;
import com.cocoblue.securitytest.dto.Post;

public interface PostService {
    public List<Post> listAll();
    public void insert (Post post);
    public Post read(Long number);
    public void modify(Post post);
    public void delete(Long number);
}
