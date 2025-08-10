package com.security.controller.article;


import com.security.dto.artiicle.ArticleRequestDto;
import com.security.dto.artiicle.ArticleResponseDto;
import com.security.service.ProfileService;
import com.security.service.article.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ProfileService profileService;

    public ArticleController(ArticleService articleService, ProfileService profileService) {
        this.articleService = articleService;
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleRequestDto requestDto) {
        Long userId = getCurrentUserId();
        ArticleResponseDto createdArticle = articleService.createArticle(requestDto, userId);
        return ResponseEntity.ok(createdArticle);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getUserArticles() {
        Long userId = getCurrentUserId();
        List<ArticleResponseDto> articles = articleService.getUserArticles(userId);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long id) {
        ArticleResponseDto article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleRequestDto requestDto) {
        Long userId = getCurrentUserId();
        ArticleResponseDto updatedArticle = articleService.updateArticle(id, requestDto, userId);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        articleService.deleteArticle(id, userId);
        return ResponseEntity.noContent().build();
    }

    private Long getCurrentUserId() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return profileService.getUserIdByUsername(username);
    }
}