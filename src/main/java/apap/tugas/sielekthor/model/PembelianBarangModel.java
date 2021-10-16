package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pembelian_barang")
public class PembelianBarangModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_barang")
    BarangModel barang;

    @ManyToOne
    @JoinColumn(name = "id_pembelian")
    PembelianModel pembelian;

    @NotNull
    @Column(name="tanggal_garansi", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalGaransi;

    @NotNull
    @Column(name="quantity", nullable = false)
    private Integer quantity;

}
