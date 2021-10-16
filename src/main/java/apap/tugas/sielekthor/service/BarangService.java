package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.apache.tomcat.jni.Local;

import java.util.List;

public interface BarangService {

    List<BarangModel> getListBarang();

    List<BarangModel> findByOrderByIdBarangAsc();

    List<BarangModel> searchTipeAndStok(String namaTipe, Integer stok);

    void addBarang(BarangModel barang);

    BarangModel getBarangByIdBarang(Long idBarang);

//    void deleteBarang(BarangModel barang);

    BarangModel updateBarang(BarangModel barang);

}
