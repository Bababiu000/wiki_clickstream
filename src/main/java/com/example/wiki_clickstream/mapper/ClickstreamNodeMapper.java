package com.example.wiki_clickstream.mapper;

import com.example.wiki_clickstream.entity.ClickstreamNode;
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
 * @since 2023-09-23
 */
public interface ClickstreamNodeMapper extends BaseMapper<ClickstreamNode> {

    List<String> getDateRange(@Param("lang") String lang);

    List<ClickstreamNode> getNodeList(@Param("lang") String lang, @Param("date") LocalDate date, @Param("offset") Integer offset,
                                  @Param("pageSize") Integer pageSize, @Param("keyword") String keyword);

    Long getListTotal(@Param("lang") String lang, @Param("date") LocalDate date, @Param("keyword") String keyword);

    List<ClickstreamNode> getCenterNodes(@Param("lang") String lang, @Param("date") LocalDate date);

    List<ClickstreamNode> getClusterNodes(@Param("lang") String lang, @Param("date") LocalDate date, @Param("label") Integer label);

    List<String> getClusterNodesName(@Param("lang") String lang, @Param("date") LocalDate date, @Param("label") Integer label);
}
