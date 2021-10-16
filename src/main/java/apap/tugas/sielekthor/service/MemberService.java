package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;

import java.util.List;

public interface MemberService {

    List<MemberModel> getListMember();

    List<MemberModel> findByOrderByIdMemberAsc();

    void addMember(MemberModel member);

    MemberModel getMemberByIdMember(Long idMember);

    MemberModel updateMember(MemberModel member);

}
