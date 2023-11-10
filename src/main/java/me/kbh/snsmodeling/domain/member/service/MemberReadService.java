package me.kbh.snsmodeling.domain.member.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.kbh.snsmodeling.domain.member.entity.Member;
import me.kbh.snsmodeling.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberReadService {

  MemberRepository memberRepository;

  public Member getMember(Long id) {
    return memberRepository.findById(id).orElseThrow();
  }
}