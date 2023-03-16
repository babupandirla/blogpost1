package com.wrapy.blogpost.service;

import com.wrapy.blogpost.entity.Post;
import com.wrapy.blogpost.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto save(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);
    void deletePost(long id);
}
