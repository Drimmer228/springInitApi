package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.VisitLog;
import com.example.apisearchpracticebase.Repositories.VisitLogRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("visitLog")
public class VisitLogController {
    @Autowired
    VisitLogRepos visitLogRepos;

    @RequestMapping("/all")
    public Iterable<VisitLog> getAll(){
        return visitLogRepos.findAll();
    }
}
