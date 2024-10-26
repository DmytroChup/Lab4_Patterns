package org.example.lab4.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab4.entities.Profile;
import org.example.lab4.services.CustomerService;
import org.example.lab4.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final CustomerService customerService;

    @GetMapping
    public String listProfiles(Model model) {
        model.addAttribute("profiles", profileService.getAllProfiles());
        return "profile/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("profile", new Profile());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "profile/create";
    }

    @PostMapping
    public String createProfile(@ModelAttribute Profile profile) {
        profileService.createProfile(profile);
        return "redirect:/profiles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Profile profile = profileService.getProfileById(id);
        model.addAttribute("profile", profile);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "profile/edit";
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable("id") Long id, @ModelAttribute Profile profile) {
        profileService.updateProfile(id, profile);
        return "redirect:/profiles";
    }

    @GetMapping("/{id}")
    public String showProfile(@PathVariable("id") Long id, Model model) {
        Profile profile = profileService.getProfileById(id);
        model.addAttribute("profile", profile);
        return "profile/detail";
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profiles";
    }
}

