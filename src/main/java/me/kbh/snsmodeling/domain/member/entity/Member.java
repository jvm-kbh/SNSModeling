package me.kbh.snsmodeling.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Member {

  final private Long id;
  private final String nickname;
  final private String email;
  final private LocalDate birthday;
  final private LocalDateTime createdAt;

  final private static Long NAME_MAX_LENGH = 10L;


  @Builder
  public Member(Long id, String nickname, String email, LocalDate birthday,
      LocalDateTime createdAt) {
    this.id = id;

    validateNickname(nickname);
    this.nickname = Objects.requireNonNull(nickname);
    this.email = Objects.requireNonNull(email);
    this.birthday = Objects.requireNonNull(birthday);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
  }

  void validateNickname(String nickname) {
    Assert.isTrue(nickname.length() <= NAME_MAX_LENGH, "최대길이 초과");
  }
}
