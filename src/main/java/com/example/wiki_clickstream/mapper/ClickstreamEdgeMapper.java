package com.example.wiki_clickstream.mapper;

import com.example.wiki_clickstream.entity.ClickstreamEdge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-10-21
 */
public interface ClickstreamEdgeMapper extends BaseMapper<ClickstreamEdge> {
    List<ClickstreamEdge> getCenterEdges (@Param("lang") String lang, @Param("date") LocalDate date);
    List<ClickstreamEdge> getClusterEdges (@Param("lang") String lang, @Param("date") LocalDate date, @Param("label") Integer label);
}
