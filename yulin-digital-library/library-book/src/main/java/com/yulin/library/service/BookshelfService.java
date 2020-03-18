package com.yulin.library.service;

import com.mongodb.client.result.UpdateResult;
import com.yulin.library.model.entity.Bookshelf;
import com.yulin.library.mongodb.service.BaseService;

public interface BookshelfService extends BaseService<Bookshelf> {

    UpdateResult updateSortNumber(Long userId, Integer scope);

}
