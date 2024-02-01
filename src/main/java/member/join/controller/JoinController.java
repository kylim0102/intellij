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

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/write")
    public String write(JoinDTO joinDTO){
        joinService.write(joinDTO);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1", name = "page") int page) {
        PageDTO<JoinDTO, JoinEntity> result = joinService.getList(page);
        model.addAttribute("data", result);
        return "result";
    }

    @GetMapping("/view")
    public String view(@RequestParam("idx") Long idx, Model model) {
        JoinDTO view = joinService.find(idx);
        model.addAttribute("view", view);
        return "view";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("idx") Long idx, Model model) {
        JoinDTO modify = joinService.find(idx);
        model.addAttribute("modify", modify);
        return "modify";
    }

    @PostMapping("/modify_proc")
    public String modify_proc(JoinDTO joinDTO, Model model) {
        JoinDTO view = joinService.modify(joinDTO);
        model.addAttribute("view", view);
        return "view";
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Void> delete(@PathVariable Long idx) {
        System.out.println("idx= " + idx);
        joinService.delete(idx);

        return ResponseEntity.ok()
                .build();
    }
}
