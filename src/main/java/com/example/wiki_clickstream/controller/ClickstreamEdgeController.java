package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.ClickstreamEdge;
import com.example.wiki_clickstream.service.IClickstreamEdgeService;
import com.example.wiki_clickstream.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-21
 */
@RestController
@RequestMapping("/clickstream_edge")
public class ClickstreamEdgeController {

    @Autowired
    IClickstreamEdgeService clickstreamEdgeService;

    @GetMapping("/center/{date}")
    public Result<List<ClickstreamEdge>> getCenters(@PathVariable String date) {
        List<ClickstreamEdge> clickstreamEdgeVos  = clickstreamEdgeService.getCenterEdges(date);
        return Result.success(clickstreamEdgeVos);
    }

    @GetMapping("/cluster/{date}")
    public Result<List<ClickstreamEdge>> getCenters(@PathVariable String date, @RequestParam Integer center) {
        List<ClickstreamEdge> clickstreamEdgeVos  = clickstreamEdgeService.getClusterEdges(date, center);
        return Result.success(clickstreamEdgeVos);
    }
}

