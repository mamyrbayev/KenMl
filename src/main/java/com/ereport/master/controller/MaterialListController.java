package com.ereport.master.controller;


import com.ereport.master.domain.MaterialList;
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
    public ResponseEntity<?> add(@RequestParam Long id, @RequestBody MaterialList materialList) {
        return buildResponse(materialListService.add(id, materialList), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(materialListService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id,
                                    @RequestParam String materialName) {
        return buildResponse(materialListService.update(id, materialName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        materialListService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }
}
