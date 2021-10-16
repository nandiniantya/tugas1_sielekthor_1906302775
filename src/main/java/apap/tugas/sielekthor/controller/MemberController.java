package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MemberController {

    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public String viewAllMember(Model model) {
        List<MemberModel> listMember = memberService.findByOrderByIdMemberAsc();
        model.addAttribute("listMember", listMember);

        return "viewall-member";
    }

    @GetMapping("/member/tambah")
    public String addMemberFormPage(Model model) {
        MemberModel member = new MemberModel();
        model.addAttribute("member", member);

        return "form-add-member";
    }

    @PostMapping("/member/tambah")
    public String addMemberSubmitPage(
            @ModelAttribute MemberModel member,
            Model model){
        memberService.addMember(member);
        model.addAttribute("namaMember", member.getNamaMember());
        return "add-member";
    }

    @GetMapping("/member/ubah/{idMember}")
    public String updateMemberFormPage(
            @PathVariable Long idMember,
            Model model
    ) {
        MemberModel member = memberService.getMemberByIdMember(idMember);
        model.addAttribute("member", member);
        return "form-update-member";
    }

    @PostMapping("/member/ubah")
    public String updateMemberSubmitPage(
            @ModelAttribute MemberModel member,
            Model model
    ){
        MemberModel updatedMember = memberService.updateMember(member);
        model.addAttribute("updatedMember", updatedMember);
        return "update-member";
    }

}
