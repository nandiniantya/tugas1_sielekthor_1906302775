package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "barang")
public class BarangModel implements Serializable {
    @Id
//    @NotNull
//    @Size(max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarang;

    @NotNull
    @Size(max=255)
    @Column(name="nama_barang", nullable = false)
    private String namaBarang;

    @NotNull
    @Column(name="stok", nullable = false)
    private Integer stok;

    @NotNull
    @Column(name="jumlah_garansi", nullable = false)
    private Integer jumlahGaransi;

    @NotNull
    @Size(max=255)
    @Column(name="deskripsi_barang", nullable = false)
    private String deskripsiBarang;

    @NotNull
    @Size(max=255)
    @Column(name="kode_barang", nullable = false)
    private String kodeBarang;

    @NotNull
    @Size(max=255)
    @Column(name="merk_barang", nullable = false)
    private String merkBarang;

    @NotNull
    @Column(name="harga_barang", nullable = false)
    private Integer hargaBarang;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "idTipe", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipe;

    @OneToMany(mappedBy = "barang")
    List<PembelianBarangModel> pembelianBarang;

//    @ManyToMany
//    @JoinTable(
//            name = "pembelian_barang",
//            joinColumns = @JoinColumn(name = "id_barang"),
//            inverseJoinColumns = @JoinColumn(name = "id_pembelian"))
//    List<PembelianModel> listPembelian;
}
