package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PembelianDb extends JpaRepository<PembelianModel,Long> {

    List<PembelianModel> findByOrderByIdPembelianAsc();

    Optional<PembelianModel> findByIdPembelian(Long idPembelian);

    @Override
    void delete(PembelianModel barang);

}
