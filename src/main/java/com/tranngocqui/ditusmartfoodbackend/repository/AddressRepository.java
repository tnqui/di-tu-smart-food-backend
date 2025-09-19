package com.tranngocqui.ditusmartfoodbackend.repository;


import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
