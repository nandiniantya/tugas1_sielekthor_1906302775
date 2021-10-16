package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.*;
import apap.tugas.sielekthor.repository.*;
import apap.tugas.sielekthor.service.BarangService;
import apap.tugas.sielekthor.service.BarangServiceImpl;
import apap.tugas.sielekthor.service.PembelianService;
import apap.tugas.sielekthor.service.PembelianServiceImpl;
import apap.tugas.sielekthor.service.MemberService;
import apap.tugas.sielekthor.service.MemberServiceImpl;
import apap.tugas.sielekthor.service.PembelianBarangService;
import org.apache.tomcat.jni.Local;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class PembelianController {

    @Qualifier("pembelianServiceImpl")
    @Autowired
    private PembelianService pembelianService;

    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;

    @Qualifier("pembelianBarangServiceImpl")
    @Autowired
    private PembelianBarangService pembelianBarangService;

    @GetMapping("/pembelian")
    public String viewAllPembelian(Model model) {
        List<PembelianModel> listPembelian = pembelianService.findByOrderByIdPembelianAsc();

        model.addAttribute("listPembelian", listPembelian);

        return "viewall-pembelian";
    }

    @GetMapping("/pembelian/{idPembelian}")
    public String viewDetailPembelian(
            @PathVariable Long idPembelian,
            Model model
    ) {
        PembelianModel pembelian = pembelianService.getPembelianByIdPembelian(idPembelian);
        if(pembelian == null){
            return "pembelian-null";
        }
        model.addAttribute("pembelian", pembelian);
        return "view-pembelian";
    }

    @GetMapping("/pembelian/hapus/{idPembelian}")
    public String deletePembelian(
            @PathVariable Long idPembelian,
            Model model
    ) {

        PembelianModel pembelian = pembelianService.getPembelianByIdPembelian(idPembelian);
        pembelianService.delete(pembelian);
        return "delete-pembelian";
    }

    public String turnAlphabetIntoNumber(String alphabet){
        String[] arrayAlphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] arrayNumbers = {"1","2","3","4","5","6","7","8","9","1","1","1","1","1","1","1","1","1","1","2","2","2","2","2","2","2"};
        int getIndex = Arrays.asList(arrayAlphabet).indexOf(alphabet);
        String number = arrayNumbers[getIndex];
        return number;
    }

    public void generateNoInvoice(PembelianModel pembelian){
        String noInvoice = "";
        noInvoice += turnAlphabetIntoNumber(Character.toString(pembelian.getMember().getNamaMember().charAt(0)));
        noInvoice += pembelian.getNamaAdmin().charAt(pembelian.getNamaAdmin().length()-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMM");
        noInvoice += formatter.format(pembelian.getTanggalPembelian());
        if(pembelian.getIsCash()){
            noInvoice += "02";
        }
        else{
            noInvoice += "01";
        }
        DateTimeFormatter getTanggal = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter getBulan = DateTimeFormatter.ofPattern("MM");
        Integer tanggal = Integer.valueOf(getTanggal.format(pembelian.getTanggalPembelian()));
        Integer bulan = Integer.valueOf(getBulan.format(pembelian.getTanggalPembelian()));
        Integer operasiTanggalBulan = (tanggal + bulan) * 5;
        noInvoice += String.format("%03d", operasiTanggalBulan);
        Random rnd = new Random();
        String CapitalizedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(2);
        for (int i = 0; i < 2; i++){
            int index = (int) (CapitalizedLetters.length() * Math.random());
            sb.append(CapitalizedLetters.charAt(index));
        }
        noInvoice += sb.toString();
        pembelian.setNoInvoice(noInvoice);
    }

    @GetMapping("/pembelian/tambah")
    public String addPembelianFormPage(Model model) {
        PembelianModel pembelian = new PembelianModel();
        PembelianBarangModel newPembelianBarang = new PembelianBarangModel();
        List<PembelianBarangModel> listPembelianBarang = new List<PembelianBarangModel>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<PembelianBarangModel> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(PembelianBarangModel pembelianBarangModel) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends PembelianBarangModel> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends PembelianBarangModel> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public PembelianBarangModel get(int index) {
                return null;
            }

            @Override
            public PembelianBarangModel set(int index, PembelianBarangModel element) {
                return null;
            }

            @Override
            public void add(int index, PembelianBarangModel element) {

            }

            @Override
            public PembelianBarangModel remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<PembelianBarangModel> listIterator() {
                return null;
            }

            @Override
            public ListIterator<PembelianBarangModel> listIterator(int index) {
                return null;
            }

            @Override
            public List<PembelianBarangModel> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        newPembelianBarang.setPembelian(pembelian);
        listPembelianBarang.add(newPembelianBarang);
        pembelian.setPembelianBarang(listPembelianBarang);

        // fetch all barang that is registered (to show barang options for client)
        ArrayList<BarangModel> listAllBarang = (ArrayList<BarangModel>) barangService.getListBarang();
        ArrayList<MemberModel> listAllMember = (ArrayList<MemberModel>) memberService.getListMember();

        // create new arraylist to store pembelian's barangs
//        ArrayList<BarangModel> newListBarang = new ArrayList<BarangModel>();
//        newListBarang.add(new BarangModel());
//        pembelian.setPembelianBarang(newListBarang);

        // assign noInvoice
        //pembelianService.generateNoInvoice(pembelian);
        pembelian.setNoInvoice("dummyInvoice");
        pembelian.setTanggalPembelian(LocalDateTime.now());
        pembelian.setTotalPembelian(0);

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("newPembelianBarang",newPembelianBarang);
        model.addAttribute("listAllBarang",listAllBarang);
        model.addAttribute("listAllMember",listAllMember);
        model.addAttribute("listPembelianBarang", listPembelianBarang);
//        model.addAttribute("newListBarang",newListBarang);

        return "form-add-pembelian";
    }


//    @PostMapping (value="/pembelian/tambah", params= {"addRow"})
//    public String addRow(@ModelAttribute PembelianModel pembelian, BindingResult bindingResult, Model model) {
//        if (pembelian.getListBarang() == null) {
//            pembelian.setListBarang(new ArrayList<BarangModel>());
//        }
//        ArrayList<MemberModel> existingListMember = (ArrayList<MemberModel>) memberService.getListMember();
//        ArrayList<BarangModel> existingListBarang = (ArrayList<BarangModel>) barangService.getListBarang();
//        pembelian.getListBarang().add(new BarangModel());
//        model.addAttribute("pembelian",pembelian);
//        model.addAttribute("existingListBarang",existingListBarang);
//        model.addAttribute("existingListMember",existingListMember);
//        return "form-add-pembelian";
//    }
//
//    @PostMapping(value="/pembelian/tambah", params={"removeRow"})
//    public String removeRow(@ModelAttribute PembelianModel pembelian, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
//        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//        pembelian.getListBarang().remove(rowId.intValue());
//
//        model.addAttribute("pembelian", pembelian);
//        return "form-add-pembelian";
//    }
//
    @PostMapping(value="/pembelian/tambah")
    public String addPembelianSubmitPage(
            @ModelAttribute PembelianModel pembelian,
            Model model){
        pembelianService.addPembelian(pembelian);
        model.addAttribute("pembelian", pembelian.getIdPembelian());
        return "add-pembelian";
    }

}
