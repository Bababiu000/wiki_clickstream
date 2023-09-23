package com.example.wiki_clickstream.mapper;

import com.example.wiki_clickstream.entity.Clickstream;
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
public interface ClickstreamMapper extends BaseMapper<Clickstream> {

    List<Clickstream> getDataByDate(@Param("date") LocalDate date);
}
