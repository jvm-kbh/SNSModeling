package me.kbh.snsmodeling.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.kbh.snsmodeling.domain.member.dto.RegisterMemberCommand;
import me.kbh.snsmodeling.domain.member.entity.Member;
import me.kbh.snsmodeling.domain.member.service.MemberReadService;
import me.kbh.snsmodeling.domain.member.service.MemberWriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/member")
public class MemberController {

  MemberWriteService memberWriteService;
  MemberReadService memberReadService;

  @PostMapping("/register")
  public void register(@RequestBody RegisterMemberCommand command) {
    memberWriteService.create(command);
  }

  @GetMapping("{id")
  public Member getMemeber(@PathVariable Long id) {
    return memberReadService.getMember(id);
  }
}
