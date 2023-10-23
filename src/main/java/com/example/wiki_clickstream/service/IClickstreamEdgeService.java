package com.example.wiki_clickstream.service;

import com.example.wiki_clickstream.entity.ClickstreamEdge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-21
 */
public interface IClickstreamEdgeService extends IService<ClickstreamEdge> {

    List<ClickstreamEdge> getCenterEdges(String date);

    List<ClickstreamEdge> getClusterEdges(String date, Integer center);
}
