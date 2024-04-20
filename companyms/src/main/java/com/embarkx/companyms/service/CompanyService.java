package com.embarkx.companyms.service;

import com.embarkx.companyms.model.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> getAll();

    public boolean create(Company company);

    public boolean delete(Long id);

    public boolean update(Long id, Company company);

    Company getById(Long id);
}
