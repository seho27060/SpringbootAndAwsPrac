package com.springAndAWSprac.springboot.service.posts;

import com.springAndAWSprac.springboot.domain.posts.Posts;
import com.springAndAWSprac.springboot.domain.posts.PostsRepository;
import com.springAndAWSprac.springboot.web.dto.PostsListResponseDto;
import com.springAndAWSprac.springboot.web.dto.PostsResponseDto;
import com.springAndAWSprac.springboot.web.dto.PostsSaveRequestDto;
import com.springAndAWSprac.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no content matched with id="+id));

        postsRepository.delete(posts);
    }
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no content matched with id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
