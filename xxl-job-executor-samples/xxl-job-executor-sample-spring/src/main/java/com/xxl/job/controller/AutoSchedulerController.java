package com.xxl.job.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WIN7 on 2018/4/11.
 */
@RequestMapping(value = "/scheduler")
public class AutoSchedulerController {

    @RequestMapping(value = "deal")
    @ResponseBody
    public void deal(){

    }
}
