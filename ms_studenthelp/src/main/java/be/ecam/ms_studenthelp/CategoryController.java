package be.ecam.ms_studenthelp;

import java.util.List;
import java.util.stream.Collectors;

import be.ecam.ms_studenthelp.Database.entities.CategoryEntity;
import be.ecam.ms_studenthelp.Database.repositories.CategoryRepository;
import be.ecam.ms_studenthelp.Object.Category;
import be.ecam.ms_studenthelp.utils.CategoryBody;
import be.ecam.ms_studenthelp.utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public List<String> getCategories() {
		List<CategoryEntity> categories = categoryRepository.findAll();

		return categories
				.stream()
				.map(CategoryEntity::getTitle)
				.collect(Collectors.toList());
	}

	@PostMapping("/categories")
	public Category createCategory(@RequestBody String body) {
		CategoryBody categoryBody = CategoryBody.fromBody(body);
		CategoryEntity categoryEntity = new CategoryEntity(categoryBody.getTitle());

		// If the category is already on the database
		if (categoryRepository.existsByTitle(categoryBody.getTitle())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("The category %s is already on the database",
							categoryBody.getTitle()));
		}

		categoryRepository.save(categoryEntity);
		return categoryEntity.toCategory();
	}

	@DeleteMapping("/categories/{category}")
	public Category deleteCategory(@PathVariable("category") String category) {
		CategoryEntity categoryEntity = DatabaseUtils.getCategoryFromDatabase(category,
				categoryRepository);

		categoryRepository.delete(categoryEntity);

		return categoryEntity.toCategory();
	}
}

