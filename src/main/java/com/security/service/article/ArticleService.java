package com.security.service.article;


import com.security.dto.artiicle.ArticleRequestDto;
import com.security.dto.artiicle.ArticleResponseDto;
import com.security.entity.article.Article;
import com.security.repository.articles.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Transactional
    public ArticleResponseDto createArticle(ArticleRequestDto requestDto, Long userId) {
        Article article = new Article();
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setUserId(userId);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return toResponseDto(savedArticle);
    }

    public List<ArticleResponseDto> getUserArticles(Long userId) {
        return articleRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return toResponseDto(article);
    }

    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto requestDto, Long userId) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        if (!existingArticle.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        existingArticle.setTitle(requestDto.getTitle());
        existingArticle.setContent(requestDto.getContent());
        existingArticle.setUpdatedAt(LocalDateTime.now());
        Article updatedArticle = articleRepository.save(existingArticle);
        return toResponseDto(updatedArticle);
    }

    public void deleteArticle(Long id, Long userId) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        if (!article.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        articleRepository.deleteById(id);
    }

    private ArticleResponseDto toResponseDto(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }
}
