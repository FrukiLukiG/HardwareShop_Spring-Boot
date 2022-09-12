package hr.tvz.plese.hardwareapp.quartz_scheduler;

import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository.HardwareRepository;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository.JDBCHardwareRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HardwareJob extends QuartzJobBean {

    private final List<Hardware> hardware;

    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public HardwareJob(JdbcTemplate jdbcTemplate) {
        HardwareRepository hardwareRepo = new JDBCHardwareRepository(jdbcTemplate);
        this.hardware = hardwareRepo.findAll();
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        Date date = new Date();
        System.out.println();
        System.out.println(formatter.format(date) + " - Trenutno dostupni hardveri");
        System.out.println("---------------------------------------");
        this.hardware.forEach(h -> System.out.println(h.getName() + " - " + h.getNumAvailable()));
        System.out.println("---------------------------------------");
    }
}
