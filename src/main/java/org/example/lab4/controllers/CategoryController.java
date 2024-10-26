package org.example.lab4.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab4.entities.Category;
import org.example.lab4.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping
    public String createCategory(@ModelAttribute Category category) {
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/detail";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}

