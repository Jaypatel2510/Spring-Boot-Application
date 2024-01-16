package ca.sheridancollege.patejayp.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.patejayp.beans.Capstone;



@SpringBootTest
@AutoConfigureTestDatabase
public class DatabaseAccessTest {
	
	@Autowired
	private DatabaseAccess da;

	@Test
	public void whenSaveCapstone_getCapstoneList() {

		int size = da.findAll().size();
		Capstone capstone = new Capstone();
		capstone.setName("Hello");

		da.save(capstone);

		assertEquals(da.findAll().size(), ++size);
	}

	@Test
	public void whenSaveCapstoneFailed_getCapstoneList() {

		int size = da.findAll().size();
		assertEquals(da.findAll().size(), size);
	}

	@Test
	public void whenDeleteCapstone() {
		long num = 1;
		Capstone capstone = new Capstone();
		da.save(capstone);
		int size = da.findAll().size();

		da.deleteById(num);
		assertEquals(da.findAll().size(), --size);

	}

	@Test
	public void whenDeleteCapstoneFailed() {
		long num = 5;
		Capstone capstone = new Capstone();
		da.save(capstone);
		int size = da.findAll().size();

		da.deleteById(num);
		assertEquals(da.findAll().size(), size);
	}
}
