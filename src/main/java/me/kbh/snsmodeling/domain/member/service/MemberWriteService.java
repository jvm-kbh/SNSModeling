package me.kbh.snsmodeling.domain.member.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.kbh.snsmodeling.domain.member.dto.RegisterMemberCommand;
import me.kbh.snsmodeling.domain.member.entity.Member;
import me.kbh.snsmodeling.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberWriteService {

  MemberRepository memberRepository;

  public void create(RegisterMemberCommand command) {
    var member = Member.builder()
        .nickname(command.nickname())
        .birthday(command.birthday())
        .email(command.email())
        .build();

    memberRepository.save(member);
  }
}