package io.namoosori.travelclub.phase7.aggregate.membership.store.mongo;

import io.namoosori.travelclub.phase7.aggregate.membership.store.MembershipStore;
import io.namoosori.travelclub.phase7.aggregate.membership.store.mongo.jpo.MembershipJpo;
import io.namoosori.travelclub.phase7.aggregate.membership.store.mongo.repository.MembershipRepository;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMembershipException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipMongoStore implements MembershipStore {
    //
    private MembershipRepository membershipRepository;

    public MembershipMongoStore(MembershipRepository membershipRepository) {
        //
        this.membershipRepository = membershipRepository;
    }

    @Override
    public String create(Membership membership) {
        //
        MembershipJpo membershipJpo = membershipRepository.save(new MembershipJpo(membership));
        return membershipJpo.getId();
    }

    @Override
    public Membership retrieve(String clubId, String memberId) {
        //
        Optional<MembershipJpo> membershipJpo = membershipRepository.findByClubIdAndMemberId(clubId, memberId);
        return membershipJpo.map(MembershipJpo::toDomain).orElse(null);
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId, boolean descending) {
        //
        Sort sort = Sort.by("memberEmail");
        sort = descending ? sort.descending() : sort.ascending();

        List<MembershipJpo> membershipJpo = membershipRepository.findAllByClubId(clubId, sort);
        return membershipJpo.stream().map(MembershipJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Membership> retrieveByMemberId(String memberId, boolean descending) {
        //
        Sort sort = Sort.by("clubId");
        sort = descending ? sort.descending() : sort.ascending();

        List<MembershipJpo> membershipJpo = membershipRepository.findAllByMemberId(memberId, sort);
        return membershipJpo.stream().map(MembershipJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Membership membership) {
        //
        membershipRepository.save(new MembershipJpo(membership));
    }

    @Override
    public void delete(String clubId, String memberEmail) {
        //
        Membership membership = retrieve(clubId, memberEmail);

        if (membership == null) {
            throw new NoSuchMembershipException("No such membership");
        }

        membershipRepository.deleteById(membership.getId());
    }

    @Override
    public boolean exists(String clubId, String memberEmail) {
        return retrieve(clubId, memberEmail) != null;
    }
}
