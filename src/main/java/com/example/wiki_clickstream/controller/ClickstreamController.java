package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.Clickstream;
import com.example.wiki_clickstream.service.IClickstreamService;
import com.example.wiki_clickstream.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
@RestController
@RequestMapping("/clickstream")
public class ClickstreamController {
    @Autowired
    private IClickstreamService clickstreamService;

    @GetMapping("/{date}")
    public Result<List<Clickstream>> getDataByDate(@PathVariable String date) {
        List<Clickstream> clickstreams = clickstreamService.getDataByDate(date);
        return Result.success(clickstreams);
    }
}

