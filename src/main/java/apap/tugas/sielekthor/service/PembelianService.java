package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;

import java.util.List;

public interface PembelianService {

    List<PembelianModel> findByOrderByIdPembelianAsc();

    PembelianModel getPembelianByIdPembelian(Long idPembelian);

    void addPembelian(PembelianModel pembelian);

    void generateTanggalPembelian(PembelianModel pembelian);

    void delete(PembelianModel pembelian);

}
