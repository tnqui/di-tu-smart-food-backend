package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.address;

import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import com.tranngocqui.ditusmartfoodbackend.repository.AddressRepository;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressDomainServiceImpl implements AddressDomainService {
    private final AddressRepository addressRepository;
    private final BaseService<Address, UUID> baseService;

    public AddressDomainServiceImpl(AddressRepository addressRepository, BaseServiceFactory baseServiceFactory) {
        this.addressRepository = addressRepository;
        this.baseService = baseServiceFactory.create(addressRepository);
    }

    @Override
    public Address getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<Address> getAddresses(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public void deleteAddressById(String id) {
        baseService.deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public List<Address> getAddressByUserId(String userId) {
        return addressRepository.findByUserId(UUIDUtils.parseUUUIDFromString(userId));
    }

    @Override
    public Address createAddress(Address address) {
        return baseService.save(address);
    }
}
