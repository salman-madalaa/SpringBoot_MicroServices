package com.embarkx.firstjobapp.companie.service;

import com.embarkx.firstjobapp.companie.model.Company;
import com.embarkx.firstjobapp.companie.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companieRepo;

    public List<Company> getAll() {
        return companieRepo.findAll();
    }

    public Company create(Company company) {
        return companieRepo.save(company);
    }

    public boolean delete(Long id) {
        try {
            companieRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Long id, Company company) {

        Optional<Company> optionalCompanie = companieRepo.findById(id);
        if (optionalCompanie.isPresent()) {
            Company company1 = new Company();
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            company.setJobs(company.getJobs());
            companieRepo.save(company1);
            return true;
        }
        return false;

    }

    @Override
    public Company getById(Long id) {
        Optional<Company> optionalCompanie = companieRepo.findById(id);
        if (optionalCompanie.isPresent()) {
            return optionalCompanie.get();
        }
        return null;
    }
}
