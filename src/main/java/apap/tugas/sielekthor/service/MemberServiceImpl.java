package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.MemberDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDb memberDb;

    @Override
    public List<MemberModel> getListMember() {
        return memberDb.findAll();
    }

    @Override
    public List<MemberModel> findByOrderByIdMemberAsc(){
        return memberDb.findByOrderByIdMemberAsc();
    }

    @Override
    public void addMember(MemberModel member){
        memberDb.save(member);
    }

    @Override
    public MemberModel getMemberByIdMember(Long idMember){
        Optional<MemberModel> member = memberDb.findByIdMember(idMember);
        if(member.isPresent()) return member.get();
        else return null;
    }

    @Override
    public MemberModel updateMember(MemberModel member){
        memberDb.save(member);
        return member;
    }

}


