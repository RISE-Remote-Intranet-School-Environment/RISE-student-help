package be.ecam.ms_studenthelp.Controller;

import java.util.List;
import java.util.stream.Collectors;

import be.ecam.ms_studenthelp.Database.Entities.CategoryEntity;
import be.ecam.ms_studenthelp.Database.Repositories.CategoryRepository;
import be.ecam.ms_studenthelp.Object.Category;
import be.ecam.ms_studenthelp.Utils.CategoryBody;
import be.ecam.ms_studenthelp.Utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoryController {
	/**
	 * Class that manage the API for the categories
	 */
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * GET path for the {@link Category}'s.
	 * @return All the categories that exists.
	 */
	@GetMapping("/categories")
	public List<String> getCategories() {
		List<CategoryEntity> categories = categoryRepository.findAll();

		return categories.stream().map(CategoryEntity::getTitle).collect(Collectors.toList());
	}

	/**
	 * POST request to create a {@link Category} based on a body.
	 * @param body Body contained in the POST request.
	 * @return Category created.
	 */
	@PostMapping("/categories")
	public Category createCategory(@RequestBody String body) {
		CategoryBody categoryBody = CategoryBody.fromBody(body);
		CategoryEntity categoryEntity = new CategoryEntity(categoryBody.getTitle());

		// If the category is already on the database
		if (categoryRepository.existsByTitle(categoryBody.getTitle())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("The category %s is already on the database", categoryBody.getTitle()));
		}

		// Save the category in the database
		categoryRepository.save(categoryEntity);
		return categoryEntity.toCategory();
	}

	/**
	 * Delete the specified {@link Category} from the database.
	 * @param category Category to delete.
	 * @return Category that have been deleted.
	 */
	@DeleteMapping("/categories/{category}")
	public Category deleteCategory(@PathVariable("category") String category) {
		CategoryEntity categoryEntity = DatabaseUtils.getCategoryFromDatabase(category,
				categoryRepository);

		categoryRepository.delete(categoryEntity);

		return categoryEntity.toCategory();
	}
}
