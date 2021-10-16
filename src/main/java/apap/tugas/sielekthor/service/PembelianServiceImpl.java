package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService{

    @Autowired
    PembelianDb pembelianDb;

    @Override
    public List<PembelianModel> findByOrderByIdPembelianAsc() {
        return pembelianDb.findByOrderByIdPembelianAsc();
    }

    public PembelianModel getPembelianByIdPembelian(Long idPembelian){
        Optional<PembelianModel> pembelian = pembelianDb.findByIdPembelian(idPembelian);
        if(pembelian.isPresent())
            return pembelian.get();
        else return null;
    }

    @Override
    public void addPembelian(PembelianModel pembelian){
        pembelianDb.save(pembelian);
    }

    @Override
    public void generateTanggalPembelian(PembelianModel pembelian) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        pembelian.setTanggalPembelian(dateTimeNow);
    }

    @Override
    public void delete(PembelianModel pembelian) {
        pembelianDb.delete(pembelian);
    }

}
