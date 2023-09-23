package com.example.wiki_clickstream.service;

import com.example.wiki_clickstream.entity.Clickstream;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wiki_clickstream.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
public interface IClickstreamService extends IService<Clickstream> {
    List<Clickstream> getDataByDate(String date);
}
