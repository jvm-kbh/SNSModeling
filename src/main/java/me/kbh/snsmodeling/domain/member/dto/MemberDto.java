package me.kbh.snsmodeling.domain.member.dto;

import java.time.LocalDate;

public record MemberDto(
    Long id,
    String nickname,
    String email,
    LocalDate birthDay
) {

}