package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.repository.*;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.BarangServiceImpl;
import apap.tugas.sielekthor.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.*;
import java.util.ArrayList;

@Controller
public class BarangController {

    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

     @GetMapping("/barang")
     public String viewAllBarang(Model model) {
         List<BarangModel> listBarang = barangService.findByOrderByIdBarangAsc();
         model.addAttribute("listBarang", listBarang);

         return "viewall-barang";
     }

    @GetMapping("/barang/tambah")
    public String addBarangFormPage(Model model) {
        BarangModel barang = new BarangModel();
        ArrayList<TipeModel> listTipe = (ArrayList<TipeModel>) tipeService.getListTipe();
        model.addAttribute("barang", barang);
        model.addAttribute("listTipe",listTipe);
        return "form-add-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarangSubmitPage(
            @ModelAttribute BarangModel barang,
            Model model){
        barangService.addBarang(barang);
        model.addAttribute("kodeBarang", barang.getKodeBarang());
        return "add-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String viewDetailBarang(
            @PathVariable Long idBarang,
            Model model
    ) {
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        if(barang == null){
            return "barang-null";
        }
        model.addAttribute("barang", barang);
        return "view-barang";
    }

    @GetMapping("/barang/hapus/{idBarang}")
    public String deleteBarang(
            @PathVariable Long idBarang,
            Model model
    ) {
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        //barangService.deleteBarang(barang);
        return "delete-barang";
    }

    @GetMapping("/barang/ubah/{idBarang}")
    public String updateBarangFormPage(
            @PathVariable Long idBarang,
            Model model
    ) {
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        model.addAttribute("barang", barang);
        return "form-update-barang";
    }

    @PostMapping("/barang/ubah/{idBarang}")
    public String updateBarangSubmitPage(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        BarangModel updatedBarang = barangService.updateBarang(barang);
        model.addAttribute("kodeBarang", updatedBarang.getKodeBarang());
        return "update-barang";
    }

    @GetMapping("/barang/cari")
    public String searchBarangEmpty(
        Model model) {
         List<TipeModel> listTipe = tipeService.getListTipe();
         model.addAttribute("listTipe", listTipe);
         return "search-barang";
    }

    @GetMapping(value ="/barang/cari", params={"namaTipe", "stok"})
    public String searchBarang(
            @RequestParam(required = false, value="namaTipe") String namaTipe,
            @RequestParam(required = false, value="stok") Integer stok,
            Model model
    ){
         List<BarangModel> listBarang = barangService.getListBarang();
         List<TipeModel> listTipe = tipeService.getListTipe();
         List<BarangModel> listBarangStokTipe = barangService.searchTipeAndStok(namaTipe, stok);
         model.addAttribute("listBarang", listBarang);
         model.addAttribute("listTopping", listTipe);
         model.addAttribute("listBarangStokTipe", listBarangStokTipe);
         return "search-barang";
    }


}
