package com.example.wiki_clickstream.vo;

import com.example.wiki_clickstream.entity.ClickstreamNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClickstreamNodeVo extends ClickstreamNode {
    private List<ClickstreamNode> Members;
}