package org.example.lab4.services;

import org.example.lab4.entities.Profile;
import org.example.lab4.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public void updateProfile(Long id, Profile newProfile) {
        Profile existingProfile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        existingProfile.setAddress(newProfile.getAddress());
        existingProfile.setPhoneNumber(newProfile.getPhoneNumber());
        existingProfile.setDateOfBirth(newProfile.getDateOfBirth());
        existingProfile.setCustomer(newProfile.getCustomer());

        profileRepository.save(existingProfile);
    }

    public void createProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

