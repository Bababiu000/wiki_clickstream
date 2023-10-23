package com.example.wiki_clickstream.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
@Data
public class ClickstreamNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long dictIdx;

    private String name;

    private Long density;

    private Long label;

    private Long dcDictIdx;

    private LocalDate date;

    private Long centerDis;
}
