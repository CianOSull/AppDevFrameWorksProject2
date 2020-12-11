package ie.cian;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ie.cian.entities.Director;
import ie.cian.entities.Director;
import ie.cian.entities.Film;
import ie.cian.service.DirectorService;
import ie.cian.service.FilmService;

@Profile("dev")
@Component
public class ApplicationDev implements CommandLineRunner {

	@Autowired
	DirectorService directorService;
	
	@Autowired
	FilmService filmService;
	
	@Override
	public void run(String... args) throws Exception {
		// Save these counties to the database
		Director SS = directorService.save("Steven", "Spielberg");
		Director AH = directorService.save("Alfred", "Hitchcock");
		Director CN = directorService.save("Christopher", "Nolan");
		Director QT = directorService.save("Quentin", "Tarantino");
		
		// Save these films to the database
		filmService.save("Jaws", SS);
		filmService.save("Jurrasic Park", SS);
		filmService.save("Rear Window", AH);
		filmService.save("Intersteller", CN);
		filmService.save("Tenet", CN);
		filmService.save("Django Unchained", QT);
	}
}
