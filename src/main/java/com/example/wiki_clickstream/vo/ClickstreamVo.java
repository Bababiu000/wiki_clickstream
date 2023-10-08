package com.example.wiki_clickstream.vo;

import com.example.wiki_clickstream.entity.Clickstream;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClickstreamVo extends Clickstream {
    private List<Clickstream> Members;
}