package com.ereport.master.controller;


import com.ereport.master.domain.MaterialList;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.MaterialListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materialList")
public class MaterialListController extends BaseController {
    private final MaterialListService materialListService;

    public MaterialListController(MaterialListService materialListService) {
        this.materialListService = materialListService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(materialListService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MaterialList materialList) throws ServiceException {
        return buildResponse(materialListService.add(materialList), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(materialListService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody MaterialList materialList) throws ServiceException {
        return buildResponse(materialListService.update(materialList), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        materialListService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }
}
