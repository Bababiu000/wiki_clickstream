package com.example.wiki_clickstream.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2023-10-21
 */
@Data
@TableName("clickstream_edge")
public class ClickstreamEdge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sourceDictIdx;

    private Integer targetDictIdx;

    private Integer distance;

    private Integer weight;

    private LocalDate date;

}
