package com.exam.studentmanage.controller;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/student") // 경로 : templates 패키지 밑의 student 패키지
public class StudentController {

    private final StudentService studentService;




    @GetMapping("/list2")
    public void list(PageRequestDTO pageRequestDTO,Model model){

        PageResponseDTO<StudentDTO> responseDTO = studentService.list(pageRequestDTO);
        log.info(responseDTO);

        model.addAttribute("responseDTO",responseDTO);
    }


    @PostMapping("/register2")
    public String register2Post(@Valid StudentDTO studentDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/student/register2";
        }

        Long sno = studentService.register(studentDTO);

        redirectAttributes.addFlashAttribute("result",sno);



        return "redirect:/student/list2";
    }

    @GetMapping("/register2")
    public void register2Get(Model model, StudentDTO studentDTO){
        model.addAttribute("studentDTO", studentDTO);
    }

    @GetMapping({"/read2", "/modify2"})
    public void read2(Long sno, PageRequestDTO pageRequestDTO,Model model){
        StudentDTO studentDTO = studentService.readOne(sno);

        log.info(studentDTO);

        model.addAttribute("dto",studentDTO);
    }

    @PostMapping("/modify2")
    public String modify( PageRequestDTO pageRequestDTO,
                          @Valid StudentDTO studentDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("haserror");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            redirectAttributes.addAttribute("sno",studentDTO.getSno());

            return "redirect:/student/modify2?"+link;
        }

        studentService.modify(studentDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("sno",studentDTO.getSno());

        return "redirect:/student/read2";
    }


    @PostMapping("/remove2")
    public String remove2(Long sno, RedirectAttributes redirectAttributes){
        studentService.delete(sno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/student/list2";
    }

    @GetMapping("/list")
    public void listGet(PageRequestDTO pageRequestDTO, Model model){
        
        log.info("list 겟매핑 들어옴");

        PageResponseDTO<StudentDTO> responseDTO = studentService.list(pageRequestDTO);
        //PageresponseDTO 객체에 pageRequestDTO 리스트 담음

        log.info(responseDTO);

        model.addAttribute("responseDTO",responseDTO);
        //모델에 담음
    }


    @PostMapping("/list")
    public void listPost(PageRequestDTO pageRequestDTO, String keyword, String type, Model model, Integer size){
        //list post방식은 검색타입,검색어,사이즈도 같이 불러옴

        pageRequestDTO.setType(type); //가져온 type을 pageRequestDTO에 담음
        pageRequestDTO.setKeyword(keyword); //이하동문

        PageResponseDTO<StudentDTO> responseDTO = studentService.list(pageRequestDTO);
        //세팅한 pageRequestDTO를 다시 list로 넣음

        model.addAttribute("responseDTO",responseDTO); //모델에 담음
    }

    @GetMapping("/register")
    public void registerGet(){
        
        log.info("레지스터 get 도착");

    }

    @PostMapping("/register")
    public String registerGet(@Valid StudentDTO studentDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        //Valid = 유효성검사를 위한 이노태이션 태그

        log.info("register Post 도착");

        if(bindingResult.hasErrors()){ //에러 발생시
            log.info("헤즈에러@@@@@@@@@@@");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/student/register";
        }

        log.info(studentDTO);

        Long sno = studentService.register(studentDTO);

        //생성 한 후 sno에 값을 넣음

        redirectAttributes.addFlashAttribute("result",sno);

        return "redirect:/student/list";
    }


    @GetMapping({"/read","/modify"})
    //읽기와 수정의 get방식을 같이 처리한다(수정은 get방식으로 읽은 뒤 값을 post로 받아서 수정하는 식)
    public void read(Long sno, PageRequestDTO pageRequestDTO,Model model){

        log.info("읽기 들어옴");
        StudentDTO studentDTO = studentService.readOne(sno);
        //가져온 sno로 값을 읽고~~~~~ DTO 생성

        log.info(studentDTO);

        model.addAttribute("studentDTO",studentDTO);
        //스튜던트dto에 담는다
    }


    @PostMapping("/modify")
    public String modify(@Valid StudentDTO studentDTO,BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO){

        if(bindingResult.hasErrors()){
            log.info("haserror 발생~~~");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/student/modify?"+pageRequestDTO.getLink();
            //에러 발생했으니 다시 수정페이지로 보낸다
        }

        studentService.modify(studentDTO);

        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("sno",studentDTO.getSno());

        return "redirect:/student/read";
    }


    @PostMapping("/remove")
    public String remove(Long sno, RedirectAttributes redirectAttributes){
        log.info("remove 왔음");

        studentService.delete(sno);

        redirectAttributes.addAttribute("result","removed");

        return "redirect:/student/list";
    }


}
