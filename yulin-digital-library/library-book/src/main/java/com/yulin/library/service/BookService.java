package com.yulin.library.service;

import com.yulin.library.model.entity.Book;
import com.yulin.library.mongodb.service.BaseService;

public interface BookService extends BaseService<Book> {
    /**
     * 图书
     */
    Integer BOOK=1;
    /**
     * 音频
     */
    Integer AUDIO=2;
    /**
     * 视频
     */
    Integer VIDEO=3;
    /**
     * 文章
     */
    Integer ARTICLE=4;
    /**
     * VIP图书
     */
    Integer VIP_BOOK=1;
    /**
     * 收费
     */
    Integer NOT_FREE=0;
}
