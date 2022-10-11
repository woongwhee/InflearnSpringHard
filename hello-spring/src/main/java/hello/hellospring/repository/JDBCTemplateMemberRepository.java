package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;
    public JDBCTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate=new JdbcTemplate(dataSource);//생성자가 1개일때 autowired를 생략가능
    }


    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);//SJI keycolumn을 쓰면 쿼리를안써도됨?
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters=new HashMap<>();
        parameters.put("name",member.getName());
        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member>result = jdbcTemplate.query("select * from member where id=?",memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member>result = jdbcTemplate.query("select * from member where name = ? ",memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from memeber",memberRowMapper());
    }
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member=new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };

    }
}
