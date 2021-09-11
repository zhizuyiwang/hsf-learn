package com.heima.article.controller.v1;

import com.heima.article.apis.ArticleHomeControllerApi;
import com.heima.article.service.AppArticleService;
import com.heima.common.article.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController implements ArticleHomeControllerApi {

    @Autowired
    AppArticleService appArticleService;

    @GetMapping("/load")
    @Override
    public ResponseResult load(ArticleHomeDto dto) {
        return appArticleService.load(dto, ArticleConstants.LOAD_TYPE_MORE);
    }

    @GetMapping("/loadmore")
    @Override
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleConstants.LOAD_TYPE_MORE);
    }

    @GetMapping("/loadnew")
    @Override
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleConstants.LOAD_TYPE_NEW);
    }
}
