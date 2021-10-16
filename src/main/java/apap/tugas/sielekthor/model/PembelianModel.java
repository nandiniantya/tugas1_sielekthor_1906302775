package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pembelian")
public class PembelianModel implements Serializable {
    @Id
//    @NotNull
//    @Size(max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembelian;

    @NotNull
    @Column(name="total", nullable = false)
    private Integer totalPembelian;

    @NotNull
    @Column(name="tanggal_pembelian", nullable = false)
    @DateTimeFormat(pattern = "dd/MM")
    private LocalDateTime tanggalPembelian;

    @NotNull
    @Size(max=255)
    @Column(name="nama_admin", nullable = false)
    private String namaAdmin;

    @NotNull
    @Size(max=255)
    @Column(name="no_invoice", nullable = false)//, unique = true)
    private String noInvoice;

    @NotNull
    @Column(name="is_cash", nullable = false)
    private Boolean isCash;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_member", referencedColumnName = "idMember", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberModel member;

    @OneToMany(mappedBy = "pembelian")
    List<PembelianBarangModel> pembelianBarang;

//    @ManyToMany(mappedBy = "listPembelian")
//    List<BarangModel> listBarang;

}
