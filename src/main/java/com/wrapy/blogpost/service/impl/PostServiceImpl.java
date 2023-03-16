package com.wrapy.blogpost.service.impl;

import com.wrapy.blogpost.entity.Post;
import com.wrapy.blogpost.exception.ResourceNotFoundException;
import com.wrapy.blogpost.payload.PostDto;
import com.wrapy.blogpost.repositories.PostRepository;
import com.wrapy.blogpost.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto save(PostDto postDto) {
        Post post=mapToEntity(postDto);
        Post response=postRepository.save(post);
        PostDto postDto1=mapToDto(response);
        return postDto1;

    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id","null"));
        PostDto postDto=mapToDto(post);
        return postDto;
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id","null"));
        if(!post.equals(null)){
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
             return mapToDto(postRepository.save(post));
        }else{
            throw new ResourceNotFoundException("Post","id",String.valueOf(id));
        }

    }

    @Override
    public void deletePost(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id","null"));
        if(!post.equals(null)){
            postRepository.delete(post);
        }
    }

    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
