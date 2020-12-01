//package com.ereport.master.kenML.controller;
//
//import com.ereport.master.controller.BaseController;
//import com.ereport.master.kenML.service.NewsServiceImpl;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/news")
//public class NewsController extends BaseController {
//    private final NewsServiceImpl newsService;
//
//    public NewsController(NewsServiceImpl newsService) {
//        this.newsService = newsService;
//    }
//
//
//    @GetMapping("/findAll")
//    public ResponseEntity<?> getAll() {
//        return buildResponse(newsService.findAll(), HttpStatus.OK);
//    }
//
//}
