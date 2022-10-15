package com.springAndAWSprac.springboot.service.posts;

import com.springAndAWSprac.springboot.domain.posts.Posts;
import com.springAndAWSprac.springboot.domain.posts.PostsRepository;
import com.springAndAWSprac.springboot.web.dto.PostsResponseDto;
import com.springAndAWSprac.springboot.web.dto.PostsSaveRequestDto;
import com.springAndAWSprac.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no content matched with id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no content matched with id="+id));

        return new PostsResponseDto(entity);
    }
}
