package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeDb extends JpaRepository<TipeModel,Long> {
}
