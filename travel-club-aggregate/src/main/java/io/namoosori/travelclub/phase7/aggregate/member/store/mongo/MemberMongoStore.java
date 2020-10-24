package io.namoosori.travelclub.phase7.aggregate.member.store.mongo;

import io.namoosori.travelclub.phase7.aggregate.member.store.MemberStore;
import io.namoosori.travelclub.phase7.aggregate.member.store.mongo.jpo.MemberJpo;
import io.namoosori.travelclub.phase7.aggregate.member.store.mongo.repository.MemberRepository;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberMongoStore implements MemberStore {

    private MemberRepository memberRepository;

    public MemberMongoStore(MemberRepository memberRepository) {
        //
        this.memberRepository = memberRepository;
    }

    @Override
    public String create(CommunityMember member) {
        //
        MemberJpo memberJpo = memberRepository.save(new MemberJpo(member));
        memberRepository.save(memberJpo);

        return memberJpo.getId();
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        //
        Optional<MemberJpo> memberJpo = memberRepository.findById(memberId);
        return memberJpo.map(MemberJpo::toDomain).orElse(null);
    }

    @Override
    public CommunityMember retrieveByEmail(String memberEmail) {
        //
        Optional<MemberJpo> member = memberRepository.findByEmail(memberEmail);
        return member.map(MemberJpo::toDomain).orElse(null);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name, boolean descending) {
        //
        Sort sort = Sort.by("name");
        sort = descending ? sort.descending() : sort.ascending();

        if (name == null) {
            name = "";
        }

        List<MemberJpo> memberJpos = memberRepository.findByNameContaining(name, sort);

        return memberJpos.stream().map(MemberJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        //
        memberRepository.save(new MemberJpo(member));
    }

    @Override
    public void delete(String memberId) {
        //
        memberRepository.deleteById(memberId);
    }

    @Override
    public boolean exists(String memberId) {
        //
        return memberRepository.existsById(memberId);
    }
}
