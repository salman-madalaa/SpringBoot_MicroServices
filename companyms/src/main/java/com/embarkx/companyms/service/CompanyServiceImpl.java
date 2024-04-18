package com.embarkx.companyms.service;

import com.embarkx.companyms.model.Company;
import com.embarkx.companyms.repo.CompanyRepo;
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

    public boolean create(Company company) {
        try {
            companieRepo.save(company);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Long id) {

        if(companieRepo.existsById(id)) {
            companieRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Long id, Company company) {

        Optional<Company> optionalCompanie = companieRepo.findById(id);
        if (optionalCompanie.isPresent()) {
            Company company1 = new Company();
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            companieRepo.save(company1);
            return true;
        }
        return false;

    }

    @Override
    public Company getById(Long id) {
        return companieRepo.findById(id).orElse(null);
    }
}
