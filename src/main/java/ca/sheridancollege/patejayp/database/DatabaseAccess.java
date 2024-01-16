package ca.sheridancollege.patejayp.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.patejayp.beans.Capstone;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public List<Capstone> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM capstone ORDER BY vote DESC";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class));
	}

	public Capstone findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM capstone WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class)).get(0);
	}

	public Long save(Capstone capstone) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO capstone(name, description, author) VALUES(:name, :description, :author)";
		namedParameters.addValue("name", capstone.getName());
		namedParameters.addValue("description", capstone.getDescription());
		namedParameters.addValue("author", capstone.getAuthor());

		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

	public Capstone getVote(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT vote FROM capstone WHERE id = :id";
		namedParameters.addValue("id", id);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Capstone>(Capstone.class)).get(0);

	}

	public void VoteUp(Capstone capstone, Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE capstone SET vote = :vote WHERE id = :id";
		namedParameters.addValue("vote", capstone.getVote() + 1);
		namedParameters.addValue("id", id);

		jdbc.update(query, namedParameters);
	}

	public void VoteDown(Capstone capstone, Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE capstone SET vote = :vote WHERE id = :id";
		namedParameters.addValue("vote", capstone.getVote() - 1);
		namedParameters.addValue("id", id);

		jdbc.update(query, namedParameters);
	}

	public void deleteById(Long id) {
		MapSqlParameterSource nameParameter = new MapSqlParameterSource();

		String query = "DELETE FROM capstone WHERE id = :id";
		nameParameter.addValue("id", id);

		jdbc.update(query, nameParameter);
	}

	public void deleteAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM capstone";
		jdbc.update(query, namedParameters);
	}

	public Long count() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM capstone";
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}

	public void saveAll(List<Capstone> capstoneList) {
		for (Capstone c : capstoneList) {
			save(c);
		}
	}

}
