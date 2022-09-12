package hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository;

import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JDBCHardwareRepository implements HardwareRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JDBCHardwareRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("h_id");
    }

    @Override
    public List<Hardware> findAll(){
        return List.copyOf(jdbcTemplate.query("SELECT * FROM hardware", this::mapRowHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code){
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM hardware WHERE h_code = ?", this::mapRowHardware, code)
            );
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware){
        try {
            hardware.setId(saveHardware(hardware));
            return Optional.of(hardware);
        }catch (DuplicateKeyException ex){
            return Optional.empty();
        }
    }

    private long saveHardware(Hardware hardware){
        Map<String, Object> vals = new HashMap<>();

        vals.put("h_name", hardware.getName());
        vals.put("h_code", hardware.getCode());
        vals.put("h_price", hardware.getPrice());
        vals.put("h_type", hardware.getType());
        vals.put("h_num_available", hardware.getNumAvailable());

        return jdbcInsert.executeAndReturnKey(vals).longValue();
    }

    @Override
    public Optional<Hardware> update(Hardware hardware){
        int exe = jdbcTemplate.update("UPDATE hardware SET " +
                "h_name = ?, h_price = ?, h_type = ?, h_num_available = ? WHERE h_code = ?",
                hardware.getName(), hardware.getPrice(),
                hardware.getType().toString(), hardware.getNumAvailable(), hardware.getCode()
        );

        if (exe > 0){
            return Optional.of(hardware);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code){
        jdbcTemplate.update("DELETE FROM review WHERE h_id = (SELECT h_id FROM hardware WHERE h_code = ?)", code);
        jdbcTemplate.update("DELETE FROM hardware WHERE h_code = ?", code);
    }


    private Hardware mapRowHardware(ResultSet r, int num) throws SQLException {
        return new Hardware(
                r.getLong("h_id"),
                r.getString("h_name"),
                r.getString("h_code"),
                r.getDouble("h_price"),
                TypeEnum.valueOf(r.getString("h_type")),
                r.getInt("h_num_available")
        );
    }
}
