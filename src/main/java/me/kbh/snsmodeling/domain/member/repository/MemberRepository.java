package me.kbh.snsmodeling.domain.member.repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.kbh.snsmodeling.domain.member.entity.Member;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberRepository {

  static final String TABLE = "member";

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private static final RowMapper<Member> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Member.builder()
      .id(resultSet.getLong("id")).nickname(resultSet.getString("nickname"))
      .email(resultSet.getString("email"))
      .birthday(resultSet.getObject("birthday", LocalDate.class))
      .createdAt(resultSet.getObject("createdAt", LocalDateTime.class)).build();

  public Optional<Member> findById(Long id) {
    var sql = String.format("SELECT * FROM %s WHERE id = :id ", TABLE);
    var params = new MapSqlParameterSource().addValue("id", id);
    List<Member> members = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);

    Member nullableMember = DataAccessUtils.singleResult(members);
    return Optional.ofNullable(nullableMember);
  }

  public List<Member> findAllByIdIn(List<Long> ids) {
    if (ids.isEmpty()) {
      return List.of();
    }

    String sql = String.format("SELECT * FROM %s WHERE id in (:ids)", TABLE);
    var params = new MapSqlParameterSource().addValue("ids", ids);
    return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
  }

  public Member save(Member member) {
    if (member.getId() == null) {
      return insert(member);
    }
    return update(member);
  }

  private Member insert(Member member) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
        namedParameterJdbcTemplate.getJdbcTemplate()).withTableName(TABLE)
        .usingGeneratedKeyColumns("id");

    SqlParameterSource params = new BeanPropertySqlParameterSource(member);
    var id = jdbcInsert.executeAndReturnKey(params).longValue();

    return Member.builder().id(id).nickname(member.getNickname()).email(member.getEmail())
        .birthday(member.getBirthday()).build();
  }

  private Member update(Member member) {
    var sql = String.format(
        "UPDATE %s set email = :email, nickname = :nickname, birthday = :birthday WHERE id = :id",
        TABLE);
    SqlParameterSource params = new BeanPropertySqlParameterSource(member);
    namedParameterJdbcTemplate.update(sql, params);
    return member;

  }
}
