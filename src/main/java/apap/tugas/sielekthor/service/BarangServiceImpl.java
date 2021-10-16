package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangDb barangDb;

    @Override
    public List<BarangModel> findByOrderByIdBarangAsc() {
        return barangDb.findByOrderByIdBarangAsc();
    }

    @Override
    public void addBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public BarangModel getBarangByIdBarang(Long idBarang){
        Optional<BarangModel> barang = barangDb.findByIdBarang(idBarang);
        if(barang.isPresent()) return barang.get();
        else return null;
    }

//    @Override
//    public void deleteBarang(BarangModel barang) {
//        barangDb.delete(barang);
//    }

    @Override
    public BarangModel updateBarang(BarangModel barang){
        barangDb.save(barang);
        return barang;
    }

    @Override
    public List<BarangModel> getListBarang() {
        return barangDb.findAll();
    }

    @Override
    public List<BarangModel> searchTipeAndStok(String namaTipe, Integer stok){
        List<BarangModel> listBarang = barangDb.findAll();
        List<BarangModel> listSearchedTipeAndStok = new ArrayList<>();
        for (int i=0; i<listBarang.size(); i++){
            String cekTipe = listBarang.get(i).getTipe().getNamaTipe();
            Integer cekStok = listBarang.get(i).getStok();
            if(stok.equals(0) && cekStok.equals(0) && cekTipe.equals(namaTipe)){
                listSearchedTipeAndStok.add(listBarang.get(i));
            }
            else if(stok.equals(1) && cekStok >= 1 && cekTipe.equals(namaTipe)){
                listSearchedTipeAndStok.add(listBarang.get(i));
            }
        }
        return listSearchedTipeAndStok;
    }

}
