package member.join.controller;

import member.join.dto.JoinDTO;
import member.join.dto.PageDTO;
import member.join.entity.JoinEntity;
import member.join.repository.JoinRepository;
import member.join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JoinController {
    @Autowired
    JoinRepository joinRepository;
    @Autowired
    JoinService joinService;

    @PostMapping("/write")
    public String write(JoinDTO joinDTO){
        joinService.write(joinDTO);
        return "redirect:/list";
    }

    @GetMapping("/write")
    public String join(){
        return "write";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1", name = "page") int page) {
        PageDTO<JoinDTO, JoinEntity> result = joinService.getList(page);
        model.addAttribute("data", result);
        return "list";
    }

    @GetMapping("/view")
    public String view(@RequestParam("idx") Long idx, Model model) {
        JoinDTO view = joinService.find(idx);
        model.addAttribute("view", view);
        return "view";
    }

    @PostMapping("/modify/{idx}")
    public String modify(@PathVariable Long idx, Model model) {
        JoinDTO modify = joinService.find(idx);
        model.addAttribute("modify", modify);
        return "modify";
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modify(@RequestBody JoinDTO joinDTO){
        System.out.println(joinDTO.getName());
        System.out.println("---------------------------------------------------------------------");
        JoinDTO joindto = joinService.modify(joinDTO);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Void> delete(@PathVariable Long idx) {
        joinService.delete(idx);

        return ResponseEntity.ok()
                .build();
    }
}
